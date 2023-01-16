package ex13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ScoreDAO {
	
	File file = new File("c:/data/java/ex13/score.txt");
	
	public void insert(ScoreVO svo) { // 학생의 새로운 점수 정보를 저장하기 위한 매서드
		try { // try ~ catch문을 사용해 예외처리를 가능하게 도움
			FileWriter writer = new FileWriter(file, true); // filewriter 인스턴스를 새롭게 생성하고 true값으로 지정해 파일에 새로운 정보를 추가
			writer.write(svo.getNo() + "|" + svo.getType() + "|" + svo.getKor() + "|" + svo.getEng() + "|" + svo.getMat() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("성적 입력 : " + e.toString());
		}
	}
	
	public ScoreVO read(int no) { // 학생의 점수 정보를 읽기 위한 매서드
		ScoreVO svo = new ScoreVO(); // ScoreVO를 사용하기 위해 새로운 인스턴스를 생성
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file)); // BufferedReader(띄어쓰기와 엔터를 경계로 입력값을 받기때문에 가공할 필요가 없음)를 사용하여 file의 값을 읽음
			String line = ""; // line 변수를 선언
			while((line = bf.readLine()) != null) { // 선언한 line 변수와 file에서 읽은 값이 null값이 아닐때 while 문을 실행
				StringTokenizer st = new StringTokenizer(line, "|"); // 새로운 StringTokenizer(문자열을 우리가 지정한 구분자로 문자열을 쪼개주는 클래스) 인스턴스를 선언하고 line의 값을 "|"로 나눠줌
				svo.setNo(Integer.parseInt(st.nextToken()));
				if(no == svo.getNo()) { // 검색된 학번을 불러와야하므로 Example1에서 보낸 no값과 vo에 저장된 no값을 비교하여 맞으면 if문을 실행
					svo.setType(st.nextToken());
					svo.setKor(Integer.parseInt(st.nextToken()));
					svo.setEng(Integer.parseInt(st.nextToken()));
					svo.setMat(Integer.parseInt(st.nextToken()));
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("성적 검색 : " + e.toString());
		}
		return svo; // svo값을 다른곳에서도 사용하기위해 return으로 반환
	}
	
	public ArrayList<ScoreVO> list(int no) { // 모든 학생의 모든 점수 정보를 읽기 위한 매서드
		ArrayList<ScoreVO> sarray = new ArrayList<ScoreVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				if(lineNo == no) {
					ScoreVO svo = new ScoreVO();
					svo.setNo(lineNo);
					svo.setType(st.nextToken());
					svo.setKor(Integer.parseInt(st.nextToken()));
					svo.setEng(Integer.parseInt(st.nextToken()));
					svo.setMat(Integer.parseInt(st.nextToken()));
					sarray.add(svo);
				}
			}
		} catch(Exception e) {
			System.out.println("성적목록 : " + e.toString());
		}
		return sarray; // sarray 값을 다른곳에서도 사용하기위해 return으로 반환
	}
}
