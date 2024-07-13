    <%@ page import="API.DTOWifi" %>
    <%@ page import="java.util.List" %>
    <%@ page import="API.DbService" %>
    <%@ page import="API.DTOWifi" %>
    <%@ page import="API.DTOBookmarkGroup" %><%--
      Created by IntelliJ IDEA.
      User: byeonsanghwa
      Date: 2024. 7. 9.
      Time: 오후 3:52
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
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
    <%
        DbService dbService = DbService.getInstance();

        DTOWifi dtoWifi;
        DTOWifi.TbPublicWifiInfo tbPublicWifiInfo;
        List<DTOWifi.WifiInfo> wifiInfos;

        // 전페이지에서 선택된 와이파이의 id를 받아서 해당 레코드만 상세정보 보여주기
        String wifiNo = request.getParameter("wifiNo");
        dtoWifi = dbService.wifiOneSelect("wifiinfo", 1, wifiNo);
        tbPublicWifiInfo = dtoWifi.getTbPublicWifiInfo();
        wifiInfos = tbPublicWifiInfo.getList();
        DTOWifi.WifiInfo wifiInfo = wifiInfos.get(0);

        DTOBookmarkGroup dtoBookmarkGroup = dbService.bookmarkGroupSelect();
        List<DTOBookmarkGroup.BookmarkGroupInfo> bookmarkGroupInfos = dtoBookmarkGroup.getBookmarkGroups();
    %>


    <form action="bookmark-list.jsp" method="get">
    <select name ="bookmarkGroupId" >
        <%
            for (DTOBookmarkGroup.BookmarkGroupInfo bookmarkGroupInfo : bookmarkGroupInfos) {
        %>
        <option  value ="<%=bookmarkGroupInfo.getGroupId()%>">
            <%=bookmarkGroupInfo.getName()%>
        </option>
        <%
            }
        %>
    </select>
    <input type ="hidden" name="X_SWIFI_MGR_NO" value ="<%=wifiInfo.getX_SWIFI_MGR_NO()%>">
        <button type="submit">북마크 추가하기</button>
    </form>


    <table>
        <tr>
            <th>Manager No</th>
            <td><%= wifiInfo.getX_SWIFI_MGR_NO() %>
            </td>
        </tr>
        <tr>
            <th>Ward Office</th>
            <td><%= wifiInfo.getX_SWIFI_WRDOFC() %>
            </td>
        </tr>
        <tr>
            <th>Main Name</th>
            <td><%= wifiInfo.getX_SWIFI_MAIN_NM() %>
            </td>
        </tr>
        <tr>
            <th>Address 1</th>
            <td><%= wifiInfo.getX_SWIFI_ADRES1() %>
            </td>
        </tr>
        <tr>
            <th>Address 2</th>
            <td><%= wifiInfo.getX_SWIFI_ADRES2() %>
            </td>
        </tr>
        <tr>
            <th>Installation Floor</th>
            <td><%= wifiInfo.getX_SWIFI_INSTL_FLOOR() %>
            </td>
        </tr>
        <tr>
            <th>Installation Type</th>
            <td><%= wifiInfo.getX_SWIFI_INSTL_TY() %>
            </td>
        </tr>
        <tr>
            <th>Installation By</th>
            <td><%= wifiInfo.getX_SWIFI_INSTL_MBY() %>
            </td>
        </tr>
        <tr>
            <th>Service Type</th>
            <td><%= wifiInfo.getX_SWIFI_SVC_SE() %>
            </td>
        </tr>
        <tr>
            <th>Network Type</th>
            <td><%= wifiInfo.getX_SWIFI_CMCWR() %>
            </td>
        </tr>
        <tr>
            <th>Construction Year</th>
            <td><%= wifiInfo.getX_SWIFI_CNSTC_YEAR() %>
            </td>
        </tr>
        <tr>
            <th>Indoor/Outdoor</th>
            <td><%= wifiInfo.getX_SWIFI_INOUT_DOOR() %>
            </td>
        </tr>
        <tr>
            <th>Remarks</th>
            <td><%= wifiInfo.getX_SWIFI_REMARS3() %>
            </td>
        </tr>
        <tr>
            <th>Latitude</th>
            <td><%= wifiInfo.getLAT() %>
            </td>
        </tr>
        <tr>
            <th>Longitude</th>
            <td><%= wifiInfo.getLNT() %>
            </td>
        </tr>
        <tr>
            <th>Work Date</th>
            <td><%= wifiInfo.getWORK_DTTM() %>
            </td>
        </tr>
    </table>
    <br>

    </body>
    </html>
