package basic;

import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("학생의 점수를 입력하시오 ex) 10(1~100)");
		Scanner sc = new Scanner(System.in);
		int int_grade = sc.nextInt();
		
		String str_grade= "ABCDF";
		char[] arr_char = str_grade.toCharArray();
		String[] arr_grade = new String[arr_char.length];
		for(int i=0; i < arr_char.length; i++) {
			int in_length = (arr_char.length - 1) - i;
			arr_grade[i] = String.valueOf(arr_char[in_length]);
//			System.out.println("i: " + i + "value: " + arr_grade[i]);
		}
		if(int_grade >= 50) {
			if(int_grade < 100) {
				int int_process = (int_grade / 10)%5;
				int result_score = int_process;  
				System.out.println(arr_grade[int_process]);
			}else {
				System.out.println(arr_grade[arr_grade.length-1]);
			}
		}else {
			System.out.println(arr_grade[0]);		
		}
	}

}
