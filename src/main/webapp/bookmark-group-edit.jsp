<%@ page import="API.DTOBookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="API.DbService" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%     request.setCharacterEncoding("UTF-8"); // *encoding을 안넣었다가 문자 breaking이 계속 발생함%>

<html>
<head>
    <title>Title</title>
</head>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }

    th, td {
        font-size: 12px;
        text-align: center;
        white-space: nowrap;
        height : 20px;
    }

    th {
        background-color: green;
        color: white;
    }

    tr:nth-child(even) {
        background-color: gainsboro;
    }

    tr:hover {
        background-color: coral;
    }

</style>



<body>
<h1>북마크 그룹 수정</h1>


<a href="index.jsp">홈</a> | <a href="loghistory.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>
<br><br>

<form action="bookmark-group-edit-submit.jsp" method="post" accept-charset="UTF-8">
    <input type="hidden" name="groupId" value="<%= request.getParameter("groupId") %>">
    <table>
        <tbody>
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="name" value ="<%=request.getParameter("name")%>" size="10"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="text" name="sequenceOrder" value ="<%=request.getParameter("sequenceOrder")%>" size="10"></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="수정">
</form>
</body>
</html>
