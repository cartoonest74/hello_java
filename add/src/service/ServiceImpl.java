package service;

import java.sql.SQLException;
import java.util.ArrayList;

import vo.Member;

public class ServiceImpl implements Service {

	private Dao dao;

	public ServiceImpl() {
		dao = new OracleDao();
	}

	@Override
	public void addMember(Member m) {
		// TODO Auto-generated method stub
		dao.insert(m);
	}

	@Override
	public ArrayList<Member> getMembers() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public Member getMember(String name) {
		// TODO Auto-generated method stub
		return dao.select(name);
	}

	@Override
	public boolean editMember(Member m) {
		// TODO Auto-generated method stub
		return dao.update(m);
	}

	@Override
	public boolean delMember(String name) {
		// TODO Auto-generated method stub
		return dao.delete(name);
	}

	
}
