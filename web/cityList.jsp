<%@ page import="java.util.List" %>
<%@ page import="pl.adamLupinski.entity.City" %><%--
  Created by IntelliJ IDEA.
  User: azlup
  Date: 21.02.2019
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cities</title>
</head>
<body>
<h1>City list:</h1>
<table style="width: 70%" border="1">
    <tr>
        <th>Name</th>
        <th>Population</th>
    </tr>
    <%
        List<City> cityList = (List<City>)request.getAttribute("cities");
        if (cityList != null) {
            for (City city: cityList){
     %>
        <tr>
            <td><%= city.getName()%></td>
            <td><%= city.getPopulation()%></td>
        </tr>
    <%
            }
        }
    %>
</table>


</body>
</html>
