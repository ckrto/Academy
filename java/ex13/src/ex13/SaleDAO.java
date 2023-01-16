package ex13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SaleDAO {
	
	File file = new File("c:/data/java/ex13/sale.txt");
	
	public ArrayList<SaleVO> list(int no){
		ArrayList<SaleVO> array = new ArrayList<SaleVO>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				if(lineNo == no) {
					SaleVO vo = new SaleVO();
					vo.setNo(lineNo);
					vo.setDate(st.nextToken());
					vo.setSale(Integer.parseInt(st.nextToken()));
					array.add(vo);
				}
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		
		return array;
	}
	
	public void insert(SaleVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getDate() + "|" + vo.getSale() + "\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("매출 등록 : " + e.toString());
		}
	}
}
