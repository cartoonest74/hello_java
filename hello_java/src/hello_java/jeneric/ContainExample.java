package hello_java.jeneric;

public class ContainExample {

	public static void main(String[] args) {
		Contain<String, String> container1 = new Contain<String, String>();
		container1.set("gd","artist");
		String name1 = container1.getKey();
		String job = container1.getValue();
		
		Contain<String, Integer> container2 = new Contain<String, Integer>();
		container2.set("gd",18);
		String name2 = container2.getKey();
		int age = container2.getValue();
		
		System.out.printf("name1:%s job:%s \nname2:%s age:%d ", name1, job, name2, age);
	}
}
