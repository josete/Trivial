<%-- 
    Document   : nuevaPregunta
    Created on : 04-ene-2018, 17:04:42
    Author     : Familia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Insertar pregunta</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/preguntas.css">
        <link rel="stylesheet" href="css/index.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="contenedor">
            <form action="/Trivial/GestorPreguntasServlet" method="POST">
                <div class="form-group">
                    <label for="pregunta">Pregunta:</label>
                    <input type="text" class="form-control" id="pregunta" name="pregunta">
                </div>
                <div class="form-group">
                    <label for="respuestaCorrecta">Respuesta correcta:</label>
                    <input type="text" class="form-control" id="respuestaCorrecta" name="respuestaCorrecta">
                </div>
                <div class="form-group">
                    <label for="respuestaA">Respuesta A:</label>
                    <input type="text" class="form-control" id="respuestaA" name="respuestaA">
                </div>
                <div class="form-group">
                    <label for="respuestaB">Respuesta B:</label>
                    <input type="text" class="form-control" id="respuestaB" name="respuestaB">
                </div>
                <div class="form-group">
                    <label for="respuestaC">Respuesta C:</label>
                    <input type="text" class="form-control" id="respuestaC" name="respuestaC">
                </div>
                <div class="form-group">
                    <label for="respuestaD">Respuesta D:</label>
                    <input type="text" class="form-control" id="respuestaD" name="respuestaD">
                </div>
                <div class="form-group">
                    <label for="tema">Tema:</label>
                    <input type="text" class="form-control" id="tema" name="tema">
                </div>
                <input type="submit" class="btn btn-default" value="Crear pregunta">
            </form>
        </div>
        <br>
        <br>
    </body>
</html>

