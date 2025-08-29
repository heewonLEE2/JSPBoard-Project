package jspboard.dao;

import java.util.List;

import jspboard.model.Member;

public interface MemberDao {
	
	public default List<Member> selectMember() throws Exception {
		return null;
	};
	
	public default Member selectMember(String mid) throws Exception {
		return null;
	};
	
	public default int insertMember(Member member) throws Exception {
		return 0;
	};
	
	public default int updateMember(Member member) throws Exception {
		return 0;
	};
	
	public default int deleteMember(String mid) throws Exception {
		return 0;
	};

	public default Member loginMember(Member member) throws Exception {
		return null;
	}

	public default List<Member> latestMember() throws Exception{
		return null;
	}
	
}













