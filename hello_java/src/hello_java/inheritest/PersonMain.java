package hello_java.inheritest;

public class PersonMain {

	public static void main(String[] args) {
		Person p = new Person();
		p.setNum(1);
		p.setName("person");
		p.setDept("dept");
		p.setAddress("address");
		
		Professor pf = new Professor();
		pf.setNum(1);
		pf.setName("겨수1");
		pf.setDept("컴ㅍㅌ");
		pf.setAddress("ㄱㄱㄷ");
		pf.setSubjects(new String[] {"jb", "os", "comp"});
		pf.print();
		pf.print_subj();
	}

}
