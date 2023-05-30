package basic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) {
		if(args.length < 3) {
			basic.Test1.num_check();			
		}else {
		basic.Test1.calculater(args);
		}
	}
	
	public static void num_check() {
		// input
		System.out.println("ex) 5 + 5 형식으로 입력하시오");
		System.out.println("ex) 연산기호 (+ - x /)");
		Scanner sc = new Scanner(System.in);
		String str_in = sc.nextLine();
		String[] args_cal = str_in.trim().split(" ");
		// pattern
		Pattern pat_num = Pattern.compile("^[0-9]*$");
		Pattern pat_op = Pattern.compile("[+-x/]");
		// num array
		String[] arr_num = new String[3];
		if(args_cal.length >= 3) {
			// matcher
			
			Matcher mat_num1 = pat_num.matcher(args_cal[0]);
			Matcher mat_num2 = pat_num.matcher(args_cal[2]);
			Matcher mat_op = pat_op.matcher(args_cal[1]);
			
			if(mat_op.find() && mat_num2.find() && mat_num1.find()) {
				arr_num[0] = args_cal[0];
				arr_num[1] = args_cal[1];
				arr_num[2] = args_cal[2];
				main(arr_num);
			}else {
				System.out.println("1형식이 맞지 않습니다 다시 입력해주세요.");
				Test1.num_check();
			}
		}else {
			System.out.println("2형식이 맞지 않습니다 다시 입력해주세요.");
			Test1.num_check();
		}
	}
	
	public static void calculater(String[] arg_cal) {
		double invid;
		String[] arr_zero = new String[0];
		int int_num1 = Integer.parseInt(arg_cal[0]);
		int int_num2 = Integer.parseInt(arg_cal[2]);
		String str_op = arg_cal[1];
		switch (str_op) {
			case "+":
				System.out.println(int_num1 + int_num2);
				basic.Test1.main(arr_zero);
				basic.Test1.num_check();
				break;
			case "/":
				invid = int_num1 / (double) int_num2 ;
				System.out.printf("%.3f",invid);
				basic.Test1.num_check();
				break;
			case "x":
				System.out.println(int_num1 * int_num2);
				basic.Test1.num_check();
				break;
			case "-":
				System.out.println(int_num1 - int_num2);
				basic.Test1.num_check();
				break;
		}
	}
}
