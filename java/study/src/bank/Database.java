package bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection getConnection(){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test02";
		String password = "pass";
		Connection con = null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			//System.out.println("立加己傍");
		}catch(Exception e){
			System.out.println("立加俊矾:" + e.toString());
		}
		return con;
	}
}
