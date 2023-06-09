package hello_java.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example1 {

	public static void main(String[] args) {
		// TODO EXAM 1
//		List<String> list = Arrays.asList(
//				"this love 다신 사랑",
//				"두번 다신 사랑안해",
//				"ditto ditto"
//			);
//		list.stream().filter(n -> n.contains("사랑")).forEach(n -> System.out.println(n));

		// TODO EXAM 2
		List<Student> list = new ArrayList<>();
		MakeStudent ms = (x, y, z, a, b) -> list.add(new Student(x, y, z, a, b));
		createAdd(ms, 1, "gd", 18, "man", "rap");
		createAdd(ms, 5, "top", 30, "man", "rap");
		createAdd(ms, 3, "dlite", 40, "man", "singer");
		createAdd(ms, 4, "vip", 50, "girl", "influence");
		
		double avg = list.stream()
						 .collect(
								 Collectors.averagingDouble(s -> s.getAge())
								 );
		System.out.println("평균 나이:" + avg);
		
		// TODO EXAM 3
		List<Student> rapper = list.stream()
								   .filter(s -> s.getDream().contains("rap"))
								   .collect(
										  Collectors.toList()
										   );
		rapper.stream().forEach(r -> System.out.println(r.getName()));
		
		// TODO EXAM 4
		Map<String, List<Student>> groupingMap = list.stream()
													 .filter(s -> s.getDream().contains("rap"))
													 .collect(
															 Collectors.groupingBy(
																	 s -> s.getName()
																	 )
															 );
		
	}

	private static void createAdd(MakeStudent ms, int i, String string, int j, String string2, String string3) {
		ms.makeStudent(i, string, j, string2, string3);
	}

}
