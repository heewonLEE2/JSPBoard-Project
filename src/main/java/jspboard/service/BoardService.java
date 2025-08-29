package jspboard.service;

import java.util.List;
import jspboard.model.Board;

public interface BoardService {
	
	public default List<Board> listBoard() throws Exception {
		return null;
	};
	
	public default Board getBoard(int bid) throws Exception {
		return null;
	};
	
	public default int registBoard(Board board) throws Exception{
		return 0;
	};
	
	public default int modifyBoard(Board board) throws Exception {
		return 0;
	};
	
	public default int removeBoard(int bid) throws Exception {
		return 0;
	};

}