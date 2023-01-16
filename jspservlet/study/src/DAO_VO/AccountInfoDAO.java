package DAO_VO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bank.Database;

public class AccountInfoDAO {
	Connection con = Database.getConnection();
	
	//특정 상품코드의 상품 정보 조회
	public AccountInfoVO read(String accountTypeNo) {
		AccountInfoVO vo=new AccountInfoVO();
		try {
			String sql="select * from tbl_accountInfo where accountTypeNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accountTypeNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setAccountTypeNo(rs.getString("accountTypeNo"));
				vo.setAccountTypeName(rs.getString("accountTypeName"));
				vo.setAccountType(rs.getString("accountType"));
			}
		}
		catch(Exception e) {
			
		}
		return vo;
	}
	
}
	

