package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements BoardCommand{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "/jsp/member/login.jsp";
	}
	
}
