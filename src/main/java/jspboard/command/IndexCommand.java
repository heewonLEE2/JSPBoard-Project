package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.AfileService;
import jspboard.service.ArticleService;
import jspboard.service.BoardService;
import jspboard.service.MemberService;
import jspboard.service.impl.AfileServiceImpl;
import jspboard.service.impl.ArticleServiceImpl;
import jspboard.service.impl.BoardServiceImpl;
import jspboard.service.impl.MemberServiceImpl;

public class IndexCommand implements BoardCommand{

	private BoardService boardService;
	private ArticleService articleService;
	private MemberService memberService;
	private AfileService afileService;
	
	
	public IndexCommand() {
		this.boardService = new BoardServiceImpl();
		this.articleService = new ArticleServiceImpl();
		this.memberService = new MemberServiceImpl();
		this.afileService = new AfileServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		req.setAttribute("boardList",
				boardService.listBoard().stream().filter(e->e.getBdelyn().equals("N")).toList()
				);
		req.setAttribute("latestArticles", articleService.latestArticles());
		req.setAttribute("latestMembers", memberService.latestMember());
		req.setAttribute("latestPhotos", afileService.latestAfileList());
		
		
		return "/main.jsp";
	}
}
















