package ex08;

public class Example1 {
	public static void execute() {
		System.out.println("********** 상 속 연 습 **********");
		
		People p1 = new People();
		p1.setJno("123456-1234567");
		p1.setName("홍길동");
		p1.print();
		System.out.println("");
		
		People p2 = new People("456789-1234567", "심청이");
		p2.print();
		System.out.println("");
		
		Student s1 = new Student();
		s1.setJno("000001-0000001");
		s1.setName("강감찬");
		s1.setSno("88001");
		System.out.println("주민번호 : " + s1.getJno());
		System.out.println("이름 : " + s1.getName());
		System.out.println("학번 : " + s1.getSno());
		System.out.println("");
		
		Student s2 = new Student("0000002-0000000", "이순신", "87001");
		s2.print();
	}
}
