package hello_java.jenericT2;

import hello_java.jenericT.Member;

public class Product extends Member<String>{
	public Product() {
		super();
	}
	public Product(String name, String age) {
		super(name, age);
	}
	public void insert() {
	}
}
