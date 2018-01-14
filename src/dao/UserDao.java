package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import common.ConnectionGet;
import common.UserInfo;

public class UserDao {

	private PreparedStatement psmt = null;
	private ResultSet rs = null;
//添加用户
	public boolean insert(UserInfo user){
		ConnectionGet cg = new ConnectionGet();
		Connection conn = cg.getConnection();
		String name = user.getName();
		String password = user.getPassword();
		String email = user.getEmail();
		int result =0;
		String sql = "insert into users values(seq_users.nextval,?,?,?,'普通用户')";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,name);
			psmt.setString(2,password);
			psmt.setString(3,email);
			
			result = psmt.executeUpdate();
			psmt.close();
			conn.close();
	   }
		catch(SQLException e){
		   e.printStackTrace();
	   }
		if(result > 0){
			return true;
		}
		else{
			return false;
		}

    }

//登录
	public List<String> login(String name,String password){
		ConnectionGet cg = new ConnectionGet();
		Connection conn = cg.getConnection();
		ArrayList<String> info_List = new ArrayList<String>();
		String sql = "select name,permission,password,email,id from users where name=? and password=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,name);
			psmt.setString(2,password);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				info_List.add(0, rs.getString(1));//name
				info_List.add(1, rs.getString(2));//permission
				info_List.add(2, rs.getString(3));//password
				info_List.add(3, rs.getString(4));//email
				info_List.add(4, rs.getString(5));//id
		    }
			else{
				info_List = null;
			}
			
			psmt.close();
			rs.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
       return info_List;
	}

//修改信息
	public boolean modify(List<String> list,Object...obj){
		boolean flag = false;
		ConnectionGet cg = new ConnectionGet();
		Connection conn = cg.getConnection();
		if("普通用户".equals(list.get(1))){
			//String sql1 = "select * from userinfo where "
			String sql = "update users set name=?, password=? , email=? where id='"+list.get(4)+"'";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,obj[0].toString());
				psmt.setString(2,obj[1].toString());
				psmt.setString(3,obj[2].toString());
				int result = psmt.executeUpdate();
				if(result > 0){
					flag = true;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else//如果为管理员
		{
			int id = Integer.parseInt(obj[0].toString());
			String sql = "update users set name=? , password=? , email=? , permission=? where id='"+id+"'";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,obj[1].toString());
				psmt.setString(2,obj[2].toString());
				psmt.setString(3, obj[3].toString());
				psmt.setString(4, obj[4].toString());
				int result = psmt.executeUpdate();
				if(result > 0){
					flag = true;
				}
				psmt.close();
				conn.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
//查询
	public Map<String,List<String>> queryInfo(Object...obj){
		ConnectionGet cg = new ConnectionGet();
		Connection conn = cg.getConnection();
		Map<String,List<String>> map = new TreeMap<String, List<String>>();
		List<String> smallList = new ArrayList<String>();
		int count=0;//表示总行数
		if(obj.length == 0){//表示查询全部
			String sql = "select id,name,password,email,permission from users";
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()){
						smallList.add(rs.getString(1));
						smallList.add(rs.getString(2));
						smallList.add(rs.getString(3));
						smallList.add(rs.getString(4));
						smallList.add(rs.getString(5));
						count++;
						}
               map.put(String.valueOf(count), smallList);
			}
            catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else
		{
		    String sql="";
			try{
				 int id = Integer.parseInt(obj[0].toString());
				 sql = "select id,name,password,email,permission from users where id='"+id+"'";
			   }catch(Exception e){//如果是字符串的话，会解析出错
				     String name = obj[0].toString();	
					 sql = "select id,name,password,email,permission from users where name like '%"+name+"%'";
			   }
				try {
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					while(rs.next()){
						smallList.add(rs.getString(1));
						smallList.add(rs.getString(2));
						smallList.add(rs.getString(3));
						smallList.add(rs.getString(4));
						smallList.add(rs.getString(5));
						count++;
						}
					map.put(String.valueOf(count), smallList);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		}
		return map;
	}
	
//删除
	public boolean delete(int id){
		boolean flag = false;
		ConnectionGet cg = new ConnectionGet();
		Connection conn = cg.getConnection();
		String sql="delete from users where id='"+id+"'";
		try {
			psmt=conn.prepareStatement(sql);
			int result = psmt.executeUpdate();
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
