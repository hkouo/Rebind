<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>시나리오 생성 - 3단계</title>
  <meta charset="UTF-8">
  <script>
    let chapterIndex = 0;

    function addChapter() {
      chapterIndex++;
      const container = document.getElementById("chaptersContainer");

      const html = `
  <fieldset style="margin-top:20px; padding:10px; border:1px solid #ccc;">
    <legend>챕터 #` + (chapterIndex + 1) + `</legend>
    <input type="hidden" name="chapters[` + chapterIndex + `].chapterOrder" value="` + (chapterIndex + 1) + `" />

    <label>챕터명:</label><br>
    <input type="text" name="chapters[` + chapterIndex + `].title" required/><br><br>

    <label>인트로 텍스트:</label><br>
    <textarea name="chapters[` + chapterIndex + `].introScript"></textarea><br><br>

    <label>엔딩 텍스트:</label><br>
    <textarea name="chapters[` + chapterIndex + `].endingScript"></textarea><br><br>

    <label>채팅 가능:</label>
    <input type="checkbox" name="chapters[` + chapterIndex + `].enableChat" value="true"/><br>

    <label>가상 핸드폰 사용:</label>
    <input type="checkbox" name="chapters[` + chapterIndex + `].enablePhone" value="true"/><br>

    <label>가상 검색 사용:</label>
    <input type="checkbox" name="chapters[` + chapterIndex + `].enableSearch" value="true"/><br>

    <label>다음 챕터 ID:</label>
    <input type="number" name="chapters[` + chapterIndex + `].nextChapterIdx"/><br>

    <hr>

    <label>챕터 이미지 URL (배경):</label>
    <input type="text" name="chapters[` + chapterIndex + `].images[0].imageUrl"/>
    <input type="hidden" name="chapters[` + chapterIndex + `].images[0].type" value="BACKGROUND"/>
    <input type="number" name="chapters[` + chapterIndex + `].images[0].zIndex" value="0"/><br><br>

    <label>챕터 아이템 이름:</label>
    <input type="text" name="chapters[` + chapterIndex + `].items[0].name"/><br>

    <label>아이템 설명:</label>
    <textarea name="chapters[` + chapterIndex + `].items[0].description"></textarea><br>

    <label>아이템 이미지 URL:</label>
    <input type="text" name="chapters[` + chapterIndex + `].items[0].imageUrl"/><br>
  </fieldset>
  `;

      container.insertAdjacentHTML("beforeend", html);
    }


    window.onload = function () {
      addChapter(); // 최초 1개 챕터 노출
    };
  </script>
</head>
<body>

<h2>시나리오 생성 - 3단계 (챕터 구성)</h2>

<form method="post" action="/scenario/create/step3">
  <input type="hidden" name="scenarioIdx" value="${scenarioIdx}"/>

  <div id="chaptersContainer"></div>

  <button type="button" onclick="addChapter()">+ 챕터 추가</button>
  <br><br>
  <button type="submit">시나리오 저장</button>
</form>

</body>
</html>
