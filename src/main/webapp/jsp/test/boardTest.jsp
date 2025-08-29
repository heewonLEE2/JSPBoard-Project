<%@page import="java.util.List"%>
<%@page import="jspboard.model.Board"%>
<%@page import="jspboard.service.impl.BoardServiceImpl"%>
<%@page import="jspboard.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardService boardService = new BoardServiceImpl();

	/*
	int result = boardService.registBoard(new Board(0, "첫 게시판",null, null));
	out.print(result>0 ? "등록 성공!" : "등록 실패!");


	
	int result = boardService.modifyBoard(new Board(2, "수정게시판이름", null, null));
	out.println(result>0 ? "수정 성공!" : "수정 실패!");
	// getMember
	
	Board board = boardService.getBoard(1);
	out.print(board);
	
	int result = boardService.removeBoard(1);
		out.println(result>0 ? "삭제 성공!" : "삭제 실패!");
	*/
	List<Board> list = boardService.listBoard();
	out.print(list);
	
	
%>