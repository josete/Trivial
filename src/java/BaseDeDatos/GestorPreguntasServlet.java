/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Pregunta;
import Objetos.Tema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Portatil
 */
public class GestorPreguntasServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> preguntas = OperacionesBaseDeDatos.getTodasPreguntas();
        request.setAttribute("preguntas", preguntas);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/verPreguntas.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pregunta = request.getParameter("pregunta");
        String respuestaCorrecta = request.getParameter("respuestaCorrecta");
        String respuestaA = request.getParameter("respuestaA");
        String respuestaB = request.getParameter("respuestaB");
        String respuestaC = request.getParameter("respuestaC");
        String respuestaD = request.getParameter("respuestaD");
        String tema = request.getParameter("tema");
        Map<String, String> respuestas = new HashMap<>();
        respuestas.put("a", respuestaA);
        respuestas.put("b", respuestaB);
        respuestas.put("c", respuestaC);
        respuestas.put("d", respuestaD);
        Pregunta p = new Pregunta(pregunta, respuestaCorrecta, tema, respuestas);
        OperacionesBaseDeDatos.insertarPregunta(p);
        OperacionesBaseDeDatos.insertarRespuestas(p);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/insercionCorrecta.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
