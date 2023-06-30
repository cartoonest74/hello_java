package boardTest.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import org.json.JSONObject;

public class ServerBT {
	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private void start() throws IOException {
				serverSocket = new ServerSocket(50001);
				System.out.println("server start");
				
				boolean flag =true;
				Thread thread = new Thread(()->{
					while(true) {
						Socket socket;
							try {
								socket = serverSocket.accept();
								ServerClientBT scbt = new ServerClientBT(this,socket);
							} catch (IOException e) {
								e.printStackTrace();
								
							}
					}
				});
		}
		
	private void stop() {
		try {
			serverSocket.close();
			threadPool.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}	
	
	public class ServerClientBT{
		private DataOutputStream dos;
		private DataInputStream dis;
		private Socket socket;
		ServerBT sbt;
		
		public ServerClientBT(ServerBT sbt,Socket socket) {
			try {
				this.sbt = sbt;
				this.socket = socket;
				this.dos = new DataOutputStream(socket.getOutputStream());
				this.dis = new DataInputStream(socket.getInputStream());
				recieve();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		
		private void recieve() {
			sbt.threadPool.execute(()->{
				boolean  flag = true;
				while(flag) {
					String receivejs;
					try {
						receivejs = dis.readUTF();
						JSONObject menu = new JSONObject(receivejs);
						int menuNum = menu.getInt("menuNum");
						
						String className = menu.getString("classNenu");
						
						switch(menuNum) {
							case 1-> insert(className);
							case 2-> select(className);
							case 3-> update(className);
							case 4-> delete(className);
						}
					} catch (IOException e) {
						e.printStackTrace();
					
					}
				}
			});
		}

		private void delete(String className) {
		}

		private void update(String className) {
		}

		private void select(String className) {
		}

		private void insert(String className) {
			
		}

		
	}
}
