package hello_java.stream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] arg) throws Exception {
//		List<Product> list = new ArrayList<>();
//		for(int i=1; i<=5; i++) {
//			Product product = new Product(i, "상품"+i, "멋진회사", (int)(10000*Math.random()));
//			list.add(product);
//		}
//		
//		Stream<Product> stream = list.stream();
//		stream.forEach(p -> System.out.println(p));
		
		Path path = Paths.get(StreamExample.class.getResource("test.text").toURI());
		Stream<String> stream = Files.lines(path, Charset.defaultCharset());
		stream.forEach(line -> System.out.println(line));
		stream.close();
	}
}
