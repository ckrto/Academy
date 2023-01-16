package DAO_VO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bank.Database;


public class PasswordTypeDAO {
	public void insert(PasswordTypeVO vo) {
		Connection con=Database.getConnection();
		try {
	         
	         String sql="insert into tbl_passwordType values (?,?)";
	         PreparedStatement ps = con.prepareStatement(sql);
	         ps.setString(1,vo.getPasswordTypeNo());
	         ps.setString(2, vo.getPasswordQuestion());
	         ps.execute();
	      }catch(Exception e) {
	         System.out.println("µî·Ï"+e.toString());
	      }
	   }
}
