<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>시나리오 생성 - 1단계</title>
</head>
<body>
<h2>시나리오 생성 - Step 1</h2>

<form method="post" action="/scenario/create/step1" enctype="multipart/form-data">
  <label>시나리오 제목:</label><br>
  <input type="text" name="title" required/><br><br>

  <label>대표 이미지 업로드:</label><br>
  <input type="file" name="imageFile" accept="image/*" required/><br><br>

  <label>참여자 ID 목록:</label><br>
  <input type="text" name="participantUserIdList" placeholder="user01,user02"/><br><br>

  <button type="submit">다음 단계 →</button>
</form>

</body>
</html>
