package jspboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jspboard.constant.BoardConstant;
import jspboard.dao.ArticleDao;
import jspboard.model.Article;
import jspboard.util.ConnectionUtil;

public class ArticleDaoImpl implements ArticleDao {
	
	@Override
	public List<Article> selectArticle(String bid, String searchWord, int currPageNum)
			throws Exception {
		
		// 첫 행 rownum
		int startRownum
			= (currPageNum-1) * BoardConstant.ARTICLE_COUNT_PER_PAGE + 1;
		
		// 마지막 행 rownum
		int endRownum = currPageNum * BoardConstant.ARTICLE_COUNT_PER_PAGE;
		
		String whereQuery = "";
		if(!bid.equals("")) whereQuery += " and a.bid="+ bid + " ";
		if(!searchWord.equals("")) {
			whereQuery += " and (a.atitle like '%" + searchWord + "%' or a.acontent like '%" + searchWord +"%') ";
		}
		
		String query =
				BoardConstant.ARTICLE_SELECTLIST_PREFIX_QUERY
				+ whereQuery
				+ BoardConstant.ARTICLE_SELECTLIST_SUFFIX_QUERY;
		
		// System.out.println(query);
		
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(query);
		
		pstmt.setInt(1, endRownum);
		pstmt.setInt(2, startRownum);
		ResultSet rs = pstmt.executeQuery();
		
		List<Article> articleList = null;
		if (rs!=null) {
			articleList = new ArrayList<Article>();
			while (rs.next()) {
				Article article = new Article(
						rs.getInt("aid"),
						rs.getString("atitle"),
						rs.getString("acontent"),
						rs.getTimestamp("aregdate"),
						rs.getInt("acount"),
						rs.getInt("afcount"),
						rs.getString("adelyn"),
						rs.getString("mid"),
						rs.getInt("bid"),
						rs.getString("bname")
				);
				articleList.add(article);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);
		// System.out.println(articleList);
		return articleList;
	}
	
	@Override
	public Article selectArticle(int aid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.ARTICLE_SELECTONE_QUERY);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		Article article = null;
		if (rs!=null) {
			if (rs.next()) {
				article = new Article(
						rs.getInt("aid"),
						rs.getString("atitle"),
						rs.getString("acontent"),
						rs.getTimestamp("aregdate"),
						rs.getInt("acount"),
						rs.getInt("afcount"),
						rs.getString("adelyn"),
						rs.getString("mid"),
						rs.getInt("bid"),
						rs.getString("bname")
				);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return article;
	}
	
	@Override
	public int insertArticle(Article article) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.ARTICLE_INSERT_QUERY);
		pstmt.setInt(1, article.getAid());
		pstmt.setString(2, article.getAtitle());
		pstmt.setString(3, article.getAcontent());
		pstmt.setString(4,  article.getMid());
		pstmt.setInt(5,  article.getBid());
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}
	
	@Override
	public int updateArticle(Article article) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.ARTICLE_UPDATE_QUERY);
		pstmt.setString(1, article.getAtitle());
		pstmt.setString(2, article.getAcontent());
		pstmt.setInt(3, article.getBid());
		pstmt.setInt(4,  article.getAid());
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}
	
	@Override
	public int deleteArticle(int aid) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.ARTICLE_DELETE_QUERY);
		pstmt.setInt(1, aid);
		
		int result = pstmt.executeUpdate();
		conn.commit();
		ConnectionUtil.close(conn, null, pstmt);
		return result;
	}

	@Override
	public int getNextAid() throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt 
			= conn.prepareStatement(BoardConstant.GET_NEXTAID_QUERY);
		ResultSet rs = pstmt.executeQuery();
		
		int nextaid = 0;
		if (rs!=null) {
			if (rs.next()) {
				nextaid = rs.getInt("nextaid");
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return nextaid;
	}
	
	@Override
	public int getTotalArticleCount(String bid, String searchWord) throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		
		String whereQuery = "";
		if(!bid.equals("")) whereQuery += " and bid="+ bid + " ";
		if(!searchWord.equals("")) {
			whereQuery += " and (atitle like '%" + searchWord + "%' or acontent like '%" + searchWord +"%') ";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(BoardConstant.GET_TOTALARTICLECOUNT_QUERY+whereQuery);
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if (rs!=null) {
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);		
		return cnt;
	}
	
	@Override
	public List<Article> latestArticles() throws Exception {
		Connection conn = ConnectionUtil.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(BoardConstant.GET_LASEST_ARTICLELIST_QUERY);
		ResultSet rs = pstmt.executeQuery();
		
		List<Article> articleList = null;
		if (rs!=null) {
			articleList = new ArrayList<Article>();
			while (rs.next()) {
				Article article = new Article(
						rs.getInt("aid"),
						rs.getString("atitle"),
						rs.getString("acontent"),
						rs.getTimestamp("aregdate"),
						rs.getInt("acount"),
						rs.getInt("afcount"),
						rs.getString("adelyn"),
						rs.getString("mid"),
						rs.getInt("bid"),
						rs.getString("bname")
				);
				articleList.add(article);
			}
		}
		ConnectionUtil.close(conn, rs, pstmt);
		return articleList;
	}
	
	
} // class








