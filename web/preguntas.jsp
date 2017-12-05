<%-- 
    Document   : preguntas
    Created on : 05-dic-2017, 12:02:40
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/preguntas.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Preguntas</title>
    </head>
    <body style="background-color: ${colorFondo}">
        <%@include file="header.html"%>
        <div class="contenedorPreguntas">
            <p>${pregunta.pregunta}</p><br>
            <a class="btn btn-lg btn-default" href="/Trivial/ComprobarRespuesta?respuesta=a">${resA}</a><br><br>
            <a class="btn btn-lg btn-default" href="/Trivial/ComprobarRespuesta?respuesta=b">${resB}</a><br><br>
            <a class="btn btn-lg btn-default" href="/Trivial/ComprobarRespuesta?respuesta=c">${resC}</a><br><br>
            <a class="btn btn-lg btn-default" href="/Trivial/ComprobarRespuesta?respuesta=d">${resD}</a><br><br>
        </div>
    </body>
</html>
