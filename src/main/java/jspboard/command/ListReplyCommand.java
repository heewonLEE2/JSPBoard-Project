package jspboard.command;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jspboard.model.Reply;
import jspboard.service.ReplyService;
import jspboard.service.impl.ReplyServiceImpl;

public class ListReplyCommand implements BoardCommand{

	private ReplyService replyService;
	
	public ListReplyCommand() {
		this.replyService = new ReplyServiceImpl();
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int aid = Integer.parseInt(req.getParameter("aid"));
		
		List<Reply> replyList = replyService.listReply(aid);
		
		PrintWriter pw =  new PrintWriter(new OutputStreamWriter(res.getOutputStream()));
		pw.write(new GsonBuilder().setPrettyPrinting().create().toJson(replyList));
		pw.flush();
		pw.close();
		
		return "nofr";
	}
	
}

















