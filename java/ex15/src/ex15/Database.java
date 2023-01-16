package ex15;

import java.sql.*;

public class Database {
	public static Connection getConnection(){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "java";
		String password = "pass";
		Connection con = null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			//System.out.println("접속성공");
		}catch(Exception e){
			System.out.println("에러:" + e.toString());
		}
		return con;
	}
}
