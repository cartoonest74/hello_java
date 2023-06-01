package hello_java.exceptest;

public class NotExistsIDException extends Exception{
	public NotExistsIDException() {}
	public NotExistsIDException(String msg) {
//		System.out.println(msg+" 없는 아이디 입니다.");
		super(msg);
	}
}
