package hellojava.practice;

public class ParentTest {
	public String nation;
	
	public ParentTest() {
		this("japan");
		System.out.println("ParentTest() call");
	}
	
	public ParentTest(String nation) {
		this.nation = nation;
		System.out.println("ParentTest(String nation) call");
	}
}
