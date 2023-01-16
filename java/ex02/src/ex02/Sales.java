package ex02;

public class Sales {
	public static void sales() {
		
		String name1 = "냉장고", name2 = "세탁기", name3 = "합계";
		int pri1 = 300, pri2 = 150;
		int qua1 = 3, qua2 =  4;
		int amo1 = 900, amo2 = 600;
		int tot1 = qua1 + qua2, tot2 = amo1 + amo2;
		
		System.out.println("********** 매출 현황 **********");
		System.out.println(" 상품명" + "\t" + "단가" + "\t" + "수량" + "\t" + "금액");
		System.out.println(" " + name1 + "\t" + pri1 + "\t" + qua1 + "\t" + amo1);
		System.out.println(" " + name2 + "\t" + pri2 + "\t" + qua2 + "\t" + amo2);
		System.out.println("-----------------------------");
		System.out.println(" " + name3 + "\t" + "\t" + tot1 + "\t" + tot2);
	}
}
