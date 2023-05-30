package hellojava.practice;

public class Activity {
	String name = null;
	public Activity(String name) {
		this.name = name;
		System.out.printf("%s :",this.name);
	}
	public void onCreate() {
		System.out.println("BOOM");
	}
}
