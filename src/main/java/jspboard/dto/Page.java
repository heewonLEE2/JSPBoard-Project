package jspboard.dto;

import java.io.Serializable;

import jspboard.constant.BoardConstant;

public class Page implements Serializable {

	public static final long serialVersionUID = 16848481L;

	private int currPageNum; // 현재 페이지 번호
	private int totalPageCount; // 전체 페이지 수
	private int totalArticleCount; // 전체 게시물 수
	private int firstPageNum; // 첫번째 페이지 번호
	private int lastPageNum; // 마지막 페이지 번호
	private boolean isFirstPage; // 첫번째 페이지 여부
	private boolean isLastPage; // 마지막 페이지 여부

	public Page(int currPageNum, int totalArticleCount) {
		this.currPageNum = currPageNum;
		this.totalArticleCount = totalArticleCount;

		this.totalPageCount = (int) Math.floor(this.totalArticleCount / BoardConstant.ARTICLE_COUNT_PER_PAGE) + 1;

		this.isFirstPage = this.currPageNum <= 1;
		this.isLastPage = this.currPageNum >= this.totalPageCount;
		
		this.firstPageNum
			=((int)Math.ceil((double)currPageNum/BoardConstant.PAGE_NUM_PER_BLOCK)-1) * BoardConstant.PAGE_NUM_PER_BLOCK+1;
		
		this.lastPageNum = (this.firstPageNum + (BoardConstant.PAGE_NUM_PER_BLOCK - 1)) > totalPageCount
				? totalPageCount
				: this.firstPageNum + (BoardConstant.PAGE_NUM_PER_BLOCK - 1);

	}

	public int getCurrPageNum() {
		return currPageNum;
	}

	public void setCurrPageNum(int currPageNum) {
		this.currPageNum = currPageNum;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(int firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	@Override
	public String toString() {
		return "Page [currPageNum=" + currPageNum + ", totalPageCount=" + totalPageCount + ", totalArticleCount="
				+ totalArticleCount + ", firstPageNum=" + firstPageNum + ", lastPageNum=" + lastPageNum
				+ ", isFirstPage=" + isFirstPage + ", isLastPage=" + isLastPage + "]";
	}

}
