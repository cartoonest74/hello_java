package hello_java.module;

public class EqualsExample {

	public static void main(String[] args) {
		Member obj1 = new Member("gd","123");
		Member obj2 = new Member("gdd","123");
		Member obj3 = new Member("top","113");
		
		if(obj1.equals(obj2)) {
			System.out.println("=");
		}else {
			System.out.println("!=");
		}
	}
}
