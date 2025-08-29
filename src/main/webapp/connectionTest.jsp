<%@page import="jspboard.util.ConnectionUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Connection conn = ConnectionUtil.getInstance().getConnection();
	System.out.println(conn!=null ? "연결 성공!" : "연결 실패!");
%>