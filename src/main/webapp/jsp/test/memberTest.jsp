<%@page import="java.util.List"%>
<%@page import="jspboard.model.Member"%>
<%@page import="jspboard.service.impl.MemberServiceImpl"%>
<%@page import="jspboard.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	MemberService memberService = new MemberServiceImpl();

	// registMember
	/*
	int result = memberService.registMember(
		new Member("hong", "hongpass", "홍길동", null, null)
	);
	out.println(result>0 ? "등록 성공!" : "등록 실패!");
	*/

	// dummy data insert
	/*
	for (int i=1; i<=100; i++) {
		memberService.registMember(
				new Member("hong"+i, "hongpass"+i, "홍길동"+i, null, null)
			);
	}
	
	*/
	// listMember
	List<Member> memberList = memberService.listMember();
	out.print(memberList);
	/*
	// getMember
	Member member = memberService.getMember("hong1");
	out.print(member);
	*/
	
	
	
	
	// modifyMember
	/*
	int result = memberService.modifyMember(
		new Member("hong1", "비밀번호", "수정홍길동", null, "Y")
	);
	out.println(result>0 ? "수정 성공!" : "수정 실패!");
	*/
	
	// removeMember
	/*
	int result = memberService.removeMember("hong1");
		out.println(result>0 ? "삭제 성공!" : "삭제 실패!");
		*/
	
%>














