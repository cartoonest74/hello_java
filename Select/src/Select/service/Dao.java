package Select.service;

import java.io.IOException;

import org.json.JSONObject;

public interface Dao {
	String trans_sequere() throws IOException;
	void conn();
	void discon();
	void closeSSH();
	void insert(JSONObject request);
	void select(JSONObject request);
	void update(JSONObject request);
	void delete(JSONObject request);
	void selectAll(JSONObject request);
}
