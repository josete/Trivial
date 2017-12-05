<%-- 
    Document   : dashboard
    Created on : 05-dic-2017, 10:55:53
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/dashboard.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Dashboard</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="botones">
            <a href="/Trivial/MostrarPreguntaServlet" class="btn btn-lg btn-default">Soltiario</a>
            <a class="btn btn-lg btn-default">Buscar contrincante</a>
            <a class="btn btn-lg btn-default">Crear Sala</a>
        </div>
    </body>
</html>
