package Select.vo;

public class Employee extends Member {
	private String job;
	
	public Employee(){};
	
	public Employee(String id, String name, String tel, String addr, int type, String etc, String job) {
		super(id, name, tel, addr, type, etc);
		this.job = job;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Employee [job=" + job + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getTel()=" + getTel() + ", getAddr()=" + getAddr() + ", getType()=" + getType() + ", getEtc()="
				+ getEtc() + "]";
	}
	
}
