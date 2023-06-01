package hello_java.exceptest;

public class AccountExample {
	public static void main(String[] arg) {
		Account gd = new Account();
		gd.deposit(30000);
		System.out.println("예금액"+gd.getBalance());
		
		try {
			gd.withdraw(88000);
		}catch(InsufficientException e){
			String message = e.getMessage();
			System.out.println(message);
		}
	}
}
