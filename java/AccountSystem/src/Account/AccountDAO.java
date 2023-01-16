package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AccountDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date;
	
	public AccountVO read(String accountNo) {
		AccountVO accountvo = new AccountVO();
		try {
			String sql = "select * from tbl_account where accountNo = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accountNo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				accountvo.setAccountNo(rs.getString("accountNo"));
				accountvo.setAccountName(rs.getString("accountName"));
				accountvo.setBalance(rs.getInt("balance"));
				date = sdf.format(rs.getDate("accountDate"));
				accountvo.setAccountDate(date);
			}
		} catch(Exception e) {
			System.out.println("Á¶È¸ : " + e.toString());
		}
		return accountvo;
	}
}
