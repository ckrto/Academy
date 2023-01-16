package ex06;

public class Car {
	String company;
	String model;
	int maxSpeed;
	
	public Car() {
		
	}
	
	public Car(String company) {
		this.company = company;
	}
	
	public Car(String company, String model, int maxSpeed) {
		super();
		this.company = company;
		this.model = model;
		this.maxSpeed = maxSpeed;
	}

	public void infoPrint() {
		System.out.println("회사 : " + company);
		System.out.println("모델 : " + model);
		System.out.println("최대속도 : " + maxSpeed);
	}
}
