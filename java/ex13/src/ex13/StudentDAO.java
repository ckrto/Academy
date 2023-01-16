package ex13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StudentDAO {
	File file = new File("c:/data/java/ex13/student.txt");
	
	public int getMaxNo() { // 마지막 번호를 저장하기 위한 매서드
		int no = 0; // 초기값을 0으로 지정
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
			System.out.println("마지막 숫자 : " + e.toString());
		}
		return no; // no값을 다른곳에서도 사용하기위해 return으로 반환
	}
	
	public ArrayList<StudentVO> list() {
		ArrayList<StudentVO> array = new ArrayList<StudentVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				StudentVO vo = new StudentVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setAddress(st.nextToken());
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("학생목록 : " + e.toString());
		}
		return array;
	}
	
	public void insert(StudentVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getAddress() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("학생등록 : " + e.toString());
		}
	}
	
	public StudentVO read(int no) {
		StudentVO vo = new StudentVO();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no==vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setAddress(st.nextToken());
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("검색 : " + e.toString());
		}
		return vo;
	}
	
}
