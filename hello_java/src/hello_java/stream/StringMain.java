package hello_java.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringMain {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		MakeStudent ms = (x,y, z, a, b) -> list.add(new Student(x, y, z, a, b));
		createAdd(ms, 1, "gd", 18,"man", "rap");
		createAdd(ms, 5, "top", 30,"man", "rap");
		createAdd(ms, 3, "dlite", 40,"man", "singer");
		createAdd(ms, 4, "vip", 50,"girl", "influence");
		System.out.println(list);
		System.out.println("----------------");
		Collections.sort(list,new Sorting());
		System.out.println(list);
		list.stream().map(s -> s.getName()).forEach(n -> System.out.println(n));
		
		Map<String, List<Student>> map= list.stream().collect(
					Collectors.groupingBy(s -> s.getSex())
				);
		
		System.out.println("----------------");
		List<Student> nameList = map.get("man");
		nameList.stream().forEach(s -> System.out.println(s));
	}

	private static void createAdd(MakeStudent ms, int i, String string, int j, String string2, String string3) {
		ms.makeStudent(i, string, j, string2, string3);
	}

}
