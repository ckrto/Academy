package Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class StudentDAOImpl implements StudentDAO{
	File file = new File("c:/data/java/ex11/student.txt");
	
	@Override
	public void insert(StudentVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getTel() + "|" + vo.getAddress() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) { 
			System.out.println("입력 : " + e.toString());
		}
	}

	@Override
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
					vo.setTel(st.nextToken());
					vo.setAddress(st.nextToken());
					break;
				}
				
			}
		} catch(Exception e) {
			System.out.println("검색 : " + e.toString());
		}
		return vo;
	}

	@Override
	public void delete(int no) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				StudentVO vo = new StudentVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no != vo.getNo()) {
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			writer.write(lines);
			writer.flush();
			writer.close();
			
		} catch(Exception e) {
			System.out.println("삭제 : " + e.toString());
		}
	}

	@Override
	public void update(StudentVO vo) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int no = Integer.parseInt(st.nextToken());
				if(no == vo.getNo()) {
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getTel() + "|" + vo.getAddress() + "\n";
				} 
				else {
					lines = lines + line + "\n";
				}
			}
			
			FileWriter writer = new FileWriter(file, false);
			writer.write(lines);
			writer.flush();
			writer.close();
			
		} catch(Exception e) {
			System.out.println("수정 : " + e.toString());
		}
	}

	@Override
	public ArrayList<StudentVO> list() {
		ArrayList<StudentVO> array = new ArrayList<>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				StudentVO vo = new StudentVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setTel(st.nextToken());
				vo.setAddress(st.nextToken());
				array.add(vo);	
			}
		} 
		catch(Exception e) {
			System.out.println("목록오류 : " + e.toString());
		}
		
		
		return array;
	}

	@Override
	public int getCode() {
		int no = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
			System.out.println("마지막 번호 : " + e.toString());
		}
		return no;
	}

	
}
