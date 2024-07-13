<%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="API.DbService" %>
<%@ page import="API.DTOBookmark" %>
<%@ page import="java.util.List" %>
<%@ page import="API.DTOBookmarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%
    DbService dbService = DbService.getInstance();

    // Get form parameters
    String bookmarkGroupId = request.getParameter("bookmarkGroupId");
    String X_SWIFI_MGR_NO = request.getParameter("X_SWIFI_MGR_NO");

    // Insert new bookmark into the database
    if (bookmarkGroupId != null  && X_SWIFI_MGR_NO != null) {
        dbService.insertBookmark(X_SWIFI_MGR_NO,bookmarkGroupId);
    }










    DTOBookmark dtoBookmark = dbService.bookmarkSelect();
    List<DTOBookmark.BookmarkInfo> list = dtoBookmark.getBookmarks();
    request.setCharacterEncoding("UTF-8");


%>
<html>
<head>
    <title>북마크 목록</title>
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
    북마크 목록
</h1>

<a href="index.jsp">홈</a> | <a href="history.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>
<br><br>

<table>
    <thead>
    <tr>
        <th>북마크 ID</th>
        <th>와이파이 번호</th>
        <th>북마크그룹명</th>
        <th>등록일</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <% if (list != null) {
        for (DTOBookmark.BookmarkInfo bookmarkInfo : list) {
    %>
    <tr>
        <td><%= bookmarkInfo.getBookmarkId() %></td>
        <td><%= bookmarkInfo.getX_SWIFI_MGR_NO() %></td>
        <td><%= bookmarkInfo.getBookmarkGroupName() %></td>
        <td><%= bookmarkInfo.getRegisterDate() %></td>
        <td>
            <form action="bookmark-delete.jsp" method="post">
                <input type="hidden" name="bookmarkId" value="<%= bookmarkInfo.getBookmarkId() %>">
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
