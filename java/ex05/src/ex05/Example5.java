package ex05;

public class Example5 {
	public static void execute() {
		//현대, 제네시스, 검정, 250 자동차 객체를 생성
				Car car1 = new Car(); // 기본생성자, 새로운객체를 만들어 car1에게 넣어줌.
				car1.company = "현대";
				car1.model = "제네시스";
				car1.color = "검정색";
				car1.maxSpeed = 250;
				// car1.carPrint();
				System.out.println("국가 : " + car1.nation);
				System.out.println("회사 : " + car1.company);
				System.out.println("모델 : " + car1.model);
				System.out.println("색상 : " + car1.color);
				System.out.println("최대속도 : " + car1.maxSpeed);
				System.out.println("-----------------------------------");
			
				//기아, K5, 흰색, 250 자동차 객체를 생성
				Car car2 = new Car();
				car2.company = "기아";
				car2.model = "K5";
				car2.color = "흰색";
				car2.maxSpeed = 250;
				car2.carPrint();
				
				System.out.println("-----------------------------------");
				//쌍용, 티볼리, 파란색, 두번째 생성자로 객체생성
				Car car3 = new Car("티볼리", "파란색", 200);
				car3.carPrint();
				
				System.out.println("-----------------------------------");
				Car car4 = new Car("현대", "그랜저", "은색", 300);
				car4.carPrint();
	}
}
