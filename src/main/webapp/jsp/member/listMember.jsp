<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/jsp/include/_head.jspf" %>
<%@ include file="/jsp/include/_nav.jspf" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h4 class="mb-0">회원 목록</h4>
  <a href="${cpath}/member/registMember.do" class="btn btn-primary">회원 등록</a>
</div>
  <p>등록된 회원의 수 : ${memberCount}, 활성:${activeMemberCount}, 비활성:${memberCount-activeMemberCount}</p>
<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover mb-0 align-middle">
      <thead class="table-light">
        <tr>
          <th scope="col">아이디</th>
          <th scope="col">이름</th>
          <th scope="col">등록일시</th>
          <th scope="col">삭제여부</th>
          <th scope="col" class="text-end">삭제/보관</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="member" items="${memberList}" varStatus="st">
          <tr>
          	<td>${member.mid}</td>
            <td>${member.mname}</td>
            <td><fmt:formatDate value="${member.mregdate}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>
              <span class="badge ${member.mdelyn eq 'Y' ? 'bg-danger' : 'bg-success'}">
                ${member.mdelyn}
              </span>
            </td>
            <td class="text-end">
              <a class="btn btn-sm ${member.mdelyn eq 'Y' ? 'btn-outline-primary' : 'btn-outline-danger'}"
                 href="${cpath}/removeMember.do?mid=${member.mid}"
                 onclick="return confirm('${member.mdelyn eq 'Y' ? '복원' : '삭제'}하시겠습니까?')">[${member.mdelyn eq 'Y' ? '복원' : '삭제'}]</a>
            </td>
          </tr>
        </c:forEach>

        <c:if test="${empty memberList}">
          <tr>
            <td colspan="5" class="text-center text-muted">등록된 회원이 없습니다!</td>
          </tr>
        </c:if>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/jsp/include/_foot.jspf" %>
