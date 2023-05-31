package hellojava.practice2;

public class Anonymous {
	Vehicle field = new Vehicle() {
		public void run() {
			System.out.println("자전거");
		}
	};
	void method1() {
		Vehicle localVal = new Vehicle() {
			public void run() {
				System.out.println("승용차");
			}
		};
		localVal.run();
	}
	
	void method2(Vehicle v) {
		v.run();
	}
}
