package service;

import java.util.ArrayList;

import vo.Member;

public interface Service {
	void addMember(Member m);

	ArrayList<Member> getMembers();

	Member getMember(String name);

	boolean editMember(Member m);

	boolean delMember(String name);
}
