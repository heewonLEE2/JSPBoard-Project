package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.ArticleService;
import jspboard.service.impl.ArticleServiceImpl;

public class RemoveArticleCommand implements BoardCommand{

	private ArticleService articleService;
	
	public RemoveArticleCommand() {
		this.articleService = new ArticleServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int aid = Integer.parseInt(req.getParameter("aid"));
		
		String bid = req.getParameter("bid");
		String searchWord = req.getParameter("searchWord") == null ? "" : req.getParameter("searchWord");
		String currPageNum = req.getParameter("currPageNum") == null ? "" : req.getParameter("currPageNum");
		
		articleService.removeArticle(aid);
		
		res.sendRedirect("/listArticle.do?bid="+bid+"&searchWord="+searchWord+"&currPageNum=" + currPageNum);
		
		return "";
	}
}
















