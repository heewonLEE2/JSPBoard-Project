package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.AfileService;
import jspboard.service.ArticleService;
import jspboard.service.impl.AfileServiceImpl;
import jspboard.service.impl.ArticleServiceImpl;

public class GetArticleCommand implements BoardCommand{

	private ArticleService articleService;
	private AfileService afileService;
	
	
	public GetArticleCommand() {
		this.articleService = new ArticleServiceImpl();
		this.afileService = new AfileServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int aid = Integer.parseInt(req.getParameter("aid"));
		String bid = req.getParameter("bid");
		String searchWord = req.getParameter("searchWord") == null ? "" : req.getParameter("searchWord");
		String currPageNum = req.getParameter("currPageNum") == null ? "" : req.getParameter("currPageNum");
		
		req.setAttribute("article", articleService.getArticle(aid)); 
		req.setAttribute("afileList", afileService.listAfile(aid));
		req.setAttribute("bid", bid);
		req.setAttribute("searchWord", searchWord);
		req.setAttribute("currPageNum", currPageNum);
		
		
		return "/jsp/article/getArticle.jsp";
	}
}















