package ex12;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("#,###원");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AccountVO vo = null;
		AccountDAO dao = new AccountDAOImpl();
		TradeDAO tdao = new TradeDAO();
		TradeVO tvo = null;
		
		int menu;
		int sno;
		int newNo;
		
		String ssno;
		String balance;
		
		boolean run = true;
		boolean isNumber = false;
		
		
		while(run) {
			System.out.println("-----------------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌조회 | 3.입금 | 4.출금 | 5.계좌목록 | 0.종료");
			System.out.println("-----------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 : 
				newNo = dao.getLastNo() + 100;
				System.out.println("귀하의 계좌번호는 " + newNo + " 입니다.");
				vo = new AccountVO();
				vo.setNo(newNo);
				
				System.out.print("이름을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				
				isNumber = false;
				do {
					System.out.print("초기입금액을 입력해주세요. : ");
					balance = sc.nextLine();
					isNumber = balance.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.print("초기입금액을 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				System.out.println("");
				
				vo.setBalance(Integer.parseInt(balance));
				dao.insert(vo);
				
				tvo = new TradeVO();
				tvo.setNo(newNo);
				tvo.setDate(sdf.format(new Date()));
				tvo.setType("입금");
				tvo.setAmount(Integer.parseInt(balance));
				tdao.insert(tvo);
				
				System.out.println("새로운 계좌가 생성되었습니다.\n");
	
				break;
			case 2 :
				isNumber = false;
				
				do {
					System.out.print("조회하실 계좌번호를 입력해주세요. : ");
					ssno = sc.nextLine();
					System.out.println("");
					isNumber = ssno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("계좌번호를 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				
//				System.out.print("조회하실 계좌번호를 입력해주세요. : ");
//				sno = sc.nextInt(); sc.nextLine();
//				System.out.println("");
//				0
				vo = dao.read(Integer.parseInt(ssno));
				
				if(vo.getName() == null) {
					System.out.println("조회하신 계좌가 존재하지 않습니다.\n");
				} else {
					System.out.println("계좌번호\t계좌주명\t잔액");
					balance = df.format(vo.getBalance());
					System.out.printf("%d\t%s\t%s\n", vo.getNo(), vo.getName(), balance);
					ArrayList<TradeVO> tarray = tdao.list(Integer.parseInt(ssno));
					System.out.println("------------------------------------------");
					for(TradeVO t : tarray) {
						String amount = df.format(t.getAmount());
						System.out.printf("%s\t%s\t%s\n", t.getDate(), t.getType(), amount);
					}
				}
				System.out.println("");
				break;
			case 3 : 
				isNumber = false;
				ssno = "";
				do {
					System.out.print("입금을 진행하실 계좌번호를 입력해주세요. : ");
					ssno = sc.nextLine();
					System.out.println("");
					isNumber = ssno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("계좌번호를 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(ssno));
				if(vo.getName() == null) {
					System.out.println("입금할 계좌가 존재하지 않습니다.");
				}
				else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("현재잔액 : " + vo.getBalance());
					System.out.println("");
					System.out.print("입금할 금액을 입력해주세요 : ");
					String price = sc.nextLine();
					
					System.out.println("");
					dao.trade(Integer.parseInt(ssno), Integer.parseInt(price));
					
					System.out.println("------------------------------------------");
					tvo = new TradeVO();
					tvo.setNo(Integer.parseInt(ssno));
					tvo.setDate(sdf.format(new Date()));
					tvo.setType("입금");
					tvo.setAmount(Integer.parseInt(price));
					tdao.insert(tvo);
					
					System.out.println("입금이 완료되었습니다.");
				}
				System.out.println("");
				break;
			case 4 : 
				isNumber = false;
				ssno = "";
				do {
					System.out.print("출금을 진행하실 계좌번호를 입력해주세요. : ");
					ssno = sc.nextLine();
					System.out.println("");
					isNumber = ssno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("계좌번호를 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(ssno));
				if(vo.getName() == null) {
					System.out.println("출금할 계좌가 존재하지 않습니다.");
				}
				else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("현재잔액 : " + vo.getBalance());
					System.out.println("");
					System.out.print("출금할 금액을 입력해주세요 : ");
					String price = sc.nextLine();
					
					System.out.println("");
					dao.trade(Integer.parseInt(ssno), (Integer.parseInt(price) * -1));

					System.out.println("------------------------------------------");
					tvo = new TradeVO();
					tvo.setNo(Integer.parseInt(ssno));
					tvo.setDate(sdf.format(new Date()));
					tvo.setType("출금");
					tvo.setAmount(Integer.parseInt(price));
					tdao.insert(tvo);
					System.out.println("출금이 완료되었습니다.");
				}
				System.out.println("");
				break;
			case 5 :
				ArrayList<AccountVO> array = dao.list();
				System.out.println("계좌번호\t계좌주명\t잔액");
				for(AccountVO v : array) {
					balance = df.format(v.getBalance());
					System.out.printf("%d\t%s\t%s\n", v.getNo(), v.getName(), balance);
				}
				System.out.println("");
				System.out.println("전체 계좌수 : " + array.size() + "\n");
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
