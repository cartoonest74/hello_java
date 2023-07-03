package boardTest.product;

import org.json.JSONObject;

public interface Service {
	void productEnroll(JSONObject loginJSON);
	void productSelect(JSONObject loginJSON);
	void productEdit(JSONObject loginJSON);
	void productDelete(JSONObject loginJSON);
}
