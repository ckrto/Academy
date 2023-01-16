package bank;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import DAO_VO.AccountInfoDAO;
import DAO_VO.AccountInfoVO;
import DAO_VO.PasswordDAO;
import DAO_VO.PasswordVO;

public class Read {
	public static void execute(){
		
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("￦0,000원");
				
		AccountInfoDAO idao = new AccountInfoDAO();
		PasswordDAO pdao= new PasswordDAO();
		PasswordVO pvo= new PasswordVO();
		AccountInfoVO ivo = new AccountInfoVO();
		
		String accountNo=""; 		//계좌번호
		String password=""; //비밀번호
		String balance =""; //포멧 적용한 잔액
		
		boolean run = true; //계좌 조회를 취소할때까지 반복될 while의 조건
		boolean run1 = true;//계좌번호 입력 확인 조건
		boolean run2 = true;//비밀번호 입력 확인
		
		while(run) {
			System.out.println("계좌 조회");	//프로그램 안내
			
			do {
				//계좌번호 입력 및 미입력 확인
				if(accountNo.equals("")) {
					System.out.print("계좌번호 : ");
					accountNo= in.nextLine();
					if(accountNo.equals("")) {
						System.out.println("계좌번호를 입력하세요.");
					}
					else {
						run1=false;
					}
				}
				//비밀번호 입력 및 미입력 확인
				if(password.equals("")) {
					System.out.print("비밀번호 : ");
					password= in.nextLine();
					if(password.equals("")) {
						System.out.println("비밀번호를 입력하세요.");
					}
					else {
						run2=false;
					}
				}
			}while(run1==true || run2==true);
			
			//해당 계좌번호의 사용자 존재 여부 확인
			pvo=pdao.read(accountNo);	//해당 계좌번호의 정보 읽어오기
			if(pvo.getAccountName()==null) {
				System.out.println("존재하지 않는 계좌번호입니다.");
				accountNo="";	//계좌번호 초기화해서 while 다시 실행하고 다시 입력하도록
			}
			else {	//입력한 계좌번호의 계좌가 존재시 해당 계좌의 비밀번호와 일치하는지 판단
				if(!pvo.getPassword().equals(password)) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					password="";
				}//비밀번호 미일치시 비밀번호 초기화해서 while 다시 실행하고 다시 입력하도록 
				else {	//입력한 계좌번호 계좌의 비밀번호와 일치할 시 
					System.out.println("계좌주\t타입\t상품명\t\t계좌개설일\t\t잔액");
					balance = df.format(pvo.getBalance());
					ivo=idao.read(pvo.getAccountTypeNo());
					System.out.printf("%s\t%s\t%s\t%s\t%s",pvo.getAccountName(),ivo.getAccountType(),ivo.getAccountTypeName(),pvo.getAccountDate(),balance);
					break;	//내용 출력하고 프로그램 종료하도록
				}
			}		
		}//w
		
	}//m
}//c
