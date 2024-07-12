<%@ page import="API.DbService" %>
<%
    double lat = Double.parseDouble(request.getParameter("lat"));
    double lng = Double.parseDouble(request.getParameter("lng"));
    System.out.println(lat);
    DbService dbService = DbService.getInstance();
    dbService.insertHistory( lat, lng);

    out.print("History logged successfully"); // Just a simple confirmation
%>