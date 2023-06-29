package hellojava.practice;

import hello_java.stream.CheckFormatException;

public class TrycatchTest {
	static int a;
	static int b;
	public static void main(String[] arg) {
		try {
			inspection("zzz","aa");
		} catch (CheckFormatException e) {
			e.printStackTrace();
			System.out.println("nnnnnnn");
		}
		test2();
		a=1;
		if(a == 0) {
			System.out.println("oiiiiii");
		}
	}
	
	private static void inspection(String id, String pwd) throws CheckFormatException {
		if (! id.equals("zzz")) {
			throw new CheckFormatException("uncorrect id");
		}
		if(! pwd.equals("zzzzzzz")) {
			throw new CheckFormatException("uncorrect pwd");
		}
		test1();
	}
	
	static void test1() {
		System.out.println("e11111");
	}
	static void test2() {
		System.out.println("e11122221");
	}
}
