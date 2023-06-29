package memberBoard.member;

import org.json.JSONObject;

public interface Service {
	void joinMember(JSONObject request);
	void findMember(JSONObject request);
	void loginMember(JSONObject request);
	void logoutMember(JSONObject request);
	void editMember(JSONObject request);
	void delMember(JSONObject request);
}
