package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * 获取数据库连接
 */
public class ConnectionGet {

	private static final String DBURL="jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String DBDRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String DBUSER="scott";
	private static final String DBPASS="tiger";
	public ConnectionGet(){
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
