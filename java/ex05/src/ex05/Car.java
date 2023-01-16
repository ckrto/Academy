package ex05;

public class Car {
	// 클래스는 기본적으로 필드, 속성, 생성자, 메소드를 가진다.
	// 필드, 속성
	String nation = "대한민국";
	String company;
	String model;
	String color;
	int maxSpeed;
	
		
	public Car() { // 기본생성자
			
	}
	
	//매개변수가 다르면 같은 생성자라도 똑같은 매서드명을 가지고 생성할 수 있다 = 오버로딩
	//this는 필드를 가리킨다.
	public Car(String model, String color, int maxSpeed) {
		super(); // 기본생성자를 가지고 밑의 값을 넣는다.
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}

	public Car(String company, String model, String color, int maxSpeed) { // 매개변수
		super();
		this.company = company;
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}

	public void carPrint() { // 메소드
		System.out.println("국가 : " + nation);
		System.out.println("회사 : " + company);
		System.out.println("모델 : " + model);
		System.out.println("색상 : " + color);
		System.out.println("최대속도 : " + maxSpeed);		
	}
}