package jspboard.dao;

import java.util.List;

import jspboard.model.Reply;

public interface ReplyDao {
	
	public default List<Reply> selectReply(int aid) throws Exception {
		return null;
	};
	
	public default Reply getReply(int rid) throws Exception {
		return null;
	};
	
	public default int insertReply(Reply reply) throws Exception{
		return 0;
	};
	
	public default int updateReply(Reply reply) throws Exception {
		return 0;
	};
	
	public default int deleteReply(int rid) throws Exception {
		return 0;
	};

}