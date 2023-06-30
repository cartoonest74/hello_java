package boardTest.extend;

import org.json.JSONObject;

public interface DAO {
	int insert(JSONObject request);
	int update(JSONObject request);
	int select(JSONObject request);
	int delete(JSONObject request);
}

