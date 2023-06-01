package hello_java.exceptest;

public class WrongPasswordException extends Exception {
	public WrongPasswordException() {}
	public WrongPasswordException(String msg) {
//		System.out.println(msg+" 없는 비번 입니다.");
		super(msg);
	}
}
