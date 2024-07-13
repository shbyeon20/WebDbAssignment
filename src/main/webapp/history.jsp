<%@ page import="API.DbService" %>
<%@ page import="API.DTOHistory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Location History</title>
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
<h1>조회 히스토리</h1>
<a href="index.jsp">홈</a> | <a href="history.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>
<br><br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Access Time</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
        // 히스토리 모든 정보 끌어오기
        DbService dbService = DbService.getInstance();
        DTOHistory dtoHistory = dbService.historySelect();
        List<DTOHistory.HistoryInfo> historyInfos = dtoHistory.getHistoryInfos();

        for (DTOHistory.HistoryInfo historyInfo : historyInfos) {
    %>
    <tr>
        <td><%= historyInfo.getId() %></td>
        <td><%= historyInfo.getLatitude() %></td>
        <td><%= historyInfo.getLongitude() %></td>
        <td><%= historyInfo.getAccessTime() %></td>
        <td>
            <form action="history-delete.jsp" method="post">
                <input type="hidden" name="historyId" value="<%= historyInfo.getId() %>">
                <input type="submit" value="Delete">
            </form>
        </td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
