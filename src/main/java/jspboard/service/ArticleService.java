package jspboard.service;

import java.util.List;

import jspboard.model.Article;

public interface ArticleService {

	public default List<Article> listArticle(String bid, String searchWord, int currPageNum) throws Exception {
		return null;
	}

	public default Article getArticle(int aid) throws Exception {
		return null;
	}

	public default int registArticle(Article article) throws Exception {
		return 0;
	}

	public default int modifyArticle(Article article) throws Exception {
		return 0;
	}

	public default int removeArticle(int aid) throws Exception {
		return 0;
	}

	public default int getNextAid() throws Exception {
		return 0;
	}

	public default int  getTotalArticleCount(String bid, String searchWord) throws Exception {
		return 0;
	}
	
	public default List<Article> latestArticles() throws Exception{
		return null;
	}
	
}














