package hellojava.practice;

public class InstanceExample {
	public static void action(InstanceA a) {
		a.method1();
		if(a instanceof InstanceC c) {
			c.method2();
		}
	}
	public static void main(String[] arg) {
		action(new InstanceA());
		action(new InstaceB());
		action(new InstanceC());
	}
}
