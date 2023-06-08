package hello_java.stream;

import java.util.ArrayList;
import java.util.List;

public class FilteringExam {

	private static void addName(NameAdd n_add, String string) {
		n_add.nameAdd(string);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		NameAdd n_add = (x) -> list.add(x);
		addName(n_add, "gd");
		addName(n_add, "top");
		addName(n_add, "dlite");
		addName(n_add, "gdaa");
		addName(n_add, "gsssp");
		
		list.stream()
			.distinct()
			.forEach(n -> System.out.println(n));
		System.out.println();
		
		list.stream()
			.filter(n -> n.startsWith("gd"))
			.forEach(n -> System.out.println(n));
		System.out.println();
		
		list.stream()
			.distinct()
			.filter(n-> n.startsWith("t"))
			.forEach(n-> System.out.println(n));
	}

}
