package hello_java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteExample {

	public static void main(String[] args) {
		try (OutputStream os = new FileOutputStream("C:/Temp/test3.db")) {
//			byte a = 10;
//			byte b = 20;
//			byte c = 30;
			
			byte[] array = {10,20,30,40,50};
			
//			os.write(array);
			os.write(array, 1, 3);
			os.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try(InputStream is = new FileInputStream("C:/Temp/test3.db")){
			byte[] data = new byte[100];
			
			while(true) {
				int num = is.read(data);
				if(num == -1) break;
				
				for(int i=0; i<num; i++) {
					System.out.println(data[i]);
				}
			}
			System.out.println();
			throw new NotFileException("파일이 없습니다.");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch (NotFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
