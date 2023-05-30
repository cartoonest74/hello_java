package hellojava.practice;

public class DaoExample {
	public static void dbWork(DataAccessObject dao) {
		if(dao instanceof MariaDao mrd) {
			mrd.undelete();
		}else {
			System.out.println();
			dao.select();
			dao.insert();
			dao.update();
			dao.delete();
		}
	}
	
	public static void main(String[] arg) {
		dbWork(new OracleDao());
		dbWork(new MySqlDao());
		dbWork(new MariaDao());
	}
}
