package hello_java.module;

public class BoxingUnboxingMainExample {

	public static void main(String[] args) {
		// -128 ~ 127
		Integer obj1 = 300;
		Integer obj2 = 300;
		// 포장객체에서 내부값을 비교할려면 equals()를 사용
		System.out.println(obj1 == obj2);
		System.out.println(obj1.equals(obj2));
		
		
	}

}
