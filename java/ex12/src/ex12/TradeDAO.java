package ex12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TradeDAO {
	File file = new File("c:/data/java/ex12/trade.txt");
	
	public ArrayList<TradeVO> list(int no) {
		ArrayList<TradeVO> array = new ArrayList<TradeVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				if(lineNo == no) {
					TradeVO vo = new TradeVO();
					vo.setNo(lineNo);
					vo.setDate(st.nextToken());
					vo.setType(st.nextToken());
					vo.setAmount(Integer.parseInt(st.nextToken()));
					array.add(vo);
				}
			}
		} catch(Exception e) {
			
		}
		return array;
	}
	
	public void insert(TradeVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getDate() + "|" + vo.getType() + "|" + vo.getAmount() + "\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("거래내역 등록 : " + e.toString());
		}
	}
}
