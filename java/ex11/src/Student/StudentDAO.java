package Student;

import java.util.ArrayList;

public interface StudentDAO {
	
	//입력
	public void insert(StudentVO vo);
	
	//검색
	public StudentVO read(int no);
	
	//삭제
	public void delete(int no);
	
	//수정
	public void update(StudentVO vo);

	//목록
	public ArrayList<StudentVO> list();
	
	//마지막 코드값구하기
	public int getCode();
}
