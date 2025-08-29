package jspboard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.model.Reply;
import jspboard.service.ReplyService;
import jspboard.service.impl.ReplyServiceImpl;

public class RegistReplyProcCommand implements BoardCommand{

	private ReplyService replyService;
	
	public RegistReplyProcCommand() {
		this.replyService = new ReplyServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
		throws Exception {
		
		String rcontent = req.getParameter("rcontent"); 
		String mid = req.getParameter("mid"); 
		int aid = Integer.parseInt(req.getParameter("aid")); 
		
		
		replyService.registReply(new Reply(0, rcontent, null, null, mid, aid));
		
		return "nofr";
	}
	
}






















