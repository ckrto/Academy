package ex15;
import java.util.*;

public class Example1 {
	public static void execute() {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		DeptDAO dao= new DeptDAO();
		
		System.out.println("\n****************** 학 과 코 드 관 리 **************");
		while(run) {
			System.out.println("\n===============================================");
			System.out.println("1.코드등록 | 2.코드목록 | 3.코드수정 | 4.코드삭제 | 0.종료");
			System.out.println("===============================================");
			int menu=Main.input("선택");
			switch(menu) {
			case 0:
				System.out.println("메인페이지로 이동합니다.");
				run=false;
			case 1://코드등록
				String mcode=dao.getMaxCode();
				int newCode=Integer.parseInt(mcode) + 10; //String->int
				System.out.println("학과코드>" +  newCode);
				System.out.print("학과이름>");
				String codeName=s.nextLine();
				
				DeptVO vo=new DeptVO();
				vo.setCode(String.valueOf(newCode)); //int->String
				vo.setDname(codeName);
				dao.insert(vo);
				System.out.println("새로운 학과가 등록되었습니다.");
				break;
			case 2://코드목록
				ArrayList<DeptVO> array=dao.list();
				for(DeptVO v:array) {
					System.out.printf("%s\t%s\n",v.getCode(),
										v.getDname());
				}
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("0~4메뉴를 선택하세요.");
			}
		}
	}
}
