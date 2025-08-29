package jspboard.dao;

import java.util.List;
import jspboard.model.Board;

public interface BoardDao {
	
	public default List<Board> selectBoard() throws Exception {
		return null;
	};
	
	public default Board selectBoard(int bid) throws Exception {
		return null;
	};
	
	public default int insertBoard(Board board) throws Exception{
		return 0;
	};
	
	public default int updateBoard(Board board) throws Exception {
		return 0;
	};
	
	public default int deleteBoard(int bid) throws Exception {
		return 0;
	};

}