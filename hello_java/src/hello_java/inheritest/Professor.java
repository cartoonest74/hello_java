package hello_java.inheritest;

public class Professor extends Person{
	private String[] subjects;
	
	public void print_subj() {
		System.out.println(name + "과목");
		for(String str:subjects) {
			System.out.println("subject"+str);
		}
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
}
