package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistBoardCommand implements BoardCommand{

	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "/jsp/board/registBoard.jsp";
	}
}
