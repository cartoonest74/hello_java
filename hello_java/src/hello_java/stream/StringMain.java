package hello_java.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringMain {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		MakeStudent ms = (x,y) -> list.add(new Student(x,y));
		createAdd(ms, 1, "gd");
		createAdd(ms, 5, "top");
		createAdd(ms, 3, "dlite");
		createAdd(ms, 4, "vip");
		System.out.println(list);
		System.out.println("----------------");
		Collections.sort(list,new Sorting());
		System.out.println(list);
	}

	private static void createAdd(MakeStudent ms, int i, String string) {
		ms.makeStudent(i, string);
	}

}
