package ex14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CampDAO {
	File file = new File("c:/data/java/ex14/camp.txt");
	
	public int getLastCode() {
		int code = 0;
		CampVO vo = new CampVO();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				code = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
				System.out.println("Ä·ÇÎÀå ¸ñ·Ï : " + e.toString());
		}
		return code;
	}
	
	public void insert(CampVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getCode() + "|" + vo.getName() + "|" + vo.getAddress() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("Ä·ÇÎÀå µî·Ï : " + e.toString());
		}
	}
	
	public ArrayList<CampVO> list() {
		ArrayList<CampVO> array = new ArrayList<CampVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				CampVO vo = new CampVO();
				vo.setCode(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setAddress(st.nextToken());
				array.add(vo);
			}
			bf.close();
		} catch(Exception e) {
			System.out.println("Ä·ÇÎÀå ¸ñ·Ï : " + e.toString());
		}
		return array;
	}
	
	public CampVO read(int code) {
		CampVO vo = new CampVO();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineCode = Integer.parseInt(st.nextToken());
				if(code == lineCode) {
					vo.setCode(lineCode);
					vo.setName(st.nextToken());
					vo.setAddress(st.nextToken());
					break;
				}
			}
			bf.close();
		} catch(Exception e) {
			System.out.println("Ä·ÇÎÀå Á¶È¸ : " + e.toString());
		}
		return vo;
	}
}
