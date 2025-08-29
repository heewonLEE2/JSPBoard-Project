package jspboard.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import jspboard.dao.MemberDao;
import jspboard.dao.impl.MemberDaoImpl;
import jspboard.model.Member;
import jspboard.service.MemberService;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	
	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}
	
	@Override
	public List<Member> listMember() throws Exception {
		return memberDao.selectMember();
	}
	
	@Override
	public Member getMember(String mid) throws Exception {
		return memberDao.selectMember(mid);
	}
	
	@Override
	public int registMember(Member member) throws Exception {
		return memberDao.insertMember(member);
	}
	
	@Override
	public int modifyMember(Member member) throws Exception {
		return memberDao.updateMember(member);
	}
	
	@Override
	public int removeMember(String mid) throws Exception {
		return memberDao.deleteMember(mid);
	}

	@Override
	public Member loginMember(Member member) throws Exception {
		return memberDao.loginMember(member);
	}
	
	@Override
	public void logoutMember(HttpSession session) throws Exception {
		if(session!=null && session.getAttribute("loginMember")!=null) {
			session.invalidate();
		}
	}

	@Override
	public List<Member> latestMember() throws Exception {
		return memberDao.latestMember();
	}
	
	
}










