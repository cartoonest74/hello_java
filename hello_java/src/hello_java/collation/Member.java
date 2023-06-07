package hello_java.collation;


public class Member implements Comparable<Member>{
	public String id;
	public int score;
	
	public Member(String id, int score) {
		super();
		this.id = id;
		this.score = score;
	}
	public int compareTo(Member m) {
		if(m.score > score) return -1;			
		else if(m.score == score) return 0;
		else return 1;
	}
}
