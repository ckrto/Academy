package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	
	Connection con = Database.CON;
	
	public void delete(int no) {
		BBSVO vo = new BBSVO();
		try {
			String sql = "delete from bbs where no=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.execute();
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
	}
	
	public void update(BBSVO vo) {
	      try {
	         String sql = "update bbs set title=?, content=?, wdate=now() where no=?";
	         PreparedStatement ps = con.prepareStatement(sql);
	         ps.setString(1, vo.getTitle());
	         ps.setString(2, vo.getContent());
	         ps.setInt(3, vo.getNo());
	         ps.execute();
	      }catch(Exception e) {
	         System.out.println("수정 :" + e.toString());
	      }
	   }
	
	public BBSVO read(int no) {
		BBSVO vo = new BBSVO();
		try {
			String sql = "select *, date_format(wdate, '%Y-%m-%d %T') fdate from bbs where no = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getString("fdate"));
			}
		} catch(Exception e) {
			System.out.println("게시글 정보 : " + e.toString());
		}
		return vo;
	}
	
	public int count(String type, String query) {
		int count = 0;
		try {
			String sql = "select count(*) cnt from bbs where " + type + " like ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) {
			System.out.println("카운트 : " + e.toString());
		}
		return count;
	}
	
	public ArrayList<BBSVO> list(int page, String type, String query) {
		ArrayList<BBSVO> array = new ArrayList<BBSVO>();
		try {
			String sql = "select *, date_format(wdate, '%Y-%m-%d %T') fdate from bbs where " + type + " like ? order by no desc limit ?,5";
			int start = (page - 1) * 5;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
			ps.setInt(2, start);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BBSVO vo = new BBSVO();
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getString("fdate"));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
	
	public void insert(BBSVO vo) {
		try {
			String sql = "insert into bbs(title, content, writer) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			ps.execute();
		} catch(Exception e) {
			System.out.println("게시글 등록 : " + e.toString());
		}
	}
}