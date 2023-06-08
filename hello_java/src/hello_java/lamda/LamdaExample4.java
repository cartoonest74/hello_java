package hello_java.lamda;

public class LamdaExample4 {
	private static Student[] students = {
			new Student("gd", 90, 96),
			new Student("top", 88, 18)
	};
	
	
	private static double avg(Functiont<Student> fun) {
		int sum = 0;
		for(Student student: students) {
			sum += fun.apply(student);
		}
		return (double) sum/students.length;
	}
	
	public static void main(String[] arg) {
//		double englishAvg = avg(s -> s.getEnglishScore());
		double englishAvg = avg(Student :: getEnglishScore);
		System.out.println("영어 평균 점수: " + englishAvg);
		
//		double mathAvg = avg(s -> s.getMathScore());
		double mathAvg = avg(Student :: getMathScore);
		System.out.println("수학 평균 점수: " + mathAvg);
	}
}
