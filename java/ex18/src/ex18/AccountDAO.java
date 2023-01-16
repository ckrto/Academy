package ex18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String odate;
	
	public void update(int ano, int balance) {
		try {
			String sql = "update tbl_account set balance = ? where ano = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, balance);
			ps.setInt(2, ano);
			ps.execute();
		} catch(Exception e) {
			System.out.println("입출금 : " + e.toString());
		}
	}
	
	public int getMaxCode() {
		int newCode = 0;
		try {
			String sql = "select max(ano) mcode from tbl_account";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int maxCode = rs.getInt("mcode");
				newCode = maxCode + 100;
			}
		} catch(Exception e) {
			System.out.println("마지막번호 : " + e.toString());
		}
		return newCode;
	}
	
	public void insert(AccountVO accountvo) {
		try {
			String sql = "insert into tbl_account(ano, aname, balance) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountvo.getAno());
			ps.setString(2, accountvo.getAname());
			ps.setInt(3, accountvo.getBalance());
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public ArrayList<AccountVO> list() {
		ArrayList<AccountVO> array = new ArrayList<AccountVO>();
		try {
			String sql = "select * from tbl_account";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				AccountVO accountvo = new AccountVO();
				accountvo.setAno(rs.getInt("ano"));
				accountvo.setAname(rs.getString("aname"));
				accountvo.setBalance(rs.getInt("balance"));
				odate = sdf.format(rs.getDate("odate"));
				accountvo.setOdate(odate);
				array.add(accountvo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
	
	public AccountVO read(int ano) {
		AccountVO accountvo = new AccountVO();
		try {
			String sql = "select * from tbl_account where ano = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				accountvo.setAno(rs.getInt("ano"));
				accountvo.setAname(rs.getString("aname"));
				accountvo.setBalance(rs.getInt("balance"));
				odate = sdf.format(rs.getDate("odate"));
				accountvo.setOdate(odate);
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return accountvo;
	}
}
