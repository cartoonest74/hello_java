package jdbc_practice.Test;

public class BoardTest {
	
	public void list() {
	
	}
	
	public void basic_menu() {
		String basic_menu = new StringBuilder()
				.append("[게시물 목록]")
				.append("--------------------------------------------------------")
				.append("no\twriter\t\tdate\t\ttitle")
				.append("--------------------------------------------------------")
				.append("Menu : 1.Create | 2.READ | 3.CLEAR | 4.EXIT")
				.toString();
		System.out.println(basic_menu);
	}
	
	public static void main(String[] arg) {
		BoardTest bt = new BoardTest();
		bt.basic_menu();
	}
}
