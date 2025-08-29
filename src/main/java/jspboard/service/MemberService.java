package jspboard.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import jspboard.model.Member;

public interface MemberService {
	
	public default List<Member> listMember() throws Exception {
		return null;
	};
	
	public default Member getMember(String mid) throws Exception {
		return null;
	};
	
	public default int registMember(Member member) throws Exception {
		return 0;
	};
	
	public default int modifyMember(Member member) throws Exception {
		return 0;
	};
	
	public default int removeMember(String mid) throws Exception {
		return 0;
	};

	public default Member loginMember(Member member) throws Exception {
		return null;
	}
	
	public default void logoutMember(HttpSession session)throws Exception{
	}
	
	public default List<Member> latestMember() throws Exception{
		return null;
	}
	
	
}



