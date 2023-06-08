package hello_java.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MapExam {

	public static void main(String[] args) {
		int[] int_a = {1, 2, 3, 4, 5};
		IntStream intStream = Arrays.stream(int_a);
		intStream
		.boxed()
		.forEach(d -> System.out.println(d.longValue()));
		
	}

}
