package textgame;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String name;
		int age;
		int choice;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("게임 시작전에 이름과 나이를 입력해주세요.");
		System.out.println("이름을 입력하세요.");
		name = sc.next();
		System.out.println("나이를 입력하세요.");
		age = sc.nextInt();
		
		if(age > 30)
		{
			System.out.println("? 지금와서 뭘하겠다고?? 인생 망했네");
		}
		
		System.out.println("203x년 어느날 취직을 하지못한 " + name + "은(는) 새로운 취직자리를 찾으러 일자리 구인구직 사이트에 접속한다.");
		System.out.println("구인구직 사이트에 접속한 " + name + "은(는) 꿀알바라고 적혀있는 한 일자리에 눈이 간다.");
		System.out.println("그 일자리는 일급 300만원을 5주동안 준다는 임상실험 알바...");
		System.out.println("학자금 대출과 비트코인, 주식으로 잃은 돈은 벌써 1억여원... "+ name + "의 선택은...");
		System.out.println("이 일자리를 선택하시겠습니까?");
		System.out.println("1. 예             2. 아니요");
		choice = sc.nextInt();
		
		if(choice == 1)
		{
			System.out.println("그래! 어차피 빚에 헐떡이고 죽을빠엔 이런 알바라도 해서 대한민국에 도움이 되는게 나을거야!");
		}
		else if(choice == 2)
		{
			System.out.println(name + "은(는) 그렇게 빚에 헐떡이다 사망하게 되었다....");
			System.out.println("Bad Ending...");
		}
		
		System.out.println("xx병원의 임상실험실");
		System.out.println(name + "씨 옷 갈아입고 여기서 설문지 작성하실게요");
		
		System.out.println("옷을 갈아입은 후");
		System.out.println("여기 설문지 드릴테니 한번 작성해보세요");
		System.out.println("신장이 2쪽 다 있는가");
		System.out.println("간을 이식받거나 해준 적이 있는가");
		System.out.println("심장질환을 가지고 있는가");
		System.out.println("알레르기가 있는가 있다면 어떤 알레르기가 있는가");
		
	}

}
