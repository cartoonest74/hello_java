package hellojava.practice;

public class Damagochi_test {
	public static void main(String[] args){
		hellojava.practice.Damagochi jiwoo = new hellojava.practice.Damagochi("pika", "Electronic");
		int a=0;
		while(a < 4) {
			a = jiwoo.int_lv;
			jiwoo.exp_develop();
		}
		System.out.println(jiwoo.toString());
	}
}
