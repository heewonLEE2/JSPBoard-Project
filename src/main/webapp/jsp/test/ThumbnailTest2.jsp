<%@page import="java.awt.Color"%>
<%@page import="jspboard.util.ThumbnailUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	// 원본파일 디렉토리
	String orgDirName = "C:/pub2504/boardfiles/20250827/";
	// 원본파일명
	String orgFileName = "4a39d29a-bf70-4544-bef1-c4a4aad9431e";
	// 원본파일 전체 경로
	String orgFilePath = orgDirName + orgFileName;
	
	// 썸네일파일 디렉토리
	String thumbDirName = "C:/pub2504/eclipse_jee_workspace/JSPBoard/src/main/webapp/jsp/img/";
	
	// 확장자 제외한 썸네일파일 경로
	String thumbFileNameOnly = thumbDirName + orgFileName;

    // 2) 품질/업스케일/JPEG 배경 지정
    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly+"_thumb2.jpg", 300, 300,
            0.9f, false, Color.WHITE);

    
%>    