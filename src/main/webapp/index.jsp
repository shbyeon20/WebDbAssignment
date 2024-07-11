<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="API.DTOWifi" %>
<%@ page import="API.DbService" %>


<%


    // 데이터 처리 과정
    DbService dbService = DbService.getInstance();
    DTOWifi DTOWifi;
    DTOWifi.TbPublicWifiInfo tbPublicWifiInfo;
    List<DTOWifi.WifiInfo> wifiInfos=null;


    // client로부터 좌표 데이터 수신과정

    String LAT =request.getParameter("LAT");
    String LNT =request.getParameter("LNT");
    if (LAT != null && LNT != null) {

        DTOWifi = dbService.wifiCloseSelect(LAT,LNT,"wifiinfo", 20);

        tbPublicWifiInfo = DTOWifi.getTbPublicWifiInfo();

        wifiInfos = tbPublicWifiInfo.getList();

    }

%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <meta charset="UTF-8">
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

<h1>와이파이 정보 구하기</h1>

<a href="index.jsp">홈</a> | <a href="history.jsp">위치_히스토리_목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보가져오기</a>
| <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹관리</a>

<br><br>

<label for="LAT">LAT: </label>
<input type="text" id="LAT" size="10">

<label for="LNT">, LNT: </label>
<input type="text" id="LNT" size="10">
<input type="button" value="내 위치 가져오기" onclick="getLocation()">
<input type="button" value="근처 WPI 정보 보기" onclick="sendLocation()">
<div id="location"></div>  <!-- if getlocation fail, error message to come out -->


<script src=Location.js></script>

<br><br>

<table>
    <thead>
    <tr>
        <th>Manager No</th>
        <th>Ward Office</th>
        <th>Main Name</th>
        <th>Address 1</th>
        <th>Address 2</th>
        <th>Installation Floor</th>
        <th>Installation Type</th>
        <th>Installation By</th>
        <th>Service Type</th>
        <th>Network Type</th>
        <th>Construction Year</th>
        <th>Indoor/Outdoor</th>
        <th>Remarks</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Work Date</th>
    </tr>
    </thead>
    <tbody>
    <tr>
            <% if (wifiInfos != null && !wifiInfos.isEmpty()) {
        for (DTOWifi.WifiInfo wifiInfo : wifiInfos) {
        %>
    <tr>
        <td><% out.write(wifiInfo.getX_SWIFI_MGR_NO());
        %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_WRDOFC() %>
        </td>
        <td><a href="detail.jsp?wifiNo=<%=wifiInfo.getX_SWIFI_MGR_NO()%>"><%= wifiInfo.getX_SWIFI_MAIN_NM() %>
        </a></td>
        <td><%= wifiInfo.getX_SWIFI_ADRES1() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_ADRES2() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_FLOOR() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_TY() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_MBY() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_SVC_SE() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_CMCWR() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_CNSTC_YEAR() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_INOUT_DOOR() %>
        </td>
        <td><%= wifiInfo.getX_SWIFI_REMARS3() %>
        </td>
        <td><%= wifiInfo.getLAT() %>
        </td>
        <td><%= wifiInfo.getLNT() %>
        </td>
        <td><%= wifiInfo.getWORK_DTTM() %>
        </td>
    </tr>
    <% } %>

    </tr>
    </tbody>
</table>
<% } %>
</body>
</html>