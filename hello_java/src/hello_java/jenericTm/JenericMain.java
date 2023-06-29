package hello_java.jenericTm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hello_java.jenericT.Member;
import hello_java.jenericT2.Son;

public class JenericMain{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Member son = new Member();
		son= new Son("1212","21212");
		
		testb(son);
		if(! (son.getClass().getSimpleName().equals("Member"))) {
			System.out.println(son.getClass().getName());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input_char ="";
		switch(1) {
			case 1 -> {
				System.out.print("school 입력: ");
				input_char = br.readLine();
			}
			case 2 -> {
				System.out.print("dept 입력: ");
				input_char = br.readLine();
			}
			case 3 -> {
				System.out.print("job 입력: ");
				input_char = br.readLine();
			}
		}
		System.out.println("char: " + input_char);
	}

	public static <T extends Member> void testb(T t1) {
		System.out.println("Son".equals(t1.getClass().getSimpleName()));
		
//		String test = new StringBuilder()
//					.append("fsdffds ")
//					.append(t1)
//					.toString();
		
	}
}
