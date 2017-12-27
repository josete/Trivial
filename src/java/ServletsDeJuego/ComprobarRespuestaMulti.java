/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import Objetos.Sala;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Familia
 */
public class ComprobarRespuestaMulti extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String respuesta = request.getParameter("respuesta");
        String respuestaCorrecta = request.getSession().getAttribute("respuestaCorrecta").toString();
        try (PrintWriter out = response.getWriter()) {
            if (respuesta.equals(respuestaCorrecta)) {
                //RespuestasSeguidas
                if (request.getSession().getAttribute("racha") != null) {
                    request.getSession().setAttribute("racha", Integer.parseInt(request.getSession().getAttribute("racha").toString()) + 1);
                    if ((int) request.getSession().getAttribute("racha") > (int) request.getSession().getAttribute("maxRacha")) {
                        request.getSession().setAttribute("maxRacha", (int) request.getSession().getAttribute("racha"));
                    }
                } else {
                    request.getSession().setAttribute("racha", 1);
                    request.getSession().setAttribute("maxRacha", 1);
                }
                //Sumar puntos
                if (request.getSession().getAttribute("puntuacion") != null) {
                    request.getSession().setAttribute("puntuacion", Integer.parseInt(request.getSession().getAttribute("puntuacion").toString()) + 10);
                } else {
                    request.getSession().setAttribute("puntuacion", 10);
                }
                request.getSession().setAttribute("correcion", "correcta");
            } else {
                if (request.getSession().getAttribute("racha") != null) {
                    if ((int) request.getSession().getAttribute("racha") > (int) request.getSession().getAttribute("maxRacha")) {
                        request.getSession().setAttribute("maxRacha", (int) request.getSession().getAttribute("racha"));
                    }
                    request.getSession().setAttribute("racha", 0);
                } else {
                    request.getSession().setAttribute("racha", 0);
                    request.getSession().setAttribute("maxRacha", 0);
                }
                if (request.getSession().getAttribute("puntuacion") != null) {
                    request.getSession().setAttribute("puntuacion", Integer.parseInt(request.getSession().getAttribute("puntuacion").toString()) + 0);
                } else {
                    request.getSession().setAttribute("puntuacion", 0);
                }
                request.getSession().setAttribute("correcion", "incorrecta");
                Sala s = (Sala)request.getSession().getAttribute("sala");
                s.correrTurno();
            }
            Sala s = (Sala)request.getSession().getAttribute("sala");
            s.siguientePregunta();
            request.getSession().setAttribute("preguntasRealizadas", (int) request.getSession().getAttribute("preguntasRealizadas") + 1);
            request.getSession().setAttribute("preguntasRealizadasPorcentaje",
                    (int) request.getSession().getAttribute("preguntasRealizadas") * 100 / (int) request.getSession().getAttribute("preguntasTotales"));
            request.getSession().removeAttribute("respuestaCorrecta");
            response.sendRedirect("/Trivial/esperarTurno.jsp");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
