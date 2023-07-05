package boardTest.extend;

import org.json.JSONObject;

public interface DAO {
	void insert(JSONObject request);
	void update(JSONObject request);
	void select(JSONObject request);
	void delete(JSONObject request);
}
