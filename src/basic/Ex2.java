package basic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex2 {
	public static void main(String[] args) {
		System.out.println("월을 입력하시오");
		Scanner sc = new Scanner(System.in);
		String str_month = sc.next();
		Pattern pat_num = Pattern.compile("0[1-9]|1[012]");
		Matcher mat_num = pat_num.matcher(str_month);
		
		if(mat_num.find()) {
			if()
		}else {
			System.out.print("잘못입력하였습니다.");
		}
	}
}
