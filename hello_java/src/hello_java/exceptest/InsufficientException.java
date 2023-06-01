package hello_java.exceptest;

public class InsufficientException extends Exception{
	public InsufficientException() {}
	public InsufficientException(String msg) {
		super(msg);
	}
}
