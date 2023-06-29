package memberBoard.member;

import org.json.JSONObject;

public class MemberService implements Service {
	MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}

	@Override
	public void joinMember(JSONObject request) {
		dao.insert(request);
	}

	@Override
	public void findMember(JSONObject request) {
		// TODO Auto-generated method stub
		dao.info(request);
	}

	@Override
	public void loginMember(JSONObject request) {
		// TODO Auto-generated method stub
		dao.login(request);
	}

	@Override
	public void logoutMember(JSONObject request) {
		// TODO Auto-generated method stub
		dao.logout(request);
	}

	@Override
	public void editMember(JSONObject request) {
		// TODO Auto-generated method stub
		dao.update(request);
	}

	@Override
	public void delMember(JSONObject request) {
		// TODO Auto-generated method stub
		dao.delete(request);
	}
	
}
