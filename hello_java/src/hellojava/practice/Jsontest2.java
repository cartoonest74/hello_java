package hellojava.practice;

import org.json.JSONObject;

public class Jsontest2 {
	public Jsontest2() {}
	public void test1(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		System.out.println("id: "+data.getString("id")+" name: " + data.getString("name") );
	}
}
