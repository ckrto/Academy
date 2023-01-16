package ex13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProductDAO {
	
	File file = new File("c:/data/java/ex13/product.txt");
	
	public void update(int no, int sale) { // 상품 수량을 +_하기 위한 매서드
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = ""; // line의 값을 받아 새롭게 저장할 lines 변수를 생성
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				ProductVO vo = new ProductVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setPrice(Integer.parseInt(st.nextToken()));
				int qnt = Integer.parseInt(st.nextToken());
				vo.setQnt(qnt - sale); // qnt값에 sale값을 빼고 return하여 전달
				
				if(no == vo.getNo()) { // lines에 새로운 값들을 전달하여 저장
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n";
				} else { // 만약에 바뀐 정보가 아니라면 기존의 값을 저장
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false); // 새로운 값으로 덮어씌울 것이기 때문에 false로 지정
			writer.write(lines);
			writer.flush();
			writer.close();
			
		} catch(Exception e) {
			System.out.println("변경 : " + e.toString());
		}
	}
	
	
	public int getMaxNo() {
		int no = 0;
		
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
		
		return no;
	}
	
	public ArrayList<ProductVO> list(){
		ArrayList<ProductVO> array = new ArrayList<ProductVO>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				ProductVO vo = new ProductVO();
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
	
	public void insert(ProductVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("상품 등록 : " + e.toString());
		}
	}
	
	public ProductVO read(int no) {
		ProductVO vo = new ProductVO();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,"|");
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no == vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setPrice(Integer.parseInt(st.nextToken()));
					vo.setQnt(Integer.parseInt(st.nextToken()));
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return vo;
	}
}
