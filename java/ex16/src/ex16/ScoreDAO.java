package ex16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScoreDAO {
	Connection con = Database.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void delete(String sno, String tcode) {
		try {
			String sql = "delete from tbl_score where sno = ? and tcode = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			ps.setString(2, tcode);
			ps.execute();
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
	}
	
	//성적 등록
	public void insert(ScoreVO vo) {
		try {
			String sql = "insert into tbl_score(sno, tcode, grade) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getSno());
			ps.setString(2, vo.getTcode());
			ps.setInt(3, vo.getGrade());
			ps.execute();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	
	//특정 학생의 특정유형 검색
	public int read(String sno, String tcode) {
		int count = 0;
		try {
			 String sql = "select count(*) cnt from tbl_score where sno = ? and tcode = ?";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, sno);
			 ps.setString(2, tcode);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()) {
				 count = rs.getInt("cnt");
			 }
		} catch(Exception e) {
			System.out.println("검색 : " + e.toString());
		}
		return count;
	}
	
	//특정 성적 목록
	public ArrayList<ScoreVO> list(String sno) {
		ArrayList<ScoreVO> array = new ArrayList<ScoreVO>();
		try {
			String sql = "select * from view_score where sno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ScoreVO vo = new ScoreVO();
				vo.setSno(rs.getString("sno"));
				vo.setTcode(rs.getString("tcode"));
				vo.setTname(rs.getString("tname"));
				String tdate = sdf.format(rs.getDate("tdate"));
				vo.setTdate(tdate);
				vo.setGrade(rs.getInt("grade"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
}
