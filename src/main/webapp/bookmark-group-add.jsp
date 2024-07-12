<%@ page import="API.DTOBookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="API.DbService" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>


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
<h1>북마크 그룹 추가</h1>


<a href="index.jsp">홈</a> | <a href="loghistory.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>
<br><br>

<form action="bookmark-group-add-submit.jsp" method="post">
    <table>
        <tbody>
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="name" size="10"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="text" name="sequenceOrder" size="10"></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="추가">
</form>
</body>
</html>
