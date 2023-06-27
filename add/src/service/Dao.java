package service;

import java.util.ArrayList;

import vo.Member;

public interface Dao {
	void insert(Member m);

	ArrayList<Member> selectAll();

	Member select(String name);

	boolean update(Member m);

	boolean delete(String name);
}
