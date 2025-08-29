package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.model.Board;
import jspboard.service.BoardService;
import jspboard.service.impl.BoardServiceImpl;

public class RegistBoardProcCommand implements BoardCommand{

	private BoardService boardService;
	
	public RegistBoardProcCommand() {
		this.boardService = new BoardServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String bname = req.getParameter("bname") == null ? "" : req.getParameter("bname");
		String bdelyn = req.getParameter("bdelyn") == null ? "" : req.getParameter("bdelyn");
		
		boardService.registBoard(new Board(0, bname, null, bdelyn));
		
		res.sendRedirect("/listBoard.do");
		
		return "";
	}
}

















