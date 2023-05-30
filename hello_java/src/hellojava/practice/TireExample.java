package hellojava.practice;

public class TireExample {
	public static void main(String[] arg) {
		SnowTire st = new SnowTire();
		Tire tr = new SnowTire();
		Tire ctr = (Tire)tr; 
		st.run();
		tr.run();
		ctr.run();
	}
}
