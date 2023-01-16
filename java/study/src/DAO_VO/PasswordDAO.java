package DAO_VO;

import java.sql.*;

import java.text.*;

import bank.Database;

public class PasswordDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//특정 계좌의 정보 가져오기
	public PasswordVO read(String accountNo) {
		PasswordVO vo=new PasswordVO();
		try {
			String sql="select *from VIEW_ALL where accountNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accountNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setAccountNo(rs.getString("accountNo"));
				vo.setAccountName(rs.getString("accountName"));
				vo.setBalance(rs.getInt("balance"));
				vo.setAccountTypeNo(rs.getString("accountTypeNo"));
				vo.setAccountDate(sdf.format(rs.getDate("accountDate")));
				vo.setPassword(rs.getString("password"));
			}
			
		}
		catch(Exception e) {
		}
		return vo;
	}
	
	public void insert(PasswordVO vo) {
		Connection con=Database.getConnection();
		try {
	         String sql="insert into tbl_password values (?,?,?,?)";
	         PreparedStatement ps = con.prepareStatement(sql);
	         ps.setString(1,vo.getAccountNo() );
	         ps.setString(2, vo.getPassword());
	         ps.setString(3, vo.getPasswordTypeNo());
	         ps.setString(4, vo.getPasswordAnswer() );
	         ps.execute();
	      }catch(Exception e) {
	         System.out.println("등록"+e.toString());
	      }
	   }
}
