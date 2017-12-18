<%-- 
    Document   : sala
    Created on : 09-dic-2017, 18:18:46
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <title>Sala ${sala.id}</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <h2>Sala ${sala.id}</h2>
            <a class="btn btn-lg btn-default" onclick="refrescar()">Refrescar jugadores</a>
            <div class="jugadores">
                <h4>Jugadores</h4>
                <c:forEach items="${sala.usuarios}" var="item">
                    ${item.nombre}<br>
                </c:forEach>
            </div>
            <br>
            <c:if test="${fn:length(sala.usuarios) gt 1}">
                <a class="btn btn-lg btn-default" href="/Trivial/MultijugadorServlet">Listo</a>
            </c:if>            
        </div>
    </body>
</html>
