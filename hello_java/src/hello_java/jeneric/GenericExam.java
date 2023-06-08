package hello_java.jeneric;

public class GenericExam {

	public static void main(String[] args) {
		Course.registerCourse1(new Applicant<Person>(new Person()));
	}

}
