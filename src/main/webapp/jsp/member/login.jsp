<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/jsp/include/_head.jspf" %>
<%@ include file="/jsp/include/_nav.jspf" %>
<div class="row justify-content-center">
  <div class="col-md-6 col-lg-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="mb-3">로그인</h4>
        <form method="post" action="${cpath}/member/login.do">
          <div class="mb-3">
            <label class="form-label required">아이디</label>
            <input type="text" name="mid" class="form-control" required maxlength="50">
          </div>
          <div class="mb-3">
            <label class="form-label required">비밀번호</label>
            <input type="password" name="mpass" class="form-control" required maxlength="200">
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-primary" type="submit">로그인</button>
            <a class="btn btn-outline-secondary" href="${cpath}/">홈으로</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@ include file="/jsp/include/_foot.jspf" %>
