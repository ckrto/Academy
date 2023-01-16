/*
 * package ex14;
 * 
 * import java.util.ArrayList;
 * 
 * public class EAAAA { code = Input.input("캠핑장 번호를 입력해주세요. : ");
 * 
 * vo = cdao.read(code);
 * 
 * if(vo.getName() == null) { System.out.println("해당캠핑장이 없습니다.\n"); break; }
 * System.out.println("캠핑장 이름 : " + vo.getName());
 * System.out.println("-----------------------------------");
 * 
 * ArrayList<String> farray = fdao.list(code); ArrayList<String> check = new
 * ArrayList<>();
 * 
 * for(int i = 0; i < facility.length; i++) { isFind = false;
 * 
 * for(String f : farray) { if(facility[i].equals(f)) { isFind = true; } }
 * if(isFind == true) { check.add("O"); } else { check.add("X"); }
 * System.out.printf("[%d] %s(%s)\n", i, facility[i], check.get(i));
 * 
 * } System.out.println("---------------------------------------------");
 * 
 * sel = Input.input("\n등록하실 시설물을 선택해주세요. : "); if(check.get(sel).equals("O")) {
 * System.out.println("이미 등록된 시설물 입니다."); break; } fdao.insert(code,
 * facility[sel]);
 * 
 * System.out.println(facility[sel] + "이(가) 등록되었습니다.");
 * 
 * System.out.println(""); }
 */