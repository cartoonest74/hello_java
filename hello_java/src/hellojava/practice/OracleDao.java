package hellojava.practice;

public class OracleDao implements DataAccessObject{
	public void select() {
		System.out.println("ora db search");
	}
	public void insert() {
		System.out.println("ora db insert");
	}
	public void update() {
		System.out.println("ora db update");
	}
	public void delete() {
		System.out.println("ora db delete");
	}
}
