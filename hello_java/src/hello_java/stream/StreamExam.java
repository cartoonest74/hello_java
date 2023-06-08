package hello_java.stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class StreamExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set.add("gd");
		set.add("top");
		set.add("vip");
//		List<String> al = new ArrayList<>();
//		al.add("gd");
//		al.add("top");
//		al.add("vip");
		
		Stream<String> stream = set.stream();
//		Stream<String> stream = al.stream();
		stream.forEach(name -> System.out.println(name));
	}


}
