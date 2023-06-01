package hello_java.exceptest;

public class LoginExample {
	public static void main(String[] arg) {
		try {
			login("top", "13");
		}catch(Exception e){
			System.out.println("ㅇㄹ" + e.getMessage());
		}
		try {
			login("gd", "13");
		}catch(Exception e){
			System.out.println("rㄹ" + e.getMessage());
		}
	}
	
	public static void login(String id, String pwd) throws NotExistsIDException, WrongPasswordException{
		if(!id.equals("gd")) {
			throw new NotExistsIDException(id);
		}
		if(!pwd.equals("123")) {
			throw new WrongPasswordException(pwd);
		}
	}
}
