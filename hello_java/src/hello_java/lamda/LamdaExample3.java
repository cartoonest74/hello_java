package hello_java.lamda;

public class LamdaExample3 {
	private static int[] scores = { 10, 50, 3};
	
	private static int maxOrMin(Operator operator) {
		int result = scores[0];
		for (int score: scores) {
			result = operator.apply(result, score);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int max = maxOrMin((x,y) -> {
			if(x > y) return x;
			return y;
		});
		System.out.println("최댓값: "+ max);
	
		int min = maxOrMin((x,y) -> {
			if(x < y) return x;
			return y;
		});
		System.out.println("최솟값: "+ min);
	}



}
