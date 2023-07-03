package boardTest.member;

public class LoginDTO {
	private int loginNid = 0;
	private int loginIf = 0;
	private String loginId = "";

	public LoginDTO() {
		super();
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getLoginIf() {
		return loginIf;
	}

	public void setLoginIf(int loginIf) {
		this.loginIf = loginIf;
	}

	public int getLoginNid() {
		return loginNid;
	}

	public void setLoginNid(int loginNid) {
		this.loginNid = loginNid;
	}
}
