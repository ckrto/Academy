package ex09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Example2 {
	public static void execute() {
		System.out.println("안녕하세요. 주소관리 시스템입니다.");
		File file = new File("c:/data/java/address.txt");
		
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write("홍길동,010-0000-0000,인천시 미추홀구\n");
			writer.write("심청이,010-1111-1111,인천시 연수구\n");
			writer.flush();
			writer.close();
			System.out.println("저장완료");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
