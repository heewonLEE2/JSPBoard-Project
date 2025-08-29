<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/jsp/include/_head.jspf" %>
<%@ include file="/jsp/include/_nav.jspf" %>

<c:set var="isEdit" value="${not empty board}" />
<h4 class="mb-3">게시판 등록</h4>
<div class="card shadow-sm">
  <div class="card-body">
    <form method="post" action="${cpath}/board/registBoard.do">
      <div class="mb-3">
        <label class="form-label required">게시판명</label>
        <input type="text" name="bname" class="form-control" required maxlength="20" value="${isEdit ? board.bname : ''}">
      </div>
      <div class="form-check form-switch mb-3">
        <input class="form-check-input" type="checkbox" id="bdelyn" name="bdelyn" value="Y" <c:if test="${isEdit and board.bdelyn eq 'Y'}">checked</c:if>>
        <label class="form-check-label" for="bdelyn">삭제</label>
      </div>
      <div class="d-flex gap-2">
        <button class="btn btn-primary" type="submit">전송</button>
        <a class="btn btn-outline-secondary" href="${cpath}/board/listBoard.do">목록으로</a>
      </div>
    </form>
  </div>
</div>
<%@ include file="/jsp/include/_foot.jspf" %>
