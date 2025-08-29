package jspboard.command;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jspboard.constant.BoardConstant;
import jspboard.model.Afile;
import jspboard.model.Article;
import jspboard.service.AfileService;
import jspboard.service.ArticleService;
import jspboard.service.impl.AfileServiceImpl;
import jspboard.service.impl.ArticleServiceImpl;
import jspboard.util.ThumbnailUtil;

public class RegistArticleProcCommand implements BoardCommand {

	private ArticleService articleService;
	private AfileService afileService;

	public RegistArticleProcCommand() {
		this.articleService = new ArticleServiceImpl();
		this.afileService = new AfileServiceImpl();
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String atitle = req.getParameter("atitle") == null ? "" : req.getParameter("atitle");
		String acontent = req.getParameter("acontent") == null ? "" : req.getParameter("acontent");
		String mid = req.getParameter("mid") == null ? "" : req.getParameter("mid");
		int bid = req.getParameter("bid") == null ? 0 : Integer.parseInt(req.getParameter("bid"));

		int aid = articleService.getNextAid();

		articleService.registArticle(new Article(aid, atitle, acontent, null, 0, 0, null, mid, bid, null));

		// javax.servlet.http.Part를 활용한 업로드

		// web.xml에 설정 <multipart-config> 설정

		// <input type="file" 로 전달되는 파일들의 컬렉션 => Part 객체 하나는 업로드 되는 파일 하나 />
		Collection<Part> parts = req.getParts();

		// 업로드된 파일의 수만큼 반복
		for (Part part : parts) {

			// 각 Part의 헤어정보를 확인해서 파일명이 있고 파일크기가 0보다 크면
			if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize() > 0) {

				// 일자별 업로드 디렉토리 생성
				File fileUploadDirectory = new File(BoardConstant.FILE_UPLOAD_DIRECTORY
						+ new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));
				if (!fileUploadDirectory.exists())
					fileUploadDirectory.mkdir();

				// 서버에 저장될 파일명을 생성
				// UUID.randomUUID() : 36자리 임의의 문자열을 반환
				UUID fileName = UUID.randomUUID();
				String serverFileName = fileUploadDirectory.getPath() + "/" + fileName;

				/*
				 * System.out.println("컨텐트타입 : " + part.getContentType()); // 컨텐트타입 : image/jpeg
				 * System.out.println("파일컴포넌트명 (<input type='file' name=) : " + part.getName());
				 * System.out.println("파일 크기 : " + part.getSize());
				 * System.out.println("클라이언트 업로드시 파일명 : " + part.getSubmittedFileName());
				 * System.out.println("생성한 서버상 파일의 실제 경로 : " + serverFileName);
				 */

				// 파일 업로드
				part.write(serverFileName);
				
				// 썸네일 만들기
				
				// 원본파일 디렉토리
				String orgDirName = fileUploadDirectory.getPath() + "/" ;
				// 원본파일명
				String orgFileName = fileName.toString();
				// 원본파일 전체 경로
				String orgFilePath = orgDirName + orgFileName;
				
				// 썸네일파일 디렉토리
				String thumbDirName = "C:/pub2504/eclipse_jee_workspace/JSPBoard/src/main/webapp/jsp/img/";
				
				// 확장자 제외한 썸네일파일 경로
				String thumbFileNameOnly = thumbDirName + orgFileName;
				
				// afile 테이블에 업로드한 파일정보 등록
				afileService.registAfile(new Afile(0, serverFileName, part.getSubmittedFileName(),
						part.getContentType(), null, null, mid, aid, thumbFileNameOnly + "_thumb.jpg"));

			    // 2) 품질/업스케일/JPEG 배경 지정
			    ThumbnailUtil.createThumbnail(orgFilePath, thumbFileNameOnly+"_thumb.jpg", 300, 300,
			            0.9f, false, Color.WHITE);
				
			}
		} // for

		res.sendRedirect("/article/listArticle.do");

		return "";
	}

} // class
