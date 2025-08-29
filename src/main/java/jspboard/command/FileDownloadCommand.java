package jspboard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.model.Afile;
import jspboard.service.AfileService;
import jspboard.service.impl.AfileServiceImpl;
import jspboard.util.FileDownloadUtil;

public class FileDownloadCommand implements BoardCommand {

	private AfileService afileService;

	public FileDownloadCommand() {
		this.afileService = new AfileServiceImpl();
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int afid = req.getParameter("afid") == null ? 0 : Integer.parseInt(req.getParameter("afid"));

		Afile afile = afileService.getAfile(afid);

		// 파일 다운로드 구현

		// 서버에서 클라이언트(웹브라우져)에게 보내는 데이터의 MIME타입 설정
		res.setContentType(afile.getAfcontenttype());

		// 클라이언트 웹브라우져
		String userAgent = req.getHeader("User-Agent");

		FileDownloadUtil fdu = new FileDownloadUtil();
		// 전송하는 파일명 설정
		res.setHeader("Content-Disposition", fdu.buildContentDisposition(userAgent, afile.getAfcfname()));

		// 서버에 있는 파일의 바이트들을 읽는 스트림
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(afile.getAfsfname()));

		// 클라이언트에게 보내줄 바이트들을 쓰는 스트림
		BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());

		// 서버내의 파일을 읽어서 클라이언트에게 전송
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = bis.read(buffer)) > 0) {
			bos.write(buffer, 0, length);
		}

		if (bos != null)
			bos.close();
		if (bis != null)
			bis.close();

		// forwarding 이나 redirection 금지 뷰이름
		return "nofr";
	}

} // class
