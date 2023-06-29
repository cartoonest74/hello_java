package memberBoard.member;

public class MemberVo {
	private String id;
	private String name;
	private String pwd;
	private String email;
	public MemberVo() {}
	public MemberVo(String id, String name, String pwd, String email) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", name=" + name + ", pwd=" + pwd + ", email=" + email + "]";
	}
	
}
