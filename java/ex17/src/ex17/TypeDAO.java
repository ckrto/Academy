package ex17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeDAO {
	Connection con = Database.getConnection();
	
	public void delete(String code, String tcode) {
		try {
			String sql = "delete from tbl_camp_type where code = ? and tcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, tcode);
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public void insert(TypeVO vo) {
		try {
			String sql = "insert into tbl_camp_type(code, tcode, qnt) values(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getTcode());
			ps.setInt(3, vo.getQnt());
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public TypeVO read(String code, String tcode) {
		TypeVO vo = new TypeVO();
		try {
			String sql = "select * from view_type where code = ? and tcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, tcode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setTcode(rs.getString("tcode"));
				vo.setTname(rs.getString("tname"));
				vo.setQnt(rs.getInt("qnt"));
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return vo;
	}
	
	public boolean delete(String fcode) {
		boolean success = false;
		try {
			String sql = "delete from tbl_type where tcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fcode);
			ps.execute();
			success = true;
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
		return success;
	}
	
	public TypeVO read(String tcode) {
		TypeVO vo = new TypeVO();
		try {
			String sql = "select * from tbl_type where tcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tcode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setTcode(rs.getString("tcode"));
				vo.setTname(rs.getString("tname"));
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return vo;
	}
	
	public void insert(String tcode, String tname) {
		try {
			String sql = "insert into tbl_type(tcode,tname) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tcode);
			ps.setString(2, tname);
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public String getMaxCode() {
		String newCode = "";
		try {
			String sql = "select max(tcode) mcode from tbl_type";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String maxCode = rs.getString("mcode");
				int code = Integer.parseInt(maxCode.substring(1)) + 10;
				newCode = "t" + code;
			}
		} catch(Exception e) {
			System.out.println("마지막코드 : " + e.toString());
		}
		return newCode;
	}
	
	public ArrayList<TypeVO> list() {
		ArrayList<TypeVO> array = new ArrayList<TypeVO>();
		try {
			String sql = "select * from tbl_type";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TypeVO vo = new TypeVO();
				vo.setTcode(rs.getString("tcode"));
				vo.setTname(rs.getString("tname"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("유형목록 : " + e.toString());
		}
		return array;
	}
	
	public ArrayList<TypeVO> list(String code) {
		ArrayList<TypeVO> array = new ArrayList<TypeVO>();
		try {
			String sql = "select * from view_type where code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TypeVO vo = new TypeVO();
				vo.setCode(rs.getString("code"));
				vo.setTcode(rs.getString("tcode"));
				vo.setTname(rs.getString("tname"));
				vo.setQnt(rs.getInt("qnt"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("유형목록 : " + e.toString());
		}
		return array;
	}
}
