<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>로그인</title></head>
<body>
<h2>로그인</h2>
<form action="<c:url value='/login' />" method="post">
  <input type="text" name="username" placeholder="아이디" required />
  <input type="password" name="password" placeholder="비밀번호" required />
  <button type="submit">로그인</button>

  <c:if test="${not empty param.error}">
    <p style="color:red;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
  </c:if>
  <c:if test="${not empty param.logout}">
    <p style="color:green;">로그아웃 되었습니다.</p>
  </c:if>
</form>
</body>
</html>

