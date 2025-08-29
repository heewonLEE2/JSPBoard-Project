package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistMemberCommand implements BoardCommand{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "/jsp/member/registMember.jsp";
	}
	
}
