<%@ page import="API.DTOWifi" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="API.DbService" %>
<%@ page import="API.APIJsonReceive" %><%--
  Created by IntelliJ IDEA.
  User: byeonsanghwa
  Date: 2024. 7. 9.
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DbService dbService = DbService.getInstance();
    APIJsonReceive apiJsonReceive = new APIJsonReceive();

    DTOWifi dtoWifi = new DTOWifi();


    int num =0;
    int sum =0;
    while (dtoWifi !=null) { // api data 끝이 도달하면 null을 생성
        dtoWifi = apiJsonReceive.getDataContainer(apiJsonReceive.JsonCreate(1+1000*num, 1000*(num+1)));
        num++;
        if (dtoWifi != null) {
            dbService.dbWifiInsert(dtoWifi);
            sum+= dtoWifi.getTbPublicWifiInfo().getList().size();
        }


    }
%>
<html>
<head>



</head>
<body>
<h1><%=sum%>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
</body>
</html>
