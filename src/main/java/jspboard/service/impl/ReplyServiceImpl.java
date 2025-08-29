package jspboard.service.impl;

import java.util.List;

import jspboard.dao.ReplyDao;
import jspboard.dao.impl.ReplyDaoImpl;
import jspboard.model.Reply;
import jspboard.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyServiceImpl() {
		replyDao = new ReplyDaoImpl();
	}
	
	@Override
	public List<Reply> listReply(int aid) throws Exception {
		return replyDao.selectReply(aid);
	}
	
	@Override
	public Reply getReply(int rid) throws Exception {
		return replyDao.getReply(rid);
	}
	
	@Override
	public int registReply(Reply reply) throws Exception {
		return replyDao.insertReply(reply);
	}
	
	@Override
	public int modifyReply(Reply reply) throws Exception {
		return replyDao.updateReply(reply);
	}
	
	@Override
	public int removeReply(int rid) throws Exception {
		return replyDao.deleteReply(rid);
	}

}
