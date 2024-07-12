<%@ page import="API.DbService" %>
<%@ page import="API.DTOBookmarkGroup" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    DbService dbservice = DbService.getInstance();

    DTOBookmarkGroup dtoBookmarkGroup = dbservice.bookmarkGroupSelect();
    List<DTOBookmarkGroup.BookmarkGroupInfo> list = dtoBookmarkGroup.getBookmarkGroups();
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>북마크 그룹</title>
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

<a href="index.jsp">홈</a> | <a href="loghistory.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>
<br><br>

<form action="bookmark-group-add.jsp" method="get">
    <input type="submit" value="북마크 그룹 이름 추가">
</form>

<br><br>


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
    <tbody>
    <% if (list != null) {
        for (DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroupInfo : list) {
            bookmarkGroupInfo.getRevisionDate();
    %>

    <tr>
        <td><%=bookmarkGroupInfo.getGroupId()%>
        </td>
        <td><%=bookmarkGroupInfo.getName()%>
        </td>
        <td><%=bookmarkGroupInfo.getSequenceOrder()%>
        </td>
        <td><%=bookmarkGroupInfo.getRegisterDate()%>
        </td>
        <td><% if (bookmarkGroupInfo.getRevisionDate() != null)
            out.write(bookmarkGroupInfo.getRevisionDate());%></td>

        <td>
            <form action="bookmark-group-edit.jsp" method="post" accept-charset="UTF-8">
                <input type="hidden" name="groupId" value="<%=bookmarkGroupInfo.getGroupId()%>">
                <input type="hidden" name="name" value="<%=bookmarkGroupInfo.getName()%>">
                <input type="hidden" name="sequenceOrder" value="<%=bookmarkGroupInfo.getSequenceOrder()%>">
                <input type="submit" value="수정">
            </form>
            <form action="bookmark-group-delete.jsp" method="post" accept-charset="UTF-8">
                <input type="hidden" name="groupId" value="<%=bookmarkGroupInfo.getGroupId()%>">
                <input type="submit" value="삭제">
            </form>
        </td>

    </tr>
    <%
            }
        }
    %>
    </tbody>

</table>

</body>
</html>

