package ex17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CampDAO {
	Connection con = Database.getConnection();
	
	public void insert(CampVO vo) {
		try {
			String sql = "insert into tbl_camp(code, cname, address) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getCname());
			ps.setString(3, vo.getAdress());
			ps.execute();
		} catch(Exception e) {
			System.out.println("오류 : " + e.toString());
		}
	}
	
	public String getNewCode() {
		String newCode = "";
		try {
			String sql = "select max(code) mcode from tbl_camp";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String maxCode = rs.getString("mcode");
				int code = Integer.parseInt(maxCode.trim().substring(1)) + 100;
				newCode = "c" + code;
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return newCode;
	}
	
	public CampVO read(String code) {
		CampVO vo = new CampVO();
		try {
			String sql = "select * from tbl_camp where code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setCode(rs.getString("code"));
				vo.setCname(rs.getString("cname"));
				vo.setAdress(rs.getString("address"));
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return vo;
	}
	
	public ArrayList<CampVO> list() {
		ArrayList<CampVO> array = new ArrayList<CampVO>();
		try {
			String sql = "select * from tbl_camp";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CampVO vo = new CampVO();
				vo.setCode(rs.getString("code"));
				vo.setCname(rs.getString("cname"));
				vo.setAdress(rs.getString("address"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
}
