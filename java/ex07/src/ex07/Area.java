package ex07;

public class Area {
	//필드
	static final double PI = 3.141592;
	
	//기본 생성자
	Area(){
		
	}
	
	//메서드
	public int area(int x) {
		int area = x*x;
		return area;
	}
	public int area(int x, int y) {
		int area = x * y;
		
		return area;
	}
	public double area(double x) {
		double area = x * x * PI;
		
		return area;
	}
}
