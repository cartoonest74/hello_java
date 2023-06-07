package hello_java.collation;

import java.util.TreeSet;

public class TreeSetExample {
	public static void main(String[] arg) {
		TreeSet<Member> treeset = new TreeSet<>();
		treeset.add(new Member("GD", 90));
		treeset.add(new Member("VIP", 30));
		treeset.add(new Member("TP", 40));
		treeset.add(new Member("OP", 50));
		treeset.add(new Member("P", 70));
		treeset.add(new Member("T", 90));
		
		Member member = treeset.last();
		System.out.println("BEST SCORE: "+member.score);
		System.out.println("BEST SCORE ID: "+member.id);
	}
}
