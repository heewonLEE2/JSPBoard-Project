package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.AfileService;
import jspboard.service.ArticleService;
import jspboard.service.BoardService;
import jspboard.service.impl.AfileServiceImpl;
import jspboard.service.impl.ArticleServiceImpl;
import jspboard.service.impl.BoardServiceImpl;

public class ModifyArticleCommand implements BoardCommand{

	private BoardService boardService;
	private ArticleService articleService;
	private AfileService afileService;
	
	
	public ModifyArticleCommand() {
		this.boardService = new BoardServiceImpl();
		this.articleService = new ArticleServiceImpl();
		this.afileService = new AfileServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int aid = req.getParameter("aid") == null ? 0 : Integer.parseInt(req.getParameter("aid")); 
		
		String bid = req.getParameter("bid");
		String searchWord = req.getParameter("searchWord") == null ? "" : req.getParameter("searchWord");
		String currPageNum = req.getParameter("currPageNum") == null ? "" : req.getParameter("currPageNum");
		
		req.setAttribute("boardList", boardService.listBoard());
		req.setAttribute("article", articleService.getArticle(aid));
		req.setAttribute("afileList", afileService.listAfile(aid));
		
		req.setAttribute("bid", bid);
		req.setAttribute("searchWord", searchWord);
		req.setAttribute("currPageNum", currPageNum);
		
		return "/jsp/article/registArticle.jsp";
	}
}

















