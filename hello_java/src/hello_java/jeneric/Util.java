package hello_java.jeneric;

public class Util<T, V>{
	public static getValue (T t1, V v1){
		if(t1.getKey() == v1) {
			return v1;
		}
	}
}
