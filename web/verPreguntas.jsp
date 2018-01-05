<%-- 
    Document   : verPreguntas
    Created on : 05-ene-2018, 17:20:33
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/preguntas.css">
        <link rel="stylesheet" href="css/sala.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/preguntas.js"></script>
        <title>Ver preguntas</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <c:forEach items="${preguntas}" var="item">
                <h4>${item}</h4>
            </c:forEach>
        </div>
        <br>
        <br>
    </body>
</html>
