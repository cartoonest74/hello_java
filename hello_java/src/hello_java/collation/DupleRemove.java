package hello_java.collation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DupleRemove {

	public static void main(String[] arg) {
//		int[] int_arr = {10, 20, 30, 40, 50, 60, 70, 40, 30, 20};
//		Set<Integer> set_int = new HashSet<>();
//		for(int num:int_arr) {
//			System.out.printf("%d: %s \n",num, set_int.add(num) ? "true":"중복");
//		}
//		System.out.println(set_int);
		
		
		// 로또랜덤번호
		List<Integer> linkedList = new LinkedList<>();
		Set<Integer> loddoSet = new HashSet<>();
		Random random = new Random();
		while(loddoSet.size() < 5) {
			loddoSet.add(random.nextInt(45)+1);
		}
		Iterator<Integer> setIterator = loddoSet.iterator();
		while(setIterator.hasNext()) {
			linkedList.add(setIterator.next());
		}
		Collections.sort(linkedList);
		System.out.println(linkedList);
	}
}
