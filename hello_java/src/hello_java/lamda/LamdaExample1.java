package hello_java.lamda;

public class LamdaExample1 {
	public static void main(String[] arg) {
		Thread thread = new Thread(
				()->System.out.println("zzz")
		);
		
		thread.start();
	}
}
