package hello_java.collectionExam;

import java.util.HashMap;
import java.util.Map;

public class PersonDao{
	private static Map<Integer, String> map = new HashMap<>();
	
	public void createMap(Object obj) {
		if(obj instanceof PersonVo) {
			PersonVo pdao = (PersonVo) obj;
			map.put(pdao.getSsn(), pdao.getName());
		}
	}
	
	public void allPrint() {
			System.out.println(map);
	}
//	@Override
//	public String toString() {
//		return "ghohohoho";
//	}
}
