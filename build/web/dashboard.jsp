<%-- 
    Document   : dashboard
    Created on : 05-dic-2017, 10:55:53
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
        <link rel="stylesheet" href="css/dashboard.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Dashboard</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="botones">
            <a href="/Trivial/MostrarPreguntaServlet" class="btn btn-lg btn-default">Solitario</a>
            <a href="/Trivial/CrearSala" class="btn btn-lg btn-default">Crear Sala</a>
            <a data-toggle="modal" data-target="#unirseSala" class="btn btn-lg btn-default">Unirse a Sala</a>
            <c:if test="${usuario.nombre eq 'admin'}">                
                <a href="/Trivial/nuevaPregunta.jsp" class="btn btn-lg btn-default">Insertar Nueva Pregunta</a>
                <a href="/Trivial/GestorPreguntasServlet" class="btn btn-lg btn-default">Ver preguntas</a>
            </c:if>
        </div>
        <div id="unirseSala" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Unirse a sala</h4>
                    </div>
                    <div class="modal-body">
                        <form action="/Trivial/UnirseASala" method="POST">
                            <div class="form-group">
                                <label for="idSala">Id de la sala:</label>
                                <input type="text" class="form-control" id="idSala" name="idSala">
                            </div>
                            <button type="submit" class="btn btn-default">Unirse</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
