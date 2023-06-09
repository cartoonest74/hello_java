package hello_java.stream;

public class Student {
	private int id;
	private String name;
	private int age;
	private String sex;
	private String dream;
	
	public Student(int id, String name, int age, String sex, String dream) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.dream = dream;
	}
	

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getDream() {
		return dream;
	}

	public void setDream(String dream) {
		this.dream = dream;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("name: " + name + ", ")
				.append("id: "+ id)
				.append("sex: " + sex + ", ")
				.append("dream: "+ dream)
				.toString();
	}
}
