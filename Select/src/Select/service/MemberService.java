package Select.service;

import org.json.JSONObject;

public class MemberService implements Service {
	MemberDAO dao;
	public MemberService(){
		dao = new MemberDAO();
	}
	@Override
	public void addMember(JSONObject request) {
		dao.insert(request);
	}

	@Override
	public void getMember(JSONObject request) {
		dao.select(request);
	}

	@Override
	public void editMember(JSONObject request) {
		dao.update(request);
	}

	@Override
	public void delMember(JSONObject request) {
		dao.delete(request);
	}

	@Override
	public void getMembers(JSONObject request) {
		dao.selectAll(request);
	}


}
