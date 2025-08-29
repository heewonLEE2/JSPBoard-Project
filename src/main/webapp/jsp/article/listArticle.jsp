<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/_head.jspf" %>
<%@ include file="/jsp/include/_nav.jspf" %>

<div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
  <h4 class="mb-0">게시글 목록</h4>
  <div class="d-flex gap-2">
    <form class="d-flex" method="get" action="${cpath}/article/listArticle.do">
          <select name="bid" class="form-select me-2" style="min-width:200px" 
         aria-label="게시판 선택" onchange="this.form.submit()">
        <option value="" <c:if test="${empty bid}">selected</c:if>>전체 게시판</option>
        <c:forEach var="board" items="${boardList}">
          <option value="${board.bid}" <c:if test="${bid eq board.bid}">selected</c:if>>
            ${board.bname}
          </option>
        </c:forEach>
      	</select>
      <input class="form-control me-2" type="search" name="searchWord" value="${searchWord}" placeholder="검색어를 입력하세요!">
      <button class="btn btn-outline-secondary" type="submit">🔍</button>
    </form>
    <c:if test="${not empty sessionScope.loginMember}">
    	<a class="btn btn-primary" href="${cpath}/article/registArticle.do?bid=${bid}&searchWord=${searchWord}&currPageNum=${page.currPageNum}">게시글 등록</a>
    </c:if>
  </div>
</div>
<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover mb-0 align-middle">
      <thead class="table-light">
        <tr>
          <th>번호</th>
          <th>게시판</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일시</th>
          <th>댓글수</th>
          <th>첨부파일수</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="article" items="${articleList}">
          <tr>
            <td>${article.aid}</td>
            <td>${article.bname}</td>
            <td>
              <a href="${cpath}/article/getArticle.do?aid=${article.aid}&bid=${bid}&searchWord=${searchWord}&currPageNum=${page.currPageNum}">
                <c:out value="${article.atitle}"/>
                </a>
            </td>
            <td>${article.mid}</td>
            <td><fmt:formatDate value="${article.aregdate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${article.acount}</td>
            <td>${article.afcount}</td>
          </tr>
        </c:forEach>
        <c:if test="${empty articleList}">
          <tr><td colspan="7" class="text-center text-muted">등록된 게시물이 없습니다!</td></tr>
        </c:if>
      </tbody>
    </table>
  </div>
</div>
<c:if test="${page.totalPageCount > 1}">
  <nav class="mt-3">
    <ul class="pagination justify-content-center">
      <li class="page-item ${page.currPageNum == 1 ? 'disabled' : ''}">
        <a class="page-link" href="${cpath}/article/listArticle.do?bid=${param.bid}&currPageNum=${page.currPageNum-1}&searchWord=${param.searchWord}">[이전]</a>
      </li>
      <c:forEach var="pageNum" begin="${page.firstPageNum}" end="${page.lastPageNum}">
        <li class="page-item ${pageNum == page.currPageNum ? 'active' : ''}">
        	<a class="page-link" href="${cpath}/article/listArticle.do?bid=${param.bid}&currPageNum=${pageNum}&searchWord=${param.searchWord}">${pageNum}</a>
        </li>
      </c:forEach>
	      <li class="page-item ${page.currPageNum == page.totalPageCount ? 'disabled' : ''}">
	        <a class="page-link" href="${cpath}/article/listArticle.do?bid=${param.bid}&currPageNum=${page.currPageNum+1}&searchWord=${param.searchWord}">[다음]</a>
	      </li>
    </ul>
  </nav>
</c:if>
<%@ include file="/jsp/include/_foot.jspf" %>
