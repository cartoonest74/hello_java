package memberBoard.member;

import org.json.JSONObject;

import memberBoard.exception.CheckFormatException;

public interface Dao {
	void insert(JSONObject request);
	void login(JSONObject request);
	void info(JSONObject request);
	void update(JSONObject request);
	void delete(JSONObject request);
	void logout(JSONObject request);
	void backmenu();
}
