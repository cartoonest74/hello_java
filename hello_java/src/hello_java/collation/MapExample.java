package hello_java.collation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
		Map<String, Integer> map  =new HashMap<>();
		map.put("red", 15);
		map.put("blue", 20);
		map.put("yellow", 10);
		
		transSet(map);
	}

	private static void transSet(Object obj) {
		String name = null;
		int maxScore = 0;
		int totalScore = 0;
		int mapv = 0;
		String nextv = null;
		
		if(obj instanceof Map) {
			Map<String, Integer> map= (Map<String, Integer>) obj;
			Set<String> keyset = map.keySet();
			Iterator<String> setiter= keyset.iterator();
			
			while(setiter.hasNext()) {
				nextv = setiter.next();
				System.out.println(nextv);
				mapv = map.get(nextv);
				totalScore += mapv ; 
				if(mapv > maxScore)
					maxScore = map.get(nextv);
					name = nextv;
			}
		}
		System.out.printf("totalScore: "+totalScore+" "+ nextv +" => maxScore: "+maxScore );
	}

}
