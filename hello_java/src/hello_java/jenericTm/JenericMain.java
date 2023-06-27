package hello_java.jenericTm;

import hello_java.jenericT.Member;
import hello_java.jenericT2.Son;

public class JenericMain{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Member son= new Son("1212","21212");
		testb(son);
	}

	public static <T extends Member> void testb(T t1) {
		System.out.println("Son".equals(t1.getClass().getSimpleName()));
		
//		String test = new StringBuilder()
//					.append("fsdffds ")
//					.append(t1)
//					.toString();
		
	}
}
