package addr_manager.vo;

public class Addres {
	private final String ID;
	private String name;
	private String age;
	private String gender;
	private String tele;
	private String addr;
	
	public Addres(String ID, String name, String age, String gender, String tele, String addr) {
		super();
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.name = gender;
		this.tele = tele;
		this.addr = addr;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String toString() {
		return String.format("이름: %s 나이: %s 전화번호: %s 주소: %s",name, age, tele, addr);
	}
	
}
