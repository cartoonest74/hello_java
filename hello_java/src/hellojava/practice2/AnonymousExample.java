package hellojava.practice2;

public class AnonymousExample {
	public static void main(String[] args) {
		Anonymous anoy = new Anonymous();
		anoy.field.run();
		anoy.method1();
		anoy.method2(new Vehicle() {
			public void run() {
				System.out.println("트럭");
			}
		});
	}
}
