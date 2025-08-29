<%@page import="java.util.List"%>
<%@page import="jspboard.model.Afile"%>
<%@page import="jspboard.service.impl.AfileServiceImpl"%>
<%@page import="jspboard.service.AfileService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

	AfileService afileService = new AfileServiceImpl();

	/*
	int result = afileService.registAfile(new Afile(
				0,
				"뭐야뭐야뭐야뭐야",
				"뭐야뭐야뭐야뭐야",
				null,
				"N",
				"hong1",
				2
			));
	out.print(result > 0 ? "등록 성공!" : "등록 실패!");
	
	out.print(afileService.getAfile(2));
	
	int result = afileService.modifyAfile(new Afile(
			1,
			"수정 두번째 afsfname",
			"수정 두번째 afcfname",
			null,
			"N",
			"hong1",
			2
			));
	
	out.print(result > 0 ? "수정 성공!" : "수정 실패!");
	
	
	int result2 = afileService.removeAfile(2);
	out.print(result2 > 0 ? "삭제 성공!" : "삭제 실패!");
	List<Afile> list = afileService.listAfile();
	out.print(list);
	*/

%>














