<%-- 
    Document   : esperarTurno
    Created on : 19-dic-2017, 11:02:04
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3;">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/preguntas.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/preguntas.js"></script>
        <title>Espera de turno</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <c:if test="${sala.turno ne usuario.id}">
                <h1>Esperando tu turno</h1>
            </c:if>
            <c:if test="${sala.turno eq usuario.id}">
                <script>redirigir("/Trivial/MostrarPreguntaMultijugadorServlet")</script>
            </c:if>
        </div>
    </body>
</html>
