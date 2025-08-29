package jspboard.service;

import java.util.List;

import jspboard.model.Reply;

public interface ReplyService {
	
	public default List<Reply> listReply(int aid) throws Exception {
		return null;
	};
	
	public default Reply getReply(int rid) throws Exception {
		return null;
	};
	
	public default int registReply(Reply reply) throws Exception{
		return 0;
	};
	
	public default int modifyReply(Reply reply) throws Exception {
		return 0;
	};
	
	public default int removeReply(int rid) throws Exception {
		return 0;
	};

}