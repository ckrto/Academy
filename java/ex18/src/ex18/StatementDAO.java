package ex18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StatementDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String odate;
	
	public void insert(StatementVO statementvo) {
		try {
			String sql = "insert into tbl_statement(ano, type, amount) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statementvo.getAno());
			ps.setString(2, statementvo.getType());
			ps.setInt(3, statementvo.getAmount());
			ps.execute();
		} catch(Exception e) {
			System.out.println("입출금 등록 : " + e.toString());
		}
	}
	
	public ArrayList<StatementVO> list(int ano) {
		ArrayList<StatementVO> array = new ArrayList<StatementVO>();
		try {
			String sql = "select * from tbl_Statement where ano = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StatementVO statementvo = new StatementVO();
				statementvo.setAno(rs.getInt("ano"));
				odate = sdf.format(rs.getDate("odate"));
				statementvo.setOdate(odate);
				statementvo.setType(rs.getString("type"));
				statementvo.setAmount(rs.getInt("amount"));
				array.add(statementvo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
}
