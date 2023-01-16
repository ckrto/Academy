package ex16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeptDAO {
	Connection con = Database.getConnection();
	
	//수정
	public void update(DeptVO vo) {
		try {
			String sql = "update tbl_dept set dname = ? where dcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getDname());
			ps.setString(2, vo.getDcode());
			ps.execute();
		} catch(Exception e) {
			System.out.println("수정 : " + e.toString());
		}
	}
	
	//삭제
	public boolean delete(String dcode) {
		boolean success = false;
		try {
			String sql = "delete from tbl_dept where dcode=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dcode);
			ps.execute();
			success = true;
		} catch(Exception e) {
//			System.out.println("삭제 : " + e.toString());
		}
		return success;
	}
	
	//조회
	public DeptVO read(String dcode) {
		DeptVO vo = new DeptVO();
		try {
			String sql = "select * from tbl_dept where dcode=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dcode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setDcode(rs.getString("dcode"));
				vo.setDname(rs.getString("dname"));
			}
			
		} catch (Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		
		return vo;
	}
	
	//등록
	public void insert(DeptVO vo) {
		try {
			String sql = "insert into tbl_dept(dcode, dname) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getDcode());
			ps.setString(2, vo.getDname());
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	//Max Code값 구하기
	public String getMaxCode() {
		String maxCode = "";
		try {
			String sql = "select max(dcode) mcode from tbl_dept";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				maxCode = rs.getString("mcode");
			}
		} catch (Exception e) {
			System.out.println("Max code : " + e.toString());
		}
		return maxCode;
	}
	
	//목록
	public ArrayList<DeptVO> list(){
		ArrayList<DeptVO> array = new ArrayList<DeptVO>();
		try {
			String sql = "select * from tbl_dept";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DeptVO vo = new DeptVO();
				vo.setDcode(rs.getString("dcode"));
				vo.setDname(rs.getString("dname"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
}
