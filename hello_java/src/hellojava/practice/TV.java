package hellojava.practice;

public class TV implements Remocon{
		public void powerOn() {
			System.out.println("TV ON");
		}
		
		public static void main(String[] arg) {
			Remocon r = new TV();
			r.powerOn();
		}
}
