package hello_java.jeneric;

public class Util{
	public static <P extends Pair<T, V>, T, V> V getValue (P p1, T t1){
		if(p1.getKey() == t1) {
			return p1.getValue();
		}
		return null;
	}
}
