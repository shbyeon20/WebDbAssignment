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
  String groupId = request.getParameter("groupId");
  System.out.println(name);
  System.out.println(sequenceOrder);
  System.out.println(groupId);


  DbService dbService = DbService.getInstance();
  dbService.bookmarkGroupUpdate(name,groupId,sequenceOrder);
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


