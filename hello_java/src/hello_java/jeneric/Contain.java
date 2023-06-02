package hello_java.jeneric;

public class Contain <T , A>{
	private T key;
	private A value;
	
	public Contain() {}
	
	public T getKey() {
		return this.key;
	}
	public A getValue() {
		return this.value;
	}
	
	public void set(T t1, A a1) {
		this.key = t1;
		this.value = a1;
	}
	
}
