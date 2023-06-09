package hello_java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {
	public static void main(String[] arg) {
		
		String orginalFileName = "C:/Temp/img1.jpg";
		String targetFileName = "C:/Temp/c_img1.jpg";
		
		try(InputStream is =new FileInputStream(orginalFileName);
			OutputStream os = new FileOutputStream(targetFileName)
		){
//			byte[] data = new byte[1024];
//			while(true) {
//				int num = is.read(data);
//				if(num == -1) break;
//				os.write(data, 0, num);
//			}
			is.transferTo(os);
			
			os.flush();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
