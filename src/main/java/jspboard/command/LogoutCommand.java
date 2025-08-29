package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.service.MemberService;
import jspboard.service.impl.MemberServiceImpl;

public class LogoutCommand implements BoardCommand{

	private MemberService memberServie;
	
	public LogoutCommand() {
		this.memberServie = new MemberServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		memberServie.logoutMember(req.getSession());
		
		res.sendRedirect("/index.do");
		
		return "";
	}
	
}
