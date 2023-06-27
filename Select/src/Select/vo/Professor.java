package Select.vo;

public class Professor extends Member{
	private String dept;

	public Professor() {};
	public Professor( String id, String name, String tel, String addr, int type, String etc, String dept) {
		super(id, name, tel, addr, type, etc);
		this.dept = dept;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Professor [dept=" + dept  + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getTel()=" + getTel() + ", getAddr()=" + getAddr() + ", getType()=" + getType()
				+ ", getEtc()=" + getEtc() + "]";
	}


}
