<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/jsp/include/_head.jspf"%>
<%@ include file="/jsp/include/_nav.jspf"%>

<script src="/js/reply.js" defer></script>

<div class="d-flex justify-content-between align-items-start mb-3">
	<div>
		<h4 class="mb-1">
			<c:out value="${article.atitle}" />
		</h4>
		<div class="text-muted">
			<p>
				아이디 : ${article.aid} &nbsp;&nbsp;
				작성자 : ${article.mid}&nbsp;&nbsp;
				댓글수 : ${article.acount}&nbsp;&nbsp;
				첨부파일수 : ${article.afcount}&nbsp;&nbsp;
				등록일시 : <fmt:formatDate value="${article.aregdate}" pattern="yyyy-MM-dd HH:mm" />
			</p>
		</div>
	</div>
	<c:if test="${sessionScope.loginMember.mid == article.mid}">
	<div class="btn-group">
		<a class="btn btn-outline-secondary btn-sm"
			href="${cpath}/article/modifyArticle.do?aid=${article.aid}&currPageNum=${currPageNum}&bid=${article.bid}&searchWord=${searchWord}">수정</a>
			<a class="btn btn-outline-danger btn-sm"
			href="${cpath}/article/removeArticle.do?aid=${article.aid}&currPageNum=${currPageNum}&bid=${article.bid}&searchWord=${searchWord}"
			onclick="return confirm('삭제 하시겠습니까?')">삭제</a>
	</div>
	</c:if>
</div>

<div class="card shadow-sm mb-3">
	<div class="card-body">
		<pre class="mb-0">
			<c:out value="${article.acontent}" />
		</pre>
	</div>
</div>

<!-- Attach files -->
<div class="card shadow-sm mb-3">
  <div class="card-header bg-light">첨부파일</div>
  <ul class="list-group list-group-flush">
    <c:forEach var="afile" items="${afileList}">
      <li class="list-group-item d-flex justify-content-between align-items-center">
        <span><i class="bi bi-paperclip"></i> ${afile.afcfname}</span>
        <a class="btn btn-sm btn-outline-primary" href="${cpath}/fileDownload.do?afid=${afile.afid}">[다운로드]</a>
      </li>
    </c:forEach>
    <c:if test="${empty afileList}"><li class="list-group-item text-muted">첨부파일이 없습니다!</li></c:if>
  </ul>
</div>
<!-- Replies -->
<div class="card shadow-sm" id="repliesCard"
     data-cpath="${cpath}"
     data-aid="${article.aid}"
     data-login-mid="${sessionScope.loginMember.mid}">
  <div class="card-header bg-light d-flex justify-content-between align-items-center">
    <span>댓글</span>
    <span class="text-muted small">총<span id="replyCount">0</span>개</span>
  </div>
  
  <c:if test="${not empty sessionScope.loginMember.mid}">
  <div class="card-body">
    <!-- 등록 폼 -->
    <form id="replyForm" class="mb-3" autocomplete="off">
      <div class="input-group">
        <input type="text" id="rcontent" name="rcontent" class="form-control"
               placeholder="댓글 내용을 입력해 주세요!" required maxlength="2000">
        <button class="btn btn-primary" type="submit">댓글 등록</button>
      </div>
    </form>
    </c:if>
    
    <!-- 댓글 목록 -->
    <ul id="replyList" class="list-group"></ul>
    <!-- 비어있을 때 -->
    <div id="replyEmpty" class="text-muted py-3 d-none">등록된 댓글이 없습니다!</div>
  </div>
</div>

<div class="mt-3">
	<a class="btn btn-outline-secondary"
		href="${cpath}/article/listArticle.do?currPageNum=${currPageNum}&bid=${bid}&searchWord=${searchWord}">목록으로</a>
</div>

<%@ include file="/jsp/include/_foot.jspf"%>
