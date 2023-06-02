package thisisjava;

public class TestMain {
	static int test_num = 0;
	static int result_num = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 함수 비교
		long time3 = System.nanoTime();
		int_test();
		long time4 = System.nanoTime();
		System.out.println(result_num + " 총합 " + "걸린시간:" + (time4 - time3));
		
		// for 비교
		long time1 = System.nanoTime();
		int int_sum = 0;
		for(int i = 0; i<1000; i++) {
			int_sum += i;
		}
		long time2 = System.nanoTime();
		System.out.println( int_sum + " 총합 " + "걸린시간:" + (time2 - time1));
	}
	
	
	public static void int_test() {
		if(test_num < 1000) {
			test_num += 1;
			int_test();
		}else {
			result_num = test_num;
		}
	}

}
