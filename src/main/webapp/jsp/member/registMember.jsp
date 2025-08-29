<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/jsp/include/_head.jspf" %>
<%@ include file="/jsp/include/_nav.jspf" %>
<div class="row justify-content-center">
  <div class="col-md-7 col-lg-6">
    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="mb-3">회원가입</h4>
        <form method="post" action="${cpath}/member/registMember.do">
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label required">아이디</label>
              <input type="text" name="mid" class="form-control" required maxlength="50">
            </div>
            <div class="col-md-6">
              <label class="form-label required">비밀번호</label>
              <input type="password" name="mpass" class="form-control" required maxlength="200">
            </div>
            <div class="col-12">
              <label class="form-label required">이름</label>
              <input type="text" name="mname" class="form-control" required maxlength="200">
            </div>
          </div>
          <div class="form-text mt-2"></div>
          <div class="mt-3 d-flex gap-2">
            <button class="btn btn-success" type="submit">가입하기</button>
            <a class="btn btn-outline-secondary" href="${cpath}/">홈으로</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@ include file="/jsp/include/_foot.jspf" %>
