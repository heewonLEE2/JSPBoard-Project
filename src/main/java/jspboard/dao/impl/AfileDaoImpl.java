package jspboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jspboard.constant.BoardConstant;
import jspboard.dao.AfileDao;
import jspboard.model.Afile;
import jspboard.model.Article;
import jspboard.util.ConnectionUtil;

public class AfileDaoImpl implements AfileDao {
	
	@Override
	public List<Afile> selectAfile(int aid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.AFILE_SELECT_QUERY);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		List<Afile> afileList = null;
		if (rs!=null) {
			afileList = new ArrayList<Afile>();
			while (rs.next()) {
				Afile afile = new Afile(
						rs.getInt("afid"),
						rs.getString("afsfname"),
						rs.getString("afcfname"),
						rs.getString("afcontenttype"),
						rs.getTimestamp("afregdate"),
						rs.getString("afdelyn"),
						rs.getString("mid"),
						rs.getInt("aid"),
						rs.getString("thumbnailpath")
				);
				afileList.add(afile);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return afileList;
	}
	
	@Override
	public Afile getAfile(int afid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.AFILE_SELECTONE_QUERY);
		pstmt.setInt(1, afid);
		ResultSet rs = pstmt.executeQuery();

		Afile afile = null;
		
		if (rs!=null) {
			if (rs.next()) {
				afile = new Afile(
						rs.getInt("afid"),
						rs.getString("afsfname"),
						rs.getString("afcfname"),
						rs.getString("afcontenttype"),
						rs.getTimestamp("afregdate"),
						rs.getString("afdelyn"),
						rs.getString("mid"),
						rs.getInt("aid"),
						rs.getString("thumbnailpath")
				);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return afile;
	}
	
	@Override
	public int insertAfile(Afile afile) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.AFILE_INSERT_QUERY);
		
		pstmt.setString(1, afile.getAfsfname());
		pstmt.setString(2, afile.getAfcfname());
		pstmt.setString(3, afile.getAfcontenttype());
		pstmt.setString(4,  afile.getMid());
		pstmt.setInt(5,  afile.getAid());
		pstmt.setString(6, afile.getThumbnailpath());
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
		
	}
	
	@Override
	public int updateAfile(Afile afile) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.AFILE_UPDATE_QUERY);
		pstmt.setString(1, afile.getAfsfname());
		pstmt.setString(2, afile.getAfcfname());
		pstmt.setInt(3,  afile.getAfid());
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}
	
	@Override
	public int deleteAfile(int afid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.AFILE_DELETE_QUERY);
		pstmt.setInt(1, afid);
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}

	@Override
	public List<Afile> latestAfileList() throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.GET_LASEST_AFILELIST_QUERY);
		ResultSet rs = pstmt.executeQuery();
		
		List<Afile> afileList = null;
		if (rs!=null) {
			afileList = new ArrayList<Afile>();
			while (rs.next()) {
				Afile afile = new Afile(
						rs.getInt("afid"),
						rs.getString("afsfname"),
						rs.getString("afcfname"),
						rs.getString("afcontenttype"),
						rs.getTimestamp("afregdate"),
						rs.getString("afdelyn"),
						rs.getString("mid"),
						rs.getInt("aid"),
						rs.getString("thumbnailpath")
				);
				afileList.add(afile);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return afileList;
	}
	
}
