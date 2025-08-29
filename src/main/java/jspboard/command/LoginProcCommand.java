package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspboard.model.Member;
import jspboard.service.MemberService;
import jspboard.service.impl.MemberServiceImpl;

public class LoginProcCommand implements BoardCommand {

	private MemberService memberService;

	public LoginProcCommand() {
		this.memberService = new MemberServiceImpl();
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String mid = req.getParameter("mid") == null ? "" : req.getParameter("mid");
		String mpass = req.getParameter("mpass") == null ? "" : req.getParameter("mpass");

		Member loginMember = memberService.loginMember(new Member(mid, mpass, null, null, null));

		// 로그인 처리를 위해 session 에 담는다.
		if (loginMember != null) {
			req.getSession().setAttribute("loginMember", loginMember);
		}

		res.sendRedirect("/index.do");
		
		return "";
	}

}
