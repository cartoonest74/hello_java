package hello_java.netWorkio;

import java.io.IOException;
import java.net.Socket;

public class ClientExam {

	public static void main(String[] args) {
		try (Socket socet = new Socket("localhost", 50001)) {
			System.out.println("client success");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("end");
		}
	}

}
