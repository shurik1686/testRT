<%-- 
    Document   : test
    Created on : Oct 3, 2020, 10:35:18 AM
    Author     : pop_av
--%>

<%@page import="ru.shurik1686.rttest.service.RTService"%>
<%@ page language="java" import="org.json.JSONArray" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% JSONArray arrayObj = new RTService().getJSON(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test RT</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <script type="text/javascript" src="app.js"></script>
    </head>
    <h1>${name}</h1>
    <div id="edits"></div>
        
    <table class="TableFix">
	<tr class="HeaderFix">
            <td class="c1">Номер</td>
            <td class="c2">Цвет</td>
        </tr>
    </table>
    <div class="DivCont">
<table class="TableFix">
        <%  for (int i = 0; i < arrayObj.length(); i++) {%>
        <tr> 
            <td class="c1" id="idc<%=i %>"><%= arrayObj.getJSONObject(i).getString("colorNumder")%></td>        
            <td class="c2" id="c<%=i %>" onmouseover="ChangeOverTd(this)" onmouseup="ChangeOverTd(this)"><%= arrayObj.getJSONObject(i).getString("nameColor")%></td>
        </tr>
        <%
            }
        %>
</table>
</div>    

</html>
