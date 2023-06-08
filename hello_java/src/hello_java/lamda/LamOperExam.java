package hello_java.lamda;

public class LamOperExam {
	
	public static void main(String[] arg) {
		LamOperator<Integer> l_plus = (x,y) -> x+y;
		LamOperator<Integer> l_sub = (x,y) -> x-y;
		LamOperator<Double> l_divde = (x,y) -> (double) x/y;
		LamOperator<Integer> l_mul = (x,y) -> x*y;
		oper(l_plus, 10, 2);
		oper(l_sub, 10, 2);
		oper(l_divde, 10, 2);
		oper(l_mul, 10, 2);
	}

	private static <T> void oper(LamOperator<T> lamo, int t1, int t2) {
		T result = lamo.operator(t1, t2);
		System.out.println(result); 
	}
}
