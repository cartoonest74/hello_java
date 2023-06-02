package hello_java.module;

public class ResourceTestMain {

	public static void main(String[] args) {
		Class cls = Member.class;
		String test1 = cls.getResource("test.txt").getPath();
		System.out.println(test1);
		garbagem();
	}
	
	@Deprecated
	public static void garbagem() {
		System.out.println("FXXK YOU!111111111111");
	}
}
