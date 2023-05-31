package hellojava.practice2;

public class ActionExample{
	public static void main(String[] args) {
		Action action = new Action() {
			public void work() {
				System.out.println("복사합니다");
			}
		};
		action.work();
	}
}
