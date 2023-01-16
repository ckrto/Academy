package ex13;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		DecimalFormat df = new DecimalFormat("#,###만원"); // 다양한 숫자 데이터를 자신의 원하는 형식으로 나타낼 수 있게 도와주는 클래스
		Scanner sc = new Scanner(System.in);
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 여러가지의 시간표시 포맷을 담고 있는 클래스
		SaleDAO sdao = new SaleDAO();
		SaleVO svo = new SaleVO();
		
		int newNo;
		
		String sno;
		String menu;
		String qnt;
		String price;
		
		boolean isNumber = false;
		boolean run = true;
		
		while(run) {
			System.out.println("------------------------------------------------------------");
			System.out.println("1.상품등록 | 2.상품조회 | 3.상품목록 | 4.매출등록 | 0.종료");
			System.out.println("------------------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요 : ");
			menu = sc.nextLine();
			
			System.out.println("");
			
			switch(menu) {
			case "1" : 
				vo = new ProductVO();
				newNo = dao.getMaxNo() + 100;
				
				System.out.println("해당 상품의 상품 번호는 " + newNo + "번 입니다.");
				vo.setNo(newNo);
				
				System.out.print("상품명을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				
				isNumber = false;
				do {
					System.out.print("상품의 단가를 입력해주세요. : ");
					price = sc.nextLine();
					
					isNumber = price.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				vo.setPrice(Integer.parseInt(price));
				
				isNumber = false;
				do {
					System.out.print("상품의 수량을 입력해주세요. : ");
					qnt = sc.nextLine();
					
					isNumber = qnt.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				vo.setQnt(Integer.parseInt(qnt));
			
				System.out.println("");
				
				dao.insert(vo);
				
				System.out.println("새로운 상품이 등록되었습니다.\n");
				break;
			case "2" :
				isNumber = false;
				do {
					System.out.print("검색하실 상품의 상품 번호를 입력하세요. : ");
					sno = sc.nextLine();
					System.out.println("");
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("상품 번호를 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("상품이 존재하지 않습니다.");
				} else {
					int sum = vo.getPrice() * vo.getQnt();
					String strSum = df.format(sum);
					String dfprice = df.format(vo.getPrice());
					System.out.println("번호\t상품명\t판매단가\t재고수량\t금액");
					System.out.printf("%d\t%s\t%s\t%d개\t%s\n", vo.getNo(), vo.getName(), dfprice, vo.getQnt(), strSum);
					
					ArrayList<SaleVO> sarray = sdao.list(Integer.parseInt(sno));
					if(sarray.size()==0) {
						System.out.println("판매한 상품이 없습니다.");
					} else {
						System.out.println("");
						System.out.println(vo.getName() + "의 매출 현황입니다.");
						System.out.println("---------------------------------------------------");
						System.out.println("번호\t날짜\t판매량");
						for(SaleVO s : sarray) {
							System.out.printf("%d\t%s\t%d\n", s.getNo(), s.getDate(), s.getSale());
						}
					}
				}
				System.out.println("");
				break;
			case "3" :
				ArrayList<ProductVO> array = dao.list();
				System.out.println("번호\t상품명\t판매단가\t재고수량\t금액");
				for(ProductVO v : array) {
					int sum = v.getPrice() * v.getQnt();
					String strSum = df.format(sum);
					String dfprice = df.format(v.getPrice());
					System.out.printf("%d\t%s\t%s\t%d개\t%s\n", v.getNo(), v.getName(), dfprice, v.getQnt(), strSum);
				}
				System.out.printf("\n등록되어 있는 전체 상품의 수는 %d개 입니다.\n", array.size());
				System.out.println("");
				break;
			case "4" :
				vo = new ProductVO();
				
				do {
					System.out.print("매출 현황을 등록하실 상품의 상품 번호를 입력하세요. : ");
					sno = sc.nextLine();
					System.out.println("");
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(isNumber == false) {
						System.out.println("상품 번호를 숫자로 입력해주세요.\n");
					}
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("상품이 존재하지 않습니다.");
				} else {
					ArrayList<SaleVO> sarray = sdao.list(Integer.parseInt(sno));
					if(sarray.size() == 0) {
						System.out.println("판매한 상품이 없습니다.");
					} else {
						System.out.println("번호\t날짜\t판매량");
						for(SaleVO s : sarray) {
							System.out.printf("%d\t%s\t%d\n", s.getNo(), s.getDate(), s.getSale());
						}
					}
					System.out.println("");
					
					System.out.println("상품명 : " + vo.getName());
					svo.setNo(Integer.parseInt(sno));
					
					System.out.println("");
					
					svo.setDate(sdf.format(new Date()));
					
					System.out.print("상품의 판매량을 입력해주세요 : ");
					String sale = sc.nextLine();
					
					System.out.println("");
					
					dao.update(Integer.parseInt(sno), Integer.parseInt(sale));
					
					svo = new SaleVO();
					svo.setNo(Integer.parseInt(sno));
					svo.setDate(sdf.format(new Date()));
					svo.setSale(Integer.parseInt(sale));
					sdao.insert(svo);
					
					System.out.println("새로운 매출 현황이 등록되었습니다.");
				}
				
				System.out.println("");
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
/*
   //재고수량 - 판매수량
   public void update(int no, int qnt) {
      try {
         BufferedReader reader=new BufferedReader(new FileReader(file));
         String line="";
         String lines="";
         while((line=reader.readLine()) != null) {
            StringTokenizer st=new StringTokenizer(line, "|");
            int lineNo=Integer.parseInt(st.nextToken());
            if(no==lineNo) {
               ProductVO vo=new ProductVO();
               vo.setNo(lineNo);
               vo.setName(st.nextToken());
               vo.setPrice(Integer.parseInt(st.nextToken()));
               vo.setQnt(Integer.parseInt(st.nextToken()));
               vo.setQnt(vo.getQnt()-qnt);
               
               lines=lines + vo.getNo()+"|"+vo.getName()
                     +"|"+vo.getPrice()+"|"+vo.getQnt()+"\n";
            }else {
               lines = lines + line;
            }
         }
         //새로운 파일저장
         FileWriter writer=new FileWriter(file, false);
         writer.write(lines);
         writer.flush();
         writer.close();
      }catch(Exception e) {
         System.out.println("마지막 상품코드:" + e.toString());
      }
   }
 */