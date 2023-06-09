package hello_java.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileExam {

	public static void main(String[] args) {
		try {
			String data = " id: gd\n" + "email: gd@vip.com\n"+ "tel: 010-8818-1818";
			
			Path path = Paths.get("C:/Temp/gd.text");
			
			Charset utf8 = Charset.forName("UTF-8");
			Files.writeString(path, data, utf8);
			
			System.out.println("파일 유형: "+ Files.probeContentType(path));
			System.out.println("파일 크기: "+ Files.size(path)+ " bytes");
			
			String content = Files.readString(path, utf8);
			System.out.println(content);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
