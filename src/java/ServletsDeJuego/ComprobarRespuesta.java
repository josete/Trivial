/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Portatil
 */
public class ComprobarRespuesta extends HttpServlet {

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
        //response.setContentType("application/json");
        String respuesta = request.getParameter("respuesta");
        String respuestaCorrecta = request.getSession().getAttribute("respuestaCorrecta").toString();
        //String returnJson = "";
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComprobarRespuesta</title>");            
            out.println("</head>");
            out.println("<body>");*/
            if (respuesta.equals(respuestaCorrecta)) {
                //out.println("<h1>Respuesta correcta</h1>");
                //RespuestasSeguidas
                if (request.getSession().getAttribute("racha") != null) {
                    request.getSession().setAttribute("racha", Integer.parseInt(request.getSession().getAttribute("racha").toString()) + 1);
                } else {
                    request.getSession().setAttribute("racha", 1);
                }
                //Sumar puntos
                if (request.getSession().getAttribute("puntuacion") != null) {
                    request.getSession().setAttribute("puntuacion", Integer.parseInt(request.getSession().getAttribute("puntuacion").toString()) + 10);
                } else {
                    request.getSession().setAttribute("puntuacion", 10);
                }
                request.getSession().setAttribute("correcion", "correcta");
                /*returnJson = "{'correccion':'correcta','puntos':+"+request.getSession().getAttribute("puntuacion")+",'racha'"+request.getSession().getAttribute("racha")+"}";
                out.println(returnJson);
		out.close();*/
 /* out.println("<h1>Puntuacion: "+request.getSession().getAttribute("puntuacion")+"</h1>");
                out.println("<h1>Racha:"+request.getSession().getAttribute("racha")+"</h1>");*/
            } else {
                //out.println("<h1>Respuesta incorrecta</h1>");
                if (request.getSession().getAttribute("racha") != null) {
                    request.getSession().removeAttribute("racha");
                }
                if (request.getSession().getAttribute("puntuacion") != null) {
                    request.getSession().setAttribute("puntuacion", Integer.parseInt(request.getSession().getAttribute("puntuacion").toString()) + 0);
                } else {
                    request.getSession().setAttribute("puntuacion", 0);
                }
                request.getSession().setAttribute("correcion", "incorrecta");
                /*returnJson = "{'correccion':'incorrecta','puntos':+"+request.getSession().getAttribute("puntuacion")+",'racha'"+request.getSession().getAttribute("racha")+"}";
                out.println(returnJson);
		out.close();*/
 /*out.println("<h1>Puntuacion: "+request.getSession().getAttribute("puntuacion")+"</h1>");
                out.println("<h1>Racha: 0</h1>");*/
            }
            request.getSession().setAttribute("preguntasRealizadas", (int) request.getSession().getAttribute("preguntasRealizadas") + 1);
            request.getSession().setAttribute("preguntasRealizadasPorcentaje",
                    (int) request.getSession().getAttribute("preguntasRealizadas") * 100 / (int) request.getSession().getAttribute("preguntasTotales"));
            //out.println("<a href='/Trivial/MostrarPreguntaServlet'>Siguiente pregunta</a>");
            request.getSession().removeAttribute("respuestaCorrecta");
            response.sendRedirect("/Trivial/MostrarPreguntaServlet");
            /*RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Trivial/MostrarPreguntaServlet");
            dispatcher.forward(request, response);*/
            /*out.println("</body>");
            out.println("</html>");*/
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
