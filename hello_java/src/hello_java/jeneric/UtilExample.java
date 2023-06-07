package hello_java.jeneric;

public class UtilExample {
	public static void main(String[] arg) {
		Pair<String, Integer> pair = new Pair<>("gd",18);
		Integer age = Util.getValue(pair, "gd");
		System.out.println(age);
		
		ChildPair<String, Integer> childPair = new ChildPair<>("toop",20);
		Integer childage = Util.getValue(childPair, "top");
		System.out.println(childage);
		
	}
}
