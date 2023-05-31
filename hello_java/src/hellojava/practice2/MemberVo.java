package hellojava.practice2;

public class MemberVo {
	private String name;
	private String age;
	private String sex;
	private String tele;
	private String addr;
	
	// 한번 호출하면 끝 초기화
	// vo 또는 dto 클래스로 활용
	public MemberVo(String name, String age, String sex, String tele, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.tele = tele;
		this.addr = addr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
