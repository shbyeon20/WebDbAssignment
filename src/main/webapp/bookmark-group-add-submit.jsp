<%@ page import="API.DbService, API.DTOBookmarkGroup" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="static java.time.LocalTime.now" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
    request.setCharacterEncoding("UTF-8");

    String name = request.getParameter("name");
    String sequenceOrder = request.getParameter("sequenceOrder");

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String currentDate = now.format(formatter);

    DTOBookmarkGroup dtoBookmarkGroup = new DTOBookmarkGroup();
    DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroupInfo = new DTOBookmarkGroup.BookmarkGroupInfo();
    bookmarkGroupInfo.setName(name);
    bookmarkGroupInfo.setSequenceOrder(sequenceOrder);
    bookmarkGroupInfo.setRegisterDate(currentDate);

    List<DTOBookmarkGroup.BookmarkGroupInfo> list = new ArrayList<>();
    list.add(bookmarkGroupInfo);
    dtoBookmarkGroup.setBookmarkGroups(list);

    DbService dbService = DbService.getInstance();
    dbService.bookmarkGroupInsert(dtoBookmarkGroup);
    %>


<!DOCTYPE html>
<html>
<head>
    <title>북마크 그룹 등록</title>
</head>
<script>
    alert("성공적으로 추가되었습니다");
</script>
<body>

</body>

<%
    response.sendRedirect("bookmark-group.jsp");
%>

</html>


