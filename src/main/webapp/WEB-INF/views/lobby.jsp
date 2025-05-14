<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로비 페이지</title>
</head>
<body>

<h2>참여 중인 시나리오 목록</h2>

<!-- lobby.jsp -->
<div class="lobby-actions">
    <form action="/scenario/create/step1" method="get">
        <button type="submit">시나리오 생성</button>
    </form>
</div>

<c:choose>
    <c:when test="${not empty scenarios}">
        <table border="1">
            <thead>
            <tr>
                <th>시나리오 이름</th>
                <th>상태</th>
                <th>참여일</th>
                <th>역할</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="scenario" items="${scenarios}">
                <tr>
                    <td>${scenario.title}</td>
                    <td>${scenario.gameStatus}</td>
                    <td>
                        <fmt:formatDate value="${scenario.participant.joinedAt}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${scenario.participant.isHost}">방장</c:when>
                            <c:otherwise>참가자</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>참여 중인 시나리오가 없습니다.</p>
    </c:otherwise>
</c:choose>

<br/>

<!-- 페이지네이션 -->
<div>
    <c:if test="${page > 1}">
        <a href="?page=${page - 1}">이전</a>
    </c:if>
    <span>Page ${page}</span>
    <a href="?page=${page + 1}">다음</a> <!-- 단순 next (마지막 체크는 나중에 추가 가능) -->
</div>

<br/>
<a href="/mypage">마이페이지로 이동</a>

</body>
</html>
