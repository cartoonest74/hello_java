package Select.service;

import org.json.JSONObject;

public interface Service {
	void addMember(JSONObject request);
	void getMember(JSONObject request);
	void editMember(JSONObject request);
	void delMember(JSONObject request);
	void getMembers(JSONObject request);
}
