package ex09;

import java.io.*;
import java.util.StringTokenizer;

public class Example3 {
	public static void execute() {
		
		String line = "";
		String name;
		String tel;
		String address;
		
		File file = new File("c:/data/java/address.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line=reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				name = st.nextToken();
				tel = st.nextToken();
				address = st.nextToken();
				
				System.out.println("이름 : " + name);
				System.out.println("전화번호 : " + tel);
				System.out.println("주소 : " + address);
				System.out.println("");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}