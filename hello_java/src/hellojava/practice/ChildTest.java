package hellojava.practice;

public class ChildTest extends ParentTest {
	public String name;
	
	public ChildTest() {
		this("gd");
		System.out.println("ChildTest() call");
	}
	
	public ChildTest(String name) {
		this.name = name;
		System.out.println("ChildTest(string name) call");
	}
}
