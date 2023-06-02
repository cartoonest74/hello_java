package hello_java.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherExample {
	public static void main(String[] arg) {
		String id = "a5AZS1004DSA";
		String pattern_id = "^[a-zA-Z]{1}[0-9a-zA-Z]{8,12}";
		Pattern pattern = Pattern.compile(pattern_id);
		Matcher isMatch = pattern.matcher(id);
		if(isMatch.find()) {
			System.out.println("ID로 사용할 수 있습니다");
		}else {
			System.out.println("ID로 사용할 수 없습니다");
		}
	}
}
