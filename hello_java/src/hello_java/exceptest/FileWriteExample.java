package hello_java.exceptest;

import java.io.IOException;

public class FileWriteExample {
	public static void main(String[] args) {
		try(FileWriter fw = new FileWriter("bla.text")){
			fw.write("javav");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
