package ex07;

import hankook.SnowTire;
import kumho.*;

public class Example3 {
	public static void execute() {
		System.out.println("********** 패키지 연습 **********");
		
		kumho.Tire t1 = new kumho.Tire();
		t1.print();
		
		SnowTire t2 = new SnowTire();
		t2.print();
		
		hankook.Tire t3 = new hankook.Tire();
		t3.print();
		
		BigWideTire t4 = new BigWideTire();
		t4.print();
	}
}
