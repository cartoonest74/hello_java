package hello_java.collectionExam;

import java.util.Scanner;

public class PersonMain {
	
	public static void main(String[] args) {
//		PersonDao dao = new PersonDao();
//		String tes = "fuck : " +  dao;
//		System.out.println(tes);
		PersonDao dao = new PersonDao();
		dao.createMap(new PersonVo(800525, "gd"));
		dao.createMap(new PersonVo(880818, "top"));
		dao.createMap(new PersonVo(800515, "dlite"));
		dao.createMap(new PersonVo(900815, "sun"));
		dao.createMap(new PersonVo(800525, "vp"));
		dao.allPrint();
	}
}
