<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>시나리오 생성 - 2단계</title>
</head>
<body>
<h2>시나리오 생성 - Step 2: 캐릭터 구성</h2>

<form method="post" action="/scenario/create/step2">
  <input type="hidden" name="scenarioIdx" value="${scenarioIdx}"/>

  <div id="characters">
    <div class="character">
      <label>이름: <input type="text" name="characters[0].name" required/></label>
      <label>타입:
        <select name="characters[0].type">
          <option value="PC">PC</option>
          <option value="NPC">NPC</option>
        </select>
      </label>
      <label>체력: <input type="number" name="characters[0].hp" value="100"/></label>
      <label>선택 가능: <input type="checkbox" name="characters[0].assignable" checked/></label>
    </div>
  </div>

  <button type="button" onclick="addCharacter()">캐릭터 추가</button><br><br>
  <button type="submit">다음 단계 →</button>
</form>

<script>
  let count = 1;
  function addCharacter() {
    const div = document.createElement("div");
    div.className = "character";
    div.innerHTML = `
      <label>이름: <input type="text" name="characters[${count}].name" required/></label>
      <label>타입:
        <select name="characters[${count}].type">
          <option value="PC">PC</option>
          <option value="NPC">NPC</option>
        </select>
      </label>
      <label>체력: <input type="number" name="characters[${count}].hp" value="100"/></label>
      <label>선택 가능: <input type="checkbox" name="characters[${count}].assignable" checked/></label>
      <br><br>
    `;
    document.getElementById("characters").appendChild(div);
    count++;
  }
</script>
</body>
</html>
