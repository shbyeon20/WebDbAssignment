<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="API.DataContainer" %>
<%@ page import="API.DbService" %>

<%
    DbService dbService = new DbService();

    DataContainer dataContainer;
    DataContainer.TbPublicWifiInfo tbPublicWifiInfo;
    List<DataContainer.WifiInfo> wifiInfos;

    dataContainer = dbService.DbSelect();
    tbPublicWifiInfo = dataContainer.getTbPublicWifiInfo();
    wifiInfos = tbPublicWifiInfo.getList();

%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <meta charset="UTF-8">
</head>

<style>
    table,th,td{
        border: 1px solid black;
        border-collapse: collapse;
    }

    th,td{
        font-size: 12px;
        text-align: center;
        white-space: nowrap;
    }

    th{
        background-color: green;
        color : white;
    }

    tr:nth-child(even){
        background-color: gainsboro;
    }

    tr:hover {
        background-color: coral;
    }

</style>

<body>

<h1>와이파이 정보 구하기</h1>

<a href="hello-servlet">홈</a> |  <a href="hello-servlet">위치_히스토리_목록</a> | <a href="hello-servlet">Open API 와이파이 정보가져오기</a>

<br><br>

<label for = "LAT" >LAT: </label>
<input type = "text" id = "LAT" size = "10" >

<label for = "LNT">, LNT: </label>
<input type = "text" id = "LNT" size = "10" >
<input type = "button" value="내 위치 가져오기" onclick="getLocation()">
<input type = "button" value="근처 WPI 정보 보기"  >
<div id="location"></div>


<script>
    function getLocation() {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(function(position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;
                document.getElementById('LAT').value = latitude;
                document.getElementById('LNT').value = longitude;

            }, function(error) {
                console.error('Error obtaining location:', error);
                document.getElementById('location').textContent = 'Failed to retrieve location: ' + error.message;
            });
        } else {
            document.getElementById('location').textContent = 'Geolocation is not supported by this browser.';
        }
    }
</script>

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
        <%
        for (DataContainer.WifiInfo wifiInfo : wifiInfos) {
        %>
    <tr>
        <td><%= wifiInfo.getX_SWIFI_MGR_NO() %></td>
        <td><%= wifiInfo.getX_SWIFI_WRDOFC() %></td>
        <td><%= wifiInfo.getX_SWIFI_MAIN_NM() %></td>
        <td><%= wifiInfo.getX_SWIFI_ADRES1() %></td>
        <td><%= wifiInfo.getX_SWIFI_ADRES2() %></td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_FLOOR() %></td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_TY() %></td>
        <td><%= wifiInfo.getX_SWIFI_INSTL_MBY() %></td>
        <td><%= wifiInfo.getX_SWIFI_SVC_SE() %></td>
        <td><%= wifiInfo.getX_SWIFI_CMCWR() %></td>
        <td><%= wifiInfo.getX_SWIFI_CNSTC_YEAR() %></td>
        <td><%= wifiInfo.getX_SWIFI_INOUT_DOOR() %></td>
        <td><%= wifiInfo.getX_SWIFI_REMARS3() %></td>
        <td><%= wifiInfo.getLAT() %></td>
        <td><%= wifiInfo.getLNT() %></td>
        <td><%= wifiInfo.getWORK_DTTM() %></td>
    </tr>
    <% } %>
    </tr>
    </tbody>
</table>
</body>
</html>