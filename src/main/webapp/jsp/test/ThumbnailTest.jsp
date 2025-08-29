<%@page import="java.awt.Color"%>
<%@page import="jspboard.util.ThumbnailUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	// 원본파일 디렉토리
	String orgDirName = "C:/pub2504/thumb/orgDir/";
	// 원본파일명
	String orgFileName = "image.jpg";
	// 원본파일 전체 경로
	String orgFilePath = orgDirName + orgFileName;
	
	// 썸네일파일 디렉토리
	String thumbDirName = "C:/pub2504/thumb/thumbDir/";
	// 확장자 제외한 썸네일파일 경로
	String thumbFileNameOnly = thumbDirName + orgFileName.substring(0, orgFileName.lastIndexOf('.'));

    // 1) 기본 사용 (비율 유지, 업스케일 없음, JPEG면 흰 배경, 품질 0.85)
    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly + "_thumb.jpg", 200, 150);

    // 2) 품질/업스케일/JPEG 배경 지정
    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly+"_thumb2.jpg", 400, 400,
            0.9f, false, Color.WHITE);

    // 3) WebP 손실 압축 (품질 0.8)
    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly+"_thumb_lossy.webp", 300, 300,
            0.8f, false, Color.WHITE, false);

    // 4) WebP 무손실
    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly+"_thumb_lossless.webp", 300, 300,
            1.0f, true, Color.WHITE, false);

%>    