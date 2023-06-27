package hellojava.practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] arg) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("str");
			String test = br.readLine();
			System.out.println(test);
			System.out.println("int");
			int test2 = Integer.parseInt(br.readLine());
			System.out.println(test2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
