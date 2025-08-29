package jspboard.service.impl;

import java.util.List;

import jspboard.dao.ArticleDao;
import jspboard.dao.impl.ArticleDaoImpl;
import jspboard.model.Article;
import jspboard.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleServiceImpl() {
		articleDao = new ArticleDaoImpl();
	}
	
	@Override
	public List<Article> listArticle(String bid, String searchWord, int currPageNum) throws Exception {
		return articleDao.selectArticle(bid, searchWord, currPageNum);
	}
	
	@Override
	public Article getArticle(int aid) throws Exception {
		return articleDao.selectArticle(aid);
	}
	
	@Override
	public int registArticle(Article article) throws Exception {
		return articleDao.insertArticle(article);
	}
	
	@Override
	public int modifyArticle(Article article) throws Exception {
		return articleDao.updateArticle(article);
	}
	
	@Override
	public int removeArticle(int aid) throws Exception {
		return articleDao.deleteArticle(aid);
	}

	
	@Override
	public int getNextAid() throws Exception {
		return articleDao.getNextAid();
	}
	
	@Override
	public int getTotalArticleCount(String bid, String searchWord) throws Exception {
		return articleDao.getTotalArticleCount(bid, searchWord);
	}
	
	@Override
	public List<Article> latestArticles() throws Exception {
		return articleDao.latestArticles();
	}
}







