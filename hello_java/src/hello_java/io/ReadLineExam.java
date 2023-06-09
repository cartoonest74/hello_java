package hello_java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

public class ReadLineExam {
	public static void main(String[] arg) {
		
		File file = new File("C:\\workspace\\hello_java\\src\\hello_java\\io");
		File[] files = file.listFiles(
				new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if(name.toUpperCase().startsWith("READ")) {
					File f = new File(dir, name);
					return f.isFile();
				}
				return false;
			}
			
		}
		);
		String s1 = ((File)files[0]).getPath();
//		for(File f:files) {
//			f.getName();
//		}
		try(BufferedReader br = new BufferedReader(new FileReader(s1))
			){
			
				int lineNo = 1;
				
				while(true) {
					String str;
					try {
						str = br.readLine();
						if(str == null) break;
						System.out.println(lineNo + "\t" + str);
						lineNo ++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
