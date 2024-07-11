<%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1>
    북마크 그룹
</h1>

<a href="index.jsp">홈</a> | <a href="history.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>

<table>
    <thead>
    <tr>

        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>

    </table>

</body>
</html>
