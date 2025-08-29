package jspboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jspboard.constant.BoardConstant;
import jspboard.dao.BoardDao;
import jspboard.model.Board;
import jspboard.util.ConnectionUtil;

public class BoardDaoImpl implements BoardDao {
	
	@Override
	public List<Board> selectBoard() throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.BOARD_SELECTLIST_QUERY);
		ResultSet rs = pstmt.executeQuery();
		List<Board> boardList = null;
		
		if (rs!=null) {
			boardList = new ArrayList<Board>();
			while (rs.next()) {
				Board board = new Board(
					rs.getInt("bid"),
					rs.getString("bname"),
					rs.getTimestamp("bregdate"),
					rs.getString("bdelyn")
				);
				boardList.add(board);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return boardList;
	}
	
	@Override
	public Board selectBoard(int bid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.BOARD_SELECTONE_QUERY);
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
		Board board = null;
		if (rs!=null) {
			if (rs.next()) {
				board = new Board(
					rs.getInt("bid"),
					rs.getString("bname"),
					rs.getTimestamp("bregdate"),
					rs.getString("bdelyn")
				);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return board;
	}
	
	@Override
	public int insertBoard(Board board) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.BOARD_INSERT_QUERY);
		pstmt.setString(1, board.getBname());
		int result = pstmt.executeUpdate();
		conn.commit();
		
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}
	
	@Override
	public int updateBoard(Board board) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.BOARD_UPDATE_QUERY);
		pstmt.setString(1, board.getBname());
		pstmt.setInt(2, board.getBid());
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}
	
	@Override
	public int deleteBoard(int bid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.BOARD_DELETE_QUERY);
		
		// System.out.println(selectBoard(bid));
		pstmt.setString(1, selectBoard(bid).getBdelyn().equals("Y") ? "N" : "Y");
		pstmt.setInt(2, bid);
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}

}













