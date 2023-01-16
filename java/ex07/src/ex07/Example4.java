package ex07;

import hankook.SnowTire;

public class Example4 {
	public static void execute() {
		System.out.println("********** 접근 제한자 **********");
		// 접근제한자
		// 아무것도 없으면 디폴트 = 같은 패키지 안에서만 사용 가능
		// public(공용) = 프로젝트 전체에서 사용 가능
		// private = 클래스안에서만 사용 가능
		
		Tire t1 = new Tire();
		
		t1.print();
		System.out.println("색상 : " + t1.color);
		System.out.println("사이즈 : " + t1.size);
		
		SnowTire t2 = new SnowTire();
		t2.setColor("빨간색");
		t2.setSize(18);
		t2.setLife(5);
		System.out.println("색상 : " + t2.getColor());
		System.out.println("사이즈 : " + t2.getSize());
		System.out.println("수명 : " + t2.getLife());
		t2.print();
		t2.printInfo();
	}
}
