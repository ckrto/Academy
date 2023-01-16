package ex10;

import java.util.ArrayList;

public interface StudentDAO { // Database Access Object 데이터에 접근하기위한 객체
	//데이터 목록 출력
	public ArrayList<StudentVO> list();
	
	//데이터 검색
	public StudentVO read(int no);
	
	//데이터 등록
	public void insert(StudentVO vo);
	
	//데이터 수정
	public void update(StudentVO vo);
	
	//데이터 삭제
	public void delete(int no);
	
}
