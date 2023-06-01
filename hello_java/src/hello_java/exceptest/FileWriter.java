package hello_java.exceptest;

import java.io.IOException;

public class FileWriter implements AutoCloseable {
	public FileWriter(String filePath) throws IOException{
		System.out.println(filePath + "파일을 엽니다");
	}
	
	public void write(String data) throws IOException{
		System.out.println(data + "저장");
	}
	
	public void close() throws IOException {
		System.out.println("파일을 닫습니다");
	}
}
