<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>회원가입</title></head>
<body>
<h2>회원가입</h2>
<form method="post" action="/register">
  <label>아이디: <input type="text" name="userId"/></label><br/>
  <label>비밀번호: <input type="password" name="password"/></label><br/>
  <label>이메일: <input type="email" name="email"/></label><br/>
  <label>닉네임: <input type="text" name="nickname"/></label><br/>
  <button type="submit">회원가입</button>
</form>
<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>
<a href="/login">로그인으로 돌아가기</a>
</body>
</html>


