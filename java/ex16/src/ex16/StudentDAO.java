package ex16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAO {
	Connection con = Database.getConnection();
	
	//삭제
	public boolean delete(String sno) {
		boolean success = false;
		try {
			String sql = "delete from tbl_student where sno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			ps.execute();
			success = true;
		} catch(Exception e) {
		
		}
		return success;
	}
	
	//수정
	public void update(StudentVO vo) {
		try {
			String sql = "update view_student set sname = ?, dcode = ? where sno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getSname());
			ps.setString(2, vo.getDcode());
			ps.setString(3, vo.getSno());
			ps.execute();
		} catch(Exception e) {
			System.out.println("수정 : " + e.toString());
		}
	}
	
	//Max Code 가져오기
	public String maxNo() {
		String maxNo = "";
		try {
			String sql = "select max(sno) mno from view_student";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				maxNo = rs.getString("mno");
			}
		} catch(Exception e) {
			System.out.println("Max Code : " + e.toString());
		}
		return maxNo;
	}
	
	//입력
	public void insert(StudentVO vo) {
		try {
			String sql = "insert into tbl_student(sno,sname,dcode) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getSno());
			ps.setString(2, vo.getSname());
			ps.setString(3, vo.getDcode());
			ps.execute();
		} catch(Exception e) {
			System.out.println("입력 : " + e.toString());
		}
	}
	
	//조회
	public StudentVO read(String sno) {
		StudentVO vo = new StudentVO();
		try {
			String sql = "select * from view_student where sno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setSno(rs.getString("sno"));
				vo.setSname(rs.getString("sname"));
				vo.setDcode(rs.getString("dcode"));
				vo.setDname(rs.getString("dname"));
			}
		} catch(Exception e) {
			System.out.println("조회 : " + e.toString());
		}
		return vo;
	}
	
	// 목록
	public ArrayList<StudentVO> list() {
		ArrayList<StudentVO> array = new ArrayList<StudentVO>();
		try {
			String sql = "select * from view_student order by sno";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setSno(rs.getString("sno"));
				vo.setSname(rs.getString("sname"));
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
