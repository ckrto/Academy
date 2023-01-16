package ex13;

import java.util.ArrayList;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in); //입력을 받기 위해 Scanner 변수를 sc로 불러와서 사용
		StudentVO vo = new StudentVO(); // StudentVO에 들어있는 학생 정보를 사용하기 위해 vo로 선언하여 사용
		StudentDAO dao = new StudentDAO(); // 학생 관련 인터페이스를 사용하기위해 dao로 선언하여 사용
		ScoreVO svo = new ScoreVO(); // ScoreVO에 들어있는 학생들의 점수 정보를 사용하기 위해 svo로 선언하여 사용
		ScoreDAO sdao = new ScoreDAO(); // 학생들의 점수 관련 인터페이스를 사용하기 위해 dao로 선언하여 사용
		
		int newNo; // 마지막번호를 가져오는 dao 클래스의 getMaxNo 매서드를 활용하기 위해 int 변수 newNo를 선언하여 사용
		
		String menu; // 사용자가 실행할 작업을 입력하여 switch~case 문을 사용하기 위해 String 변수 menu를 선언하여 사용
		String sno; // 사용자가 학생을 조회 및 등록을 위해 사용하는 String 변수
		
		boolean isNumber = false; // 숫자가 맞는지 체크하기위해 사용할 boolean 변수 isNumber을 선언
		boolean run = true; // while문을 실행하기 위해 boolean 변수 run을 선언
		
		while(run) {
			System.out.println("--------------------------------------------------------");
			System.out.println("1.학생등록 | 2.학생조회 | 3.학생목록 | 4.성적등록 | 0.종료");
			System.out.println("--------------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextLine(); // 실행할 작업을 선택하여 값을 저장
			
			System.out.println("");
			
			switch(menu) { // 변수 menu에 저장된 값에 따라 다른 case 문을 실행 
			case "1" : // 학생 등록을 위해 사용되는 case문
				vo = new StudentVO(); // StudentVO를 사용하기 위해 vo 인스턴스(객체)를 생성
				newNo = dao.getMaxNo() + 100; // StudentDAO에 들어있는 getMaxNo 매서드에 100을 더해 newNo에 저장
				
				System.out.println("귀하의 학번은 " + newNo + "번 입니다.");
				vo.setNo(newNo); // 학번은 사용자가 정하는 것이 아닌 시스템이 자동으로 지정해줌
				
				System.out.print("이름을 입력해주세요. : ");
				vo.setName(sc.nextLine()); // 사용자가 입력한 이름을 vo.setName에 저장
				
				System.out.print("주소를 입력해주세요. : ");
				vo.setAddress(sc.nextLine()); // 사용자가 입력한 주소를 vo.setAddress에 저장
				
				System.out.println("");
				
				dao.insert(vo); // StudentDAO에 있는 insert 매서드에 vo값을 보내고 insert 매서드를 실행
				
				System.out.println("새로운 학생이 등록되었습니다.\n");
				break;
			case "2" : 
				isNumber = false; // 학번을 숫자로 입력했는지 확인할 변수
				do {
					System.out.print("조회하실 학생의 학번을 입력해주세요. : ");
					sno = sc.nextLine(); // 사용자가 조회한 학번 정보를 sno 변수에 저장
					System.out.println("");
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?"); // 정규식을 활용하여 사용자가 조회한 학번 정보를 저장한 sno와 비교
					if(isNumber == false) { // 숫자가 아니면 if문을 실행
						System.out.println("학번을 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false); // do~while문으로 입력한 정보가 숫자일때까지 while문을 실행
				
				vo = dao.read(Integer.parseInt(sno)); // StudentDAO에 있는 read 매서드에 String sno값을 integer.parsInt를 사용해 변환하여 전달
				
				if(vo.getName() == null) { // dao.read를 실행하고 sno값과 vo에 저장된 name을 비교하여 null 값일때 if문 실행
					System.out.println("조회하신 학생이 존재하지 않습니다.");
				} else {
					System.out.println("학번\t이름\t주소");
					System.out.printf("%s\t%s\t%s\n", vo.getNo(), vo.getName(), vo.getAddress());
					
					ArrayList<ScoreVO> sarray = sdao.list(Integer.parseInt(sno)); // 입력된 sno값과 맞는 사람의 점수를 출력해주기 위해 arraylist와 sdao에 있는 list 매서드를 사용하여 검색한 사람의 점수 정보를 sarray에 저장
					
					System.out.println("\n" + vo.getName() + "님의 성적입니다.");
					System.out.println("-----------------------------------------------------");
					System.out.println("유형\t국어\t영어\t수학\t총점\t평균");
					for(ScoreVO s : sarray) { // sarray에 ScoreVO에 있는 점수정보를 저장
						s.print(); // sarray에 저장된 점수 정보를 출력
					}
				}
				
				System.out.println("");
				break;
			case "3" :
				ArrayList<StudentVO> array = dao.list(); // array에 StudentDAO의 list 매서드를 저장
				System.out.println("학번\t이름\t주소");
				for(StudentVO v : array) { // StudentVO에 값을 하나씩 꺼내 출력
					System.out.printf("%s\t%s\t%s\n", v.getNo(), v.getName(), v.getAddress());	
				}
				System.out.printf("\n등록 되어있는 전체 학생 수는 %d명 입니다.\n", array.size()); // size()를 사용하여 array의 크기(학생 수)를 출력
				System.out.println("");
				break;
			case "4" :
				svo = new ScoreVO(); // ScoreVO를 사용하기 위해 svo 인스턴스 생성
				isNumber = false;
				do {
					System.out.print("성적을 입력하실 학생의 학번을 입력해주세요. : ");
					sno = sc.nextLine();
					System.out.println("");
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("학번을 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				svo.setNo(Integer.parseInt(sno));
				
				svo = sdao.read(Integer.parseInt(sno));
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("조회하신 학생이 존재하지 않습니다.");
				} 
				else {
					System.out.print("시험 유형을 입력해주세요. : ");
					svo.setType(sc.nextLine());
					
					isNumber = false;
					String kor = "";
					do {
						System.out.print("국어 점수를 입력해주세요. : ");	
						kor = sc.nextLine();
						
						isNumber = kor.matches("-?\\d+(\\.\\d+)?");
						if(isNumber == false) {
							System.out.println("국어 점수를 숫자로 입력해주세요.\n");
						}
					} while(isNumber == false);
					svo.setKor(Integer.parseInt(kor));
					
					isNumber = false;
					String eng = "";
					do {
						System.out.print("영어 점수를 입력해주세요. : ");
						eng = sc.nextLine();
						
						isNumber = eng.matches("-?\\d+(\\.\\d+)?");
						if(isNumber == false) {
							System.out.println("영어 점수를 숫자로 입력해주세요.\n");
						}
					} while(isNumber == false);
					svo.setEng(Integer.parseInt(eng));
					
					isNumber = false;
					String mat = "";
					do {
						System.out.print("수학 점수를 입력해주세요. : ");
						mat = sc.nextLine();
						
						isNumber = mat.matches("-?\\d+(\\.\\d+)?");
						if(isNumber == false) {
							System.out.println("수학 점수를 숫자로 입력해주세요.\n");
						}
					} while(isNumber == false);
					svo.setMat(Integer.parseInt(mat));
					
					System.out.println("");
					
					sdao.insert(svo); // 입력받은 점수들(svo)를 ScoreDAO의 insert 매서드를 실행하여 저장
					
					System.out.println("새로운 성적이 등록되었습니다.\n");
				}
				
				break;
			case "0" :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
		}
	}
}
