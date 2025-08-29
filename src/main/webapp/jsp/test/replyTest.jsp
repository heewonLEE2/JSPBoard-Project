<%@page import="java.util.List"%>
<%@page import="jspboard.model.Reply"%>
<%@page import="jspboard.service.impl.ReplyServiceImpl"%>
<%@page import="jspboard.service.ReplyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
 
 	ReplyService replyService = new ReplyServiceImpl();
	/* 
 	int result = replyService.registReply(new Reply(
 				0,
 				"네번째 댓글입니다.",
 				null,
 				"N",
 				"hong1",
 				2
 			));
 
 	out.print(result > 0 ? "등록 성공!" : "등록 실패!");
 	
 	out.print(replyService.getReply(1));
 	

 	
 	int result = replyService.modifyReply(
 			new Reply(
 					1,
 					"수정 네번째 댓글입니다.",
 					null,
 					"N",
 					"hong1",
 					2
 					));
 
 	out.print(result > 0 ? "수정 성공!" : "수정 실패!");
 	
 	
 	int result = replyService.removeReply(1);
 	out.print(result > 0 ? "삭제 성공!" : "삭제 실패!");
 	List<Reply> list = replyService.listReply();
 	out.print(list);
 	*/
 	
 %>
 
 
 
 
 
 
 
 
 
 
 