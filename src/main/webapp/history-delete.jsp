<%@ page import="API.DbService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int historyId = Integer.parseInt(request.getParameter("historyId"));
    DbService dbService = DbService.getInstance();

    try {
        dbService.deleteHistory(historyId);
        out.println("<script>alert('삭제성공'); window.location = 'history.jsp';</script>");
    } catch (Exception e) {
        out.println("<script>alert('삭제실패'); window.location = 'history.jsp';</script>");
    }
%>