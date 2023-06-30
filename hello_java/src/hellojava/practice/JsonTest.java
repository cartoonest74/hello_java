package hellojava.practice;

import org.json.JSONObject;

public class JsonTest {
	public static void main(String[] arg) {
		JSONObject data = new JSONObject();
		data.put("id", "gd");
		data.put("name", "gdragon");
		JSONObject request = new JSONObject();
		request.put("data",data);
		Jsontest2 jst2 = new Jsontest2(); 
		jst2.test1(request);
	}
}
