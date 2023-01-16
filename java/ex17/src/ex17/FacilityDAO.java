package ex17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FacilityDAO {
	Connection con = Database.getConnection();
	
	public void delete(String code, String fcode) {
		try {
			String sql = "delete from tbl_camp_facility where code = ? and fcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, fcode);
			ps.execute();
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
	}
	
	public void insert(FacilityVO vo) {
		try {
			String sql = "insert into tbl_camp_facility(code, fcode) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getFcode());
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public FacilityVO read(String code, String fcode) {
		FacilityVO vo = new FacilityVO();
		ArrayList<FacilityVO> array = new ArrayList<FacilityVO>();
		try {
			String sql = "select * from view_facility where code = ? and fcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, fcode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setCode(rs.getString("code"));
				vo.setFcode(rs.getString("fcode"));
				vo.setFname(rs.getString("fname"));
			}
		} catch(Exception e) {
			System.out.println("특정 목록 : " + e.toString());
		}
		return vo;
	}
	
	public boolean delete(String fcode) {
		boolean success = false;
		try {
			String sql = "delete from tbl_facility where fcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fcode);
			ps.execute();
			success = true;
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
		return success;
	}
	
	public FacilityVO read(String fcode) {
		FacilityVO vo = new FacilityVO();
		try {
			String sql = "select * from tbl_facility where fcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fcode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setFcode(rs.getString("fcode"));
				vo.setFname(rs.getString("fname"));
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return vo;
	}
	
	public void insert(String fcode, String fname) {
		try {
			String sql = "insert into tbl_facility(fcode,fname) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fcode);
			ps.setString(2, fname);
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	public String getMaxCode() {
		String newCode = "";
		try {
			String sql = "select max(fcode) mcode from tbl_facility";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String maxCode = rs.getString("mcode");
				int code = Integer.parseInt(maxCode.substring(1)) + 10;
				newCode = "f" + code;
			}
		} catch(Exception e) {
			System.out.println("마지막코드 : " + e.toString());
		}
		return newCode;
	}
	
	public ArrayList<FacilityVO> list() {
		ArrayList<FacilityVO> array = new ArrayList<FacilityVO>();
		try {
			String sql = "select * from tbl_facility";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FacilityVO vo = new FacilityVO();
				vo.setFcode(rs.getString("fcode"));
				vo.setFname(rs.getString("fname"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("특정 목록 : " + e.toString());
		}
		return array;
	}
	
	public ArrayList<FacilityVO> list(String code) {
		ArrayList<FacilityVO> array = new ArrayList<FacilityVO>();
		try {
			String sql = "select * from view_facility where code = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FacilityVO vo = new FacilityVO();
				vo.setCode(rs.getString("code"));
				vo.setFcode(rs.getString("fcode"));
				vo.setFname(rs.getString("fname"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("특정 목록 : " + e.toString());
		}
		return array;
	}
}
