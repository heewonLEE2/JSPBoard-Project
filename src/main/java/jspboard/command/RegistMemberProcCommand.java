package jspboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.model.Member;
import jspboard.service.MemberService;
import jspboard.service.impl.MemberServiceImpl;

public class RegistMemberProcCommand implements BoardCommand {

	private MemberService memberService;

	public RegistMemberProcCommand() {
		this.memberService = new MemberServiceImpl();
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String mid = req.getParameter("mid") == null ? "" : req.getParameter("mid");
		String mpass = req.getParameter("mpass") == null ? "" : req.getParameter("mpass");
		String mname = req.getParameter("mname") == null ? "" : req.getParameter("mname");

		// 멤버 등록 처리
		memberService.registMember(new Member(mid, mpass, mname, null, null));

		res.sendRedirect("/listMember.do");
		
		return "";
	}
}









