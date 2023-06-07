package hello_java.collectionExam;

public class PersonVo{
	private int ssn;
	private String name;
	
	public PersonVo(int ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	public int hashCode() {
		return this.ssn;
	}
	
	public boolean equals(PersonVo pv) {
		if(this.ssn == pv.ssn) {
			return true;
		}else {
			return false;
		}
	}
	
}
