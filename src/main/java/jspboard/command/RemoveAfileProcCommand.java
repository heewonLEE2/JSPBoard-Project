package jspboard.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.AfileService;
import jspboard.service.impl.AfileServiceImpl;

public class RemoveAfileProcCommand implements BoardCommand {

	private AfileService afileService;

	public RemoveAfileProcCommand() {
		this.afileService = new AfileServiceImpl();
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int aid = req.getParameter("aid") == null ? 0 : Integer.parseInt(req.getParameter("aid"));
		int afid = req.getParameter("afid") == null ? 0 : Integer.parseInt(req.getParameter("afid"));

		// 파일 경로 저장
		String filePath = afileService.getAfile(afid).getAfsfname();
		// System.out.println("filePath : " + filePath);
		
		// 썸네일 파일 경로 저장
		String thumbnailFilePath = afileService.getAfile(afid).getThumbnailpath();
		
		// 파일 삭제
		File deleteFile = new File(filePath);
		if (deleteFile.exists()) {
			deleteFile.delete();
		}

		// 썸네일 파일 삭제
		File thumbnailDeleteFile = new File(thumbnailFilePath);
		if(thumbnailDeleteFile.exists()) {
			thumbnailDeleteFile.delete();
		}
		
		// DB에서 해당 데이터 삭제
		afileService.removeAfile(afid);

		return "/article/getArticle.do?aid=" + aid;
	}
}



