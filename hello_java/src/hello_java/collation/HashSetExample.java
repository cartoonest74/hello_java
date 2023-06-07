package hello_java.collation;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
	public static void main(String[] arg) {
		Set<Student> setStudent = new HashSet<Student>();
		setStudent.add(new Student(1, "gd"));
		setStudent.add(new Student(2, "dd"));
		setStudent.add(new Student(1, "dddd"));
		
		System.out.println("저장된 객체 수: " + setStudent.size());
		for(Student stu:setStudent) {
			System.out.println(stu.getStudentNum()+" " + stu.getName());
		}
	}
}
