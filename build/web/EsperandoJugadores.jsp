<%-- 
    Document   : EsperandoJugadores
    Created on : 18-dic-2017, 10:11:46
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5;">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/preguntas.css">
        <link rel="stylesheet" href="css/sala.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/preguntas.js"></script>
        <title>Esperando ...</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <c:if test="${fn:length(sala.usuarios) eq fn:length(sala.listo)}">
                <script>redirigir("/Trivial/MostrarPreguntaMultijugadorServlet")</script>
            </c:if>
        </div>
    </body>
</html>
