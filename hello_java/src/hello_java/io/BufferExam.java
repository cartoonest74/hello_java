package hello_java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferExam {
	public static void main(String[] arg) {
		String orgFilepath1 = BufferExam.class.getResource("img1.jpg").getPath();
		String targetFillepath1 = "C:/Temp/img4.jpg";
		Thread th1 = new Thread(
				new Runnable() {
					public void run() {
						try(FileInputStream fis = new FileInputStream(orgFilepath1);
								FileOutputStream fos = new FileOutputStream(targetFillepath1)
								){
							long nonBufferTime = copy(fis, fos);
							System.out.println("버퍼 미사용:\t"+nonBufferTime + "ns");
							
						}catch(IOException e) {
							
						}
						
					}
				}
		);
		Thread th2 = new Thread(new Runnable() {
			
			public void run() {
				String orgFilepath2 = BufferExam.class.getResource("img2.jpg").getPath();
				String targetFillepath2 = "C:/Temp/img3.jpg";
				try(
						FileInputStream fis2 = new FileInputStream(orgFilepath2);
						FileOutputStream fos2 = new FileOutputStream(targetFillepath2);
						BufferedInputStream bis = new BufferedInputStream(fis2);
						BufferedOutputStream bos = new BufferedOutputStream(fos2)
						){
					long BufferTime = copy(bis, bos);
					System.out.println("버퍼 사용:\t"+BufferTime + "ns");
					
				}catch(IOException e) {
					
				}
			}
		});
	
		th2.start();
		th1.start();
	}

	private static long copy(InputStream bis, OutputStream bos) throws IOException {
		long start = System.nanoTime();
		bis.transferTo(bos);
		
//		while(true) {
//			int data = bis.read();
//			if(data == -1)break;
//			bos.write(data);
//		}
//		bos.flush();
//		
		long end = System.nanoTime();
		return (end - start);
	}
	
	

}
