<%@page import="jspboard.model.Article"%>
<%@page import="jspboard.service.impl.ArticleServiceImpl"%>
<%@page import="jspboard.service.ArticleService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	ArticleService articleSerivce = new ArticleServiceImpl();
	
	for(int i=1; i<450; i++){
		Article article = new Article(
				articleSerivce.getNextAid(), "더미 데이터 게시물 제목" + i, "더미 데이터 게시물 내용" + i ,
					null, 0, 0, null, "heewon", 1, "연습게시물"
				);
		articleSerivce.registArticle(article);
	}


%>