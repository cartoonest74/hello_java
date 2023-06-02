package hello_java.module;

import java.util.Properties;
import java.util.Set;

public class getPropertyExample {

	public static void main(String[] args) {
		//운영체제와 사용자 정보 출력
		String jvName = System.getProperty("java.specification.version");
		String osName = System.getProperty("os.name");
		String usName = System.getProperty("user.name");
		String usHome = System.getProperty("user.home");
		
		Properties props = System.getProperties();
		Set keys = props.keySet();
		
		for(Object objKey:keys) {
			String key = (String) objKey;
			String value = System.getProperty(key);
			System.out.printf("%-40s: %s\n", key, value);
		}
	}

}
