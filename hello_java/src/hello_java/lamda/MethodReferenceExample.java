package hello_java.lamda;

public class MethodReferenceExample {
	public static void main(String[] arg) {
//		Person person = new Person();
//		
//		person.action(Computer :: staticMethod);
//		// 클래스 :: 정적메소드
//		
//		Computer com = new Computer();
//		
//		person.action(com::instanceMethod);
//		// 객체생성 :: 인스턴스메소드
		ComparePerson cp = new ComparePerson();
		cp.ordering(String :: compareToIgnoreCase);
	}
}
