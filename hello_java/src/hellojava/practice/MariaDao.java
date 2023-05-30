package hellojava.practice;

public class MariaDao implements DataAccessObject {
	public void select() {
		System.out.println("maria db search");
	}
	public void insert() {
		System.out.println("maria db insert");
	}
	public void update() {
		System.out.println("maria db update");
	}
	public void delete() {
		System.out.println("maria db delete");
	}
	public void undelete() {
		System.out.println("maria db undelete");
	}
}
