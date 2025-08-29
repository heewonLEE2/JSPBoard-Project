package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.BoardService;
import jspboard.service.impl.BoardServiceImpl;

public class RemoveBoardCommand implements BoardCommand{

	private BoardService boardService;
	
	public RemoveBoardCommand() {
		this.boardService = new BoardServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// System.out.println("RemoveBoardCommand 에서의 : " + req.getParameter("bid"));
		
		String bid = req.getParameter("bid") == null ? "" : req.getParameter("bid");
		
		boardService.removeBoard(Integer.parseInt(bid));
		
		res.sendRedirect("/listBoard.do");
		
		return "";
	}
	
}















