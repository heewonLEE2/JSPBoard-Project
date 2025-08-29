package jspboard.service.impl;

import java.util.List;

import jspboard.dao.BoardDao;
import jspboard.dao.impl.BoardDaoImpl;
import jspboard.model.Board;
import jspboard.service.BoardService;

public class BoardServiceImpl implements BoardService {
	
	private BoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public List<Board> listBoard() throws Exception {
		return boardDao.selectBoard();
	}
	
	@Override
	public Board getBoard(int bid) throws Exception {
		return boardDao.selectBoard(bid);
	}

	@Override
	public int registBoard(Board board) throws Exception {
		return boardDao.insertBoard(board);
	}
	
	@Override
	public int modifyBoard(Board board) throws Exception {
		return boardDao.updateBoard(board);
	}
	
	@Override
	public int removeBoard(int bid) throws Exception {
		return boardDao.deleteBoard(bid);
	}

}
