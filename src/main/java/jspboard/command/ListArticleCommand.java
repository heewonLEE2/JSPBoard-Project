package jspboard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dto.Page;
import jspboard.model.Article;
import jspboard.model.Board;
import jspboard.service.ArticleService;
import jspboard.service.BoardService;
import jspboard.service.impl.ArticleServiceImpl;
import jspboard.service.impl.BoardServiceImpl;

public class ListArticleCommand implements BoardCommand{

	private ArticleService articleService;
	private BoardService boardService;
	
	public ListArticleCommand() {
		this.articleService = new ArticleServiceImpl();
		this.boardService = new BoardServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int currPageNum = req.getParameter("currPageNum")
				== null ? 1 : Integer.parseInt(req.getParameter("currPageNum"));
		
		String bid = req.getParameter("bid") == null ? "" : req.getParameter("bid");
		req.setAttribute("bid", bid);
		
		String searchWord = req.getParameter("searchWord") == null ? "" : req.getParameter("searchWord");
		req.setAttribute("searchWord", searchWord);
		
		List<Board> boardList = boardService.listBoard();
		req.setAttribute("boardList", boardList);
		
		List<Article> articleList = articleService.listArticle(bid, searchWord, currPageNum);
		req.setAttribute("articleList", articleList);
		
		if (articleList!=null) {
			req.setAttribute("articleCount", articleList.size());
		} else {
			req.setAttribute("articleCount", 0);
		}
		
		// page 정보 보내기
		// System.out.println("아티클 사이즈 : "+articleService.getTotalArticleCount());
		req.setAttribute("page",
				new Page(currPageNum, articleList == null ? 0 : articleService.getTotalArticleCount(bid, searchWord)));
		
		return "/jsp/article/listArticle.jsp";
	}
	
}












