package hello_java.jenericTm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import hello_java.jenericT.Member;
import hello_java.jenericT2.Product;

public class JenericMain{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String  str= br.readLine();
		String reg = "^[0-9ㄱ-ㅎ가-힣]*$";
		char enter = 0x0A;
		Boolean bl = Pattern.matches(reg, str);
		System.out.println("str: "+ str);
		if(str.isBlank()) {
			System.out.println("okokokokok");
		}
		br.close();
		Member son = new Member();
		son= new Product("1212","21212");
		List<Member> obl = new ArrayList<>();
		Member product = new Product();
		obl.add(product);
		for(Member ob:obl) {
			if(ob.getClass().getSimpleName().equals("Product")) {
				testb(ob);
			}
		}
//		testb(new Product());
		if(! (son.getClass().getSimpleName().equals("da"))) {
			System.out.println(son.getClass().getName());
		}
		String input_char ="";
//		switch(1) {
//			case 1 -> {
//				System.out.print("school 입력: ");
//				input_char = br.readLine();
//			}
//			case 2 -> {
//				System.out.print("dept 입력: ");
//				input_char = br.readLine();
//			}
//			case 3 -> {
//				System.out.print("job 입력: ");
//				input_char = br.readLine();
//			}
//		}
//		System.out.println("char: " + input_char);
	}

	public static <T extends Member> void testb(T t1) {
		System.out.println("Son".equals(t1.getClass().getSimpleName()));
		t1.insert(t1.getClass().getSimpleName());
//		String test = new StringBuilder()
//					.append("fsdffds ")
//					.append(t1)
//					.toString();
		
	}
}
