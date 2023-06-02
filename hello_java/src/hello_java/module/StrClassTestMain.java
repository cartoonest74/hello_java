package hello_java.module;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StrClassTestMain {

	public static void main(String[] args) {
		String data = "자바";
		
		// TODO string -> byte[] -> string
		
		// string => byte 배열
		byte[] arr1 = data.getBytes(); // 기본값 UTF-8
		System.out.println(Arrays.toString(arr1));
		
		// byte 배열 -> string
		String str1 = new String(arr1);
		System.out.println("str1: "+ str1);
		
			
		// string => byte 배열
		try{
			byte[] arr2 = data.getBytes("EUC-KR");
			System.out.println(Arrays.toString(arr2));
			// byte 배열 -> string
			String str2 = new String(arr2, "EUC-KR");
			System.out.println("str2: "+ str2);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		System.out.println("\n StringBuilder");
		// TODO StringBuilder
		String data2 = new StringBuilder()
				.append("gd")
				.toString();
		System.out.println(data2);
		
		System.out.println("\n StringTokenizer");
		// TODO StringTokenizer
		
		String data3 = "gd&top,dlite";
		String[] arr3 = data3.split("&|,");
		for(String token:arr3) {
			System.out.println(token);
		}
		System.out.println(" ");
		
		String data4 = "gd/top/dlite";
		StringTokenizer st = new StringTokenizer(data4, "/");
//		while(st.hasMoreTokens()) {
//			String str_token = st.nextTokens();
			int int_token = st.countTokens();
			System.out.println(int_token);
//		}
	}

}
