package boardTest.product;

import org.json.JSONObject;

public interface Service {
	void productEnroll(String loginId);
	void productSelect(String loginId);
	void productEdit(String loginId);
	void productDelete(String loginId);
}