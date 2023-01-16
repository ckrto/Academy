package ex06;

import java.util.ArrayList;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Car>  cars = new ArrayList<>();
		
		Car car = new Car("기아", "K7", 250);
		cars.add(car);
		
		car = new Car("기아", "K5", 200);
		cars.add(car);
		
		String company;
		String model;
		String smodel;
		String dmodel;
		String umodel;
		String ncompany;
		String nmodel;
		String sel;
		String nmaxSpeed;
		
		int maxSpeed;
		int menu;
		
		boolean find = false;
		boolean run = true;
		
		System.out.println("안녕하세요 자동차 관리 시스템입니다.");
		
		while(run) {
			System.out.println("아래의 항목중 한가지를 선택해주세요.");
			System.out.println("-------------------------------");
			System.out.println("1.등록 | 2.검색 | 3.목록 | 4.삭제 | 5.수정 | 0.종료");
			System.out.println("-------------------------------");
			System.out.print("선택할 항목 : ");
			menu = sc.nextInt(); sc.nextLine();
			
			System.out.println("");
			
			switch(menu) {
			case 1 :
				System.out.print("회사명을 입력해주세요 : ");
				company = sc.next(); sc.nextLine();
				
				System.out.print("모델명을 입력해주세요 : ");
				model = sc.next(); sc.nextLine();
				
				System.out.print("자동차의 최대 속도를 입력해주세요 : ");
				maxSpeed = sc.nextInt(); sc.nextLine();
				
				car = new Car(company, model, maxSpeed);
				cars.add(car);
				
				System.out.println("등록된 차량의 수는 " + cars.size() + " 입니다. \n");
				break;
			case 2 :
				System.out.print("검색하고 싶으신 모델명을 입력하세요 : ");
				smodel = sc.next(); sc.nextLine();
				
				for(Car c:cars) {
					if(smodel.equals(c.model)) {
						find = true;
						c.infoPrint();
						System.out.println("");
					}
				}
				if(find == false) {
					System.out.println("등록된 차량이 아닙니다.");
				}
				break;
			case 3 :
				System.out.println("회사명\t모델명\t최대속도\t");
				for(Car c:cars) {
					System.out.println(c.company + "\t" + c.model + "\t" + c.maxSpeed + "\t");
				}
				System.out.println("");
				break;
			case 4 :
				System.out.print("삭제할 차량의 모델명을 입력해주세요. : ");
				dmodel = sc.next(); sc.nextLine();
				
				for(Car c:cars) {
					if(dmodel.equals(c.model)) {
						find = true;
						c.infoPrint();
						
						System.out.print("정말 삭제하실 건가요(예 : Y, 아니오 : N)? : ");
						sel = sc.next(); sc.nextLine();
						
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							cars.remove(c);
							System.out.println("삭제가 완료되었습니다.\n");
							break;
						}
						else if (sel.equals("N") || sel.equals("n") || sel.equals("ㅜ")) {
							System.out.println("취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 차량이 아닙니다.");
				}
				break;
			case 5 :
				System.out.print("수정할 차량의 모델명을 입력해주세요. : ");
				umodel = sc.next(); sc.nextLine();
				
				for(Car c:cars) {
					if(umodel.equals(c.model)) {
						find = true;
						System.out.print("회사명 : " + c.company + "을(를) 무엇으로 고치실건가요? : ");
						ncompany = sc.nextLine();
						
						System.out.print("모델 : " + c.model + "을(를) 무엇으로 고치실건가요? : ");
						nmodel = sc.nextLine();
						
						System.out.print("최대속도 : " + c.maxSpeed + "을(를) 무엇으로 고치실건가요? : ");
						nmaxSpeed = sc.nextLine();
						
						System.out.println("회사 : " + ncompany);
						System.out.println("모델 : " + nmodel);
						System.out.println("최대속도 : " + nmaxSpeed);
						System.out.println("");
						System.out.print("위의 값으로 수정하실건가요 (예 : Y , 아니요 : N)? : ");
						sel = sc.next(); sc.nextLine();
						
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							if(!ncompany.equals("")) {
								c.company = ncompany;
							}
							if(!nmodel.equals("")) { 
								c.model = nmodel;
							}
							if(!nmaxSpeed.equals("")) {
								c.maxSpeed = Integer.parseInt(nmaxSpeed);
							}
							System.out.println("");
							c.infoPrint();
							System.out.println("수정이 완료되었습니다.\n");
							break;
						}
						else if (sel.equals("N") || sel.equals("n") || sel.equals("ㅜ")) {
							System.out.println("취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 차량이 아닙니다.");
				}
				
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
				break;
			}
		}
	}
}
