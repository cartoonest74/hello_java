package hello_java.lamda;

public class ComparePerson {
	public void ordering(Comparable comparable) {
		String a = "gd";
		String b = "top";
		
		int result = comparable.compare(a,b);
		
		if(result < 0) {
			System.out.printf("%s 은 %s 앞", a, b);

		}else if(result == 0) {
			System.out.printf("%s 은 %s 같", a, b);
			
		}else {
			System.out.printf("%s 은 %s 뒤", a, b);
			
		}
	}
}
