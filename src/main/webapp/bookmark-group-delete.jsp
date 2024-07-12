<%@ page import="API.DbService" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 10.
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% DbService dbService = DbService.getInstance();
    int groupid = Integer.valueOf(request.getParameter("groupId"));

    dbService.bookmarkGroupDelete(groupid);
    response.sendRedirect("bookmark-group.jsp");
%>
<html>
<script>
    alert("성공적으로 삭제되었습니다.");
</script>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
