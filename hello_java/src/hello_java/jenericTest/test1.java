package hello_java.jenericTest;

import java.util.ArrayList;
import java.util.List;

public class test1 {

	public static void main(String[] args) {
		List<Object> lo = new ArrayList<>();
		lo.add(new Gd());
		lo.add(new Top());
		
		stringTransObjeect(lo);
//		lo.stream().filter(name -> name.getClass().getSimpleName().equals("Gd"));
	}
	
	private static void stringTransObjeect(List<Object> la) {
		for(Object oo:la) {
			if(oo.getClass().getSimpleName().equals("Gd")) {
				((Gd) oo).insert();
			};
		}
	}
	
	
	
	public static class Gd{
		public Gd() {}
		void insert() {
			System.out.println("gd");
		}
	}
	public static class Top{
		public Top() {}
		void insert() {
			System.out.println("top");
		}
	}
}
