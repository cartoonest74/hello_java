package boardTest.board;

import org.json.JSONObject;

public interface Service {
	void boardEnroll(JSONObject loginJSON);
	void boardSelect(JSONObject loginJSON);
	void boardEdit(JSONObject loginJSON);
	void boardDelete(JSONObject loginJSON);
}
