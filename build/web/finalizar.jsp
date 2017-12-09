<%-- 
    Document   : finalizar
    Created on : 09-dic-2017, 12:36:29
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
        <title>Finalizar partida</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <a class="btn btn-lg btn-default ancho" href="/Trivial/dashboard.jsp">Menu principal</a><br><br>
            <a class="btn btn-lg btn-default ancho" href="/Trivial/MostrarPreguntaServlet">Volver a jugar</a><br>
            <div>
                <h2>Resultados: </h2>
                <h4>${cookie.puntuacion.value} puntos</h4>
                <h4>Racha maxima de ${cookie.maxRacha.value} preguntas</h4>
            </div>
        </div>
    </body>
</html>
