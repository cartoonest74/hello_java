package hello_java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class CharacterConvertExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String outputFileName = "C:/Temp/test.txt";
		String test_str = "test str!!!";
		
		try(OutputStream  os = new FileOutputStream(outputFileName);
			Writer writer = new OutputStreamWriter(os, "UTF-8")){
			writer.write(test_str);
			writer.flush();
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		try(InputStream is = new FileInputStream(outputFileName);
			Reader reader = new InputStreamReader(is, "UTF-8")
		){
			char[] data = new char[100];
			int num = reader.read(data);
			System.out.println(data);
			
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
	}

}
