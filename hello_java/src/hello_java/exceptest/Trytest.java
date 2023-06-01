package hello_java.exceptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Trytest {
	public static void main(String[] arg) {
		String[] arr_str = {"10","2a"};
		int value = 0;
		for(int i=0; i<=2; i++) {
			try{
				value = Integer.parseInt(arr_str[i]);
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("error:" + e.getMessage());
			}
			catch(NumberFormatException e) {
				System.out.println("error:" + e.getMessage());
			}
			finally{
				System.out.println(value);
			}
		}
	}
}
