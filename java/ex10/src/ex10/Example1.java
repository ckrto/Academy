package ex10;

import java.util.ArrayList;

public class Example1 {
	public static void execute() {
		//학생 목록 출력
		StudentDAO dao = new StudentDAOImpl();
//		ArrayList<StudentVO> array = dao.list();
//		
//		System.out.println("전체 학생 수 : " + array.size());
//		for(StudentVO vo : array) {
//			System.out.println(vo.toString());
//		}
		
		//학생 검색
//		StudentVO vo = dao.read(100);
//		System.out.println(vo.toString());
		
		//학생 입력
//		StudentVO vo = new StudentVO();
//		vo.setNo(400);
//		vo.setName("성춘향");
//		vo.setTel("010-0001-0002");
//		vo.setAddress("서울 강남구 압구정동");
//		dao.insert(vo);
		
		//학생 삭제
//		dao.delete(100);
		
		//학생 수정
		StudentVO vo = new StudentVO();
		vo.setNo(100);
		vo.setName("김길동");
		vo.setTel("010-1111-1111");
		vo.setAddress("인천광역시 미추홀구 학익동");
		
		dao.update(vo);
		
	}
}
