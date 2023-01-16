package hankook;

public class SnowTire { //카멜표시법 (자바 빈) 
	private String color = "검정색";
	private int life = 3;
	private int size = 20;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void print() {
		System.out.println("한국타이어의 스노우타이어 입니다.");
	}
	
	public void printInfo() {
		System.out.println("색상 : " + color);
		System.out.println("사이즈 : " + size);
	}
}
