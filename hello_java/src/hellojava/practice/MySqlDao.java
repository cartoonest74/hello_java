package hellojava.practice;

public class MySqlDao implements DataAccessObject {
	public void select() {
		System.out.println("sql db search");
	}
	public void insert() {
		System.out.println("sql db insert");
	}
	public void update() {
		System.out.println("sql db update");
	}
	public void delete() {
		System.out.println("sql db delete");
	}
}
