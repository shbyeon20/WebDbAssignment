<%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="API.DbService" %>
<%
    // 삭제버튼 행의 bookmarkid 전해받고 삭제하기
    int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));
    DbService dbService = DbService.getInstance();
    dbService.bookmarkDelete(bookmarkId, 0); // Deleting by bookmarkId

    // 이전 페이지로 다시 돌려보내기
    response.sendRedirect("bookmark-list.jsp");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
