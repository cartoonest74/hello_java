package boardTest.extend;

import org.json.JSONObject;

public interface DAO {
	void login(JSONObject request);
	void logout(JSONObject request);
	void insert(JSONObject request);
	void update(JSONObject request);
	void select(JSONObject request);
	void delete(JSONObject request);
}
