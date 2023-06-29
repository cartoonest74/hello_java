package Select.service;

import java.io.IOException;

import org.json.JSONObject;

public interface Dao {
	void insert(JSONObject request);
	void select(JSONObject request);
	void update(JSONObject request);
	void delete(JSONObject request);
	void selectAll();
}
