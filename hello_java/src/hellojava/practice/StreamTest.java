package hellojava.practice;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
	
	public static void main(String[] arg) {
//		Student student = new Student("gd","gdgdg");
//		Student student1 = new Student("top","gdgdg2");
//		Student student2 = new Student("asfasfa","gdgdg4");
//		Student student3 = new Student("dlite","gdgdg3");
//		List<Student> stu_arr = new ArrayList<>();
//		stu_arr.add(student);
//		stu_arr.add(student1);
//		stu_arr.add(student2);
//		stu_arr.add(student3);
//		boolean str = stu_arr.stream()
//			.anyMatch(name -> name.getPwd().equals("gdfsdfsf"));
//		System.out.println(str);
		test1(1,"no");
	}
	
	public static String test1(int mn, String sst) {
		if(mn == 1 && sst.equals("no")) {
			test2();
			return  "1";
		}
		if(mn == 1) {
			test3();
			return "2";
		}
		return "3";
	}
	public static void test2() {
		System.out.println("2222222");
	}
	public static void test3() {
		System.out.println("33333333");
	}
	
	public static class Student{
		private String name;
		private String pwd;
		
		public Student() {}
		
		public Student(String name, String pwd) {
			super();
			this.name = name;
			this.pwd = pwd;
		}
		
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", pwd=" + pwd + "]";
		}
		
	}
}
