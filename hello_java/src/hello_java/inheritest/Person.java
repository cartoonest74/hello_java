package hello_java.inheritest;

public class Person {
	protected int num;
	protected String name;
	protected String dept;
	protected String address;
	
	public void print() {
		System.out.println(num);
		System.out.println(name);
		System.out.println(dept);
		System.out.println(address);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
