package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import service.UserService;

public class AdminView {
	private static UserService userServ = new UserService();
	private static Scanner input = new Scanner(System.in);
	
	
	public static void showMainView(){
		//Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("欢迎使用用户管理系统！");
			System.out.println("======================");
			System.out.println("用户登录-------------1");
			System.out.println("用户注册-------------2");
			System.out.println("退出程序-------------3");
			
			int choice = input.nextInt();
			

			if(choice == 1){
				//用户登录
				  System.out.println("请输入您的用户名：");
				  String name = input.next();
				  System.out.println("请输入您的密码：");
				  String password = input.next();
				List<String> list = userServ.login(name,password);
				
				if(list == null){
					System.out.println("用户信息不存在！");
				}else{
					if(list.get(1).equals("普通用户")){
						System.out.println(list.get(0)+"您好:\t您的权限是： 普通用户");
						System.out.println("=========================");
						showCommonView(list);//显示普通用户界面
						break;
					}
					if(list.get(1).equals("管理员")){
						System.out.println(list.get(0)+"您好:\t您的权限是： 管理员");
						System.out.println("=========================");
						showAdminView(list);//显示管理员界面
						break;
					}
				}
		     }
			if(choice == 2){
				//用户注册
				System.out.println("请输入用户名：");
				String name = input.next();
				System.out.println("请输入密码：");
				String password = input.next();
				System.out.println("请输入邮箱：");
				String email = input.next();
				
				if(userServ.insert(name,password,email)){
					System.out.println("用户注册成功！");
				}
				else{
					System.out.println("用户注册失败");
				}
			}
			if(choice == 3){
				System.exit(1);
			}
		}
		input.close();
	}

	//list里面保存了当前用户信息
	public static void showAdminView(List<String> list){

		while(true){
			System.out.println("\n");
			System.out.println("添加用户--------------1");
			System.out.println("删除用户--------------2");
			System.out.println("修改用户--------------3");
			System.out.println("查询用户--------------4");
			System.out.println("退出程序--------------5");

			int choice = input.nextInt();
			UserService userServ = new UserService();
			if(choice == 1){
				//添加用户
				System.out.println("请输入用户名：");
				String name = input.next();
				System.out.println("请输入密码：");
				String password = input.next();
				System.out.println("请输入邮箱：");
				String email = input.next();
				if(userServ.insert(name,password,email)){
					System.out.println("添加用户成功！");
				}
			}
			if(choice == 2){
				//删除用户
				System.out.println("请输入要删除用户的ID号码:");
				int id = input.nextInt();
				if(userServ.deleteUser(id)){
					System.out.println("删除成功!");
				}
				else{
					System.out.println("删除失败！");
				}
			}
			if(choice == 3){
				//修改用户
	              System.out.println("请输入要修改的用户的ID号码：");
	              int id = input.nextInt();
	              System.out.println("请输入要修改的用户的用户名：");
	              String name = input.next();
	              System.out.println("请输入要修改的用户的密码：");
	              String password = input.next();
	              System.out.println("请输入要修改的用户的邮箱：");
	              String email = input.next();
	              System.out.println("请输入要修改的用户的权限（管理员/普通用户）：");
	              String permission = input.next();
	              if(userServ.modify(list, id,name,password,email,permission)){
	            	  System.out.println("修改成功");
	              }else{
	            	  System.out.println("修改失败");
	              }
			}
			if(choice == 4){
				//查询用户
				queryMethod();
				
			}
			if(choice == 5){
				System.exit(1);
			}
		}
	}
	public static void queryMethod(){
		Iterator<String> iter = null;
    	   System.out.println("查询全部用户-------------1");
    	   System.out.println("根据ID查询用户-----------2");
    	   System.out.println("根据姓名查询用户--------- 3");
    	   System.out.println("请输入要做的操作:");
		
    	   int oper =input.nextInt();
    	   switch(oper){
    	   case 1:{
    		   	Map<String,List<String>> map = userServ.query();
    		   	int count = 0;
    		   	Set<String> set = map.keySet();
    		   	iter = set.iterator();
    		   	while(iter.hasNext()){
    		   		count = Integer.parseInt(iter.next());
    		   	}
    		   	List<String> list = map.get(String.valueOf(count));
    		   	iter = list.iterator();
    		   	String[] str = new String[list.size()];
    		   	int i = 0;
    		   	while(iter.hasNext()){
    		   		str[i] = iter.next();
    		   		i++;
    		   	}
    		   	for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
    		   	System.out.println("\n");
				  break;
			  }
    	   case 2:{
				  System.out.println("请输入要查询的用户的ID:");
				  int id = input.nextInt();
				  
				  Map<String,List<String>> map = userServ.query(id);
	              int count = 0;
	              Set<String> set = map.keySet();
	              iter = set.iterator();
	              while(iter.hasNext()){
	            	  count = Integer.parseInt(iter.next());
	              }
	              List<String> list = map.get(String.valueOf(count));
	              iter = list.iterator();
	              String[] str = new String[list.size()];
	              int i = 0;
	              while(iter.hasNext()){
	            	  str[i] = iter.next();
	            	  i++;
	              }
	              for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
				  break;
			  }
			  case 3:{
				  String name = null;
				  System.out.println("请输入要查询的用户的姓名:");
				   name = input.next();
				  Map<String,List<String>> map = userServ.query(name);
	              int count = 0;
	              Set<String> set = map.keySet();
	              iter = set.iterator();
	              while(iter.hasNext()){
	            	  count = Integer.parseInt(iter.next());
	              }
	              List<String> list = map.get(String.valueOf(count));
	              iter = list.iterator();
	              String[] str = new String[list.size()];
	              int i = 0;
	              while(iter.hasNext()){
	            	  str[i] = iter.next();
	            	  i++;
	              }
	              for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
				  break;
			  }
    	   }
	}
	
	//list里面保存了当前用户信息
	public static void showCommonView(List<String> list){
	while(true){
			System.out.println("修改自己的信息----------1");
			System.out.println("查询自己的信息----------2");
			System.out.println("退出程序---------------3");
			
			//Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			UserService userServ = new UserService();
			if(choice == 1){
				//修改自己的信息
				System.out.println("您当前的信息是：");	
				System.out.println("ID:\t 姓名:\t 密码:\t 邮箱:\t\t\t 用户权限：\t");
				System.out.println(list.get(4)+"\t"+list.get(0)+"\t"+list.get(2)+"\t"+list.get(3)+"\t"+list.get(1));
				System.out.println("请输入新用户名：");
				String name = input.next();
				System.out.println("请输入新密码：");
				String password = input.next();
				System.out.println("请输入新邮箱：");
				String email = input.next();
				if(userServ.modify(list, name,password,email)){
					System.out.println("修改成功");
				}else{
					System.out.println("修改失败");
				}
			
			}
			if(choice == 2){
				System.out.println("ID:\t 姓名:\t 密码:\t 邮箱:\t\t\t 用户权限：\t");
				System.out.println(list.get(4)+"\t"+list.get(0)+"\t"+list.get(2)+"\t"+list.get(3)+"\t"+list.get(1));
			}
			if(choice == 3){
				System.exit(1);
			}
		}
	}
	
	
	
	
	
  public static void main(String[] args){
	  showMainView();
  }
}
