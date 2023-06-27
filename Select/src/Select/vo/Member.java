package Select.vo;

public class Member {
	private String id;
	private String name;
	private String tel;
	private String addr;
	private int type;
	private String etc;
	
	public Member() {};
	
	public Member(String id, String name, String tel, String addr, int type, String etc) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
		this.type = type;
		this.etc = etc;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "Member [ id=" + id + ", name=" + name + ", tel=" + tel + ", addr=" + addr + ", type="
				+ type + ", etc=" + etc + "]";
	}
}
