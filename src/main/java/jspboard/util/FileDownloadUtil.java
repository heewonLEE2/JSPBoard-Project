package jspboard.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileDownloadUtil {

	public String buildContentDisposition(String userAgent, String originalFilename) throws Exception {
		
		String ua = (userAgent == null) ? "" : userAgent.toLowerCase();

		// URL 인코딩(공백 + → %20 치환)
		String encoded = URLEncoder.encode(originalFilename, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		// ASCII 대체명(확장자 유지, 비ASCII 제거)
		String asciiFallback = toAsciiFallback(originalFilename);

		// IE(Trident) 전용
		if (ua.contains("msie") || ua.contains("trident")) {
			// IE는 filename* 미지원 → URL-encoded를 filename에
			return "attachment; filename=\"" + encoded + "\"";
		}

		// 그 외(Chrome/Firefox/Edge/Safari): RFC 5987 조합
		return "attachment; filename=\"" + asciiFallback + "\"; filename*=UTF-8''" + encoded;
	}

	public String toAsciiFallback(String filename) {
		String name = filename;
		String ext = "";
		int dot = filename.lastIndexOf('.');
		if (dot != -1) {
			name = filename.substring(0, dot);
			ext = filename.substring(dot); // ".pdf" 등
		}
		// ASCII 인쇄 가능 문자만 남김
		String ascii = name.replaceAll("[^\\x20-\\x7E]", "");
		if (ascii.isEmpty())
			ascii = "download";
		return ascii + ext;
	}

}
