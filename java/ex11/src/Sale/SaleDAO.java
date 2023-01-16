package Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SaleDAO {
	File file = new File("c:/data/java/ex11/sales.txt");
	
	public int getNo() {
		int no = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
			System.out.println("getNO : " + e.toString());
		}
		return no;
	}
	
	//등록
	public void insert(SaleVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("등록 : " + e.toString());
		}
	}
	//목록
	public ArrayList<SaleVO> list() {
		ArrayList<SaleVO> array = new ArrayList<SaleVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line=bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				SaleVO vo = new SaleVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setPrice(Integer.parseInt(st.nextToken()));
				vo.setQnt(Integer.parseInt(st.nextToken()));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}
	
	//검색
	public SaleVO read(int no) {
		SaleVO vo = new SaleVO();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line=bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no == vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setPrice(Integer.parseInt(st.nextToken()));
					vo.setQnt(Integer.parseInt(st.nextToken()));
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("검색 : " + e.toString());
		}
		
		return vo;
	}
	
	//삭제
	public void delete(int no) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				SaleVO vo = new SaleVO();
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
	
	//수정
	
	public void update(SaleVO vo) {
		try {
			 BufferedReader bf = new BufferedReader(new FileReader(file));
			 String line = "";
			 String lines = "";
			 while((line = bf.readLine()) != null) {
				 StringTokenizer st = new StringTokenizer(line, "|");
				 int no = Integer.parseInt(st.nextToken());
				 if(no == vo.getNo()) {
					 lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n";
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
}
