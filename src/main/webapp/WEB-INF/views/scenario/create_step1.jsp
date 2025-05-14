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

  <label>시나리오 대표 이미지:</label><br>
  <input type="file" name="imageFile" accept="image/*"/><br><br>

  <!-- 참여자 ID 입력: 쉼표로 구분 -->
  <label>참여자 ID 목록 (쉼표로 구분):</label><br>
  <input type="text" id="participantInput"/><br>
  <input type="hidden" id="participantUserIdxList" name="participantUserIdxList"/><br><br>

  <button type="submit">다음 단계 →</button>
</form>

<script>
  document.querySelector("form").addEventListener("submit", function (e) {
    const input = document.getElementById("participantInput").value;
    const ids = input.split(",").map(i => i.trim()).filter(Boolean);
    document.getElementById("participantUserIdxList").value = ids.join(",");
  });
</script>
</body>
</html>
