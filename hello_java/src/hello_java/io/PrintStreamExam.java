package hello_java.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class PrintStreamExam {

	public static void main(String[] args) {
		try(FileOutputStream fos = new FileOutputStream("C:\\Temp\\test2.txt");
			PrintStream ps = new PrintStream(fos)
			){
			ps.print("마치");
			ps.println("마치");
			ps.print("마치");
			ps.println("마치");
			ps.flush();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
		try(FileInputStream fis = new FileInputStream("C:\\Temp\\test2.txt");
			DataInputStream dis = new DataInputStream(fis)
			) {
			File file = new File("C:\\Temp\\test2.txt");
			Charset utf8 = Charset.forName("UTF-8");
			String utf_text = dis.readUTF();
			System.out.println(utf_text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Files.writeString(file,utf_text,utf8);
		// TODO 시간 복잡도 test
		// tail recursion
		Thread testh = new Thread(new Runnable() {
			public void run() {
				long start = System.nanoTime();
				
				int test = tail_f(5,0);
				System.out.println(test);
				
				long end = System.nanoTime();
				System.out.println(end - start);
			}
		});
		testh.start();
		// for
		Thread testh2 = new Thread(new Runnable() {
			public void run() {
				long start = System.nanoTime();

				int sum = 00;
				for(int i=0; i<6; i++) {
					sum += i;
				}
				System.out.println(sum);
				
				long end = System.nanoTime();
				System.out.println(end - start);
			}
		});
		testh2.start();
		
		// recursion
		Thread testh3 = new Thread(new Runnable() {
			public void run() {
				long start = System.nanoTime();
				
				int re = re_f(5);
				System.out.println(re);
				long end = System.nanoTime();
				System.out.println(end - start);
			}

		});
		testh3.start();
		
	}

	private static int tail_f(int i, int j) {
			if(i == 0) {
				return j;
			}
		return tail_f(i - 1, i + j);
	}

	private static int re_f(int i) {
		if(i == 0) return i;
		return i + re_f(i-1);
	}
}
