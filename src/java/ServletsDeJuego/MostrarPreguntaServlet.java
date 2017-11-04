/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import BaseDeDatos.OperacionesBaseDeDatos;
import Objetos.Pregunta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Familia
 */
public class MostrarPreguntaServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int totalDePreguntas = OperacionesBaseDeDatos.getNumeroDePreguntas();
            ArrayList<Integer> numeros;
            if(request.getSession().getAttribute("numeros")==null){
                numeros = new ArrayList<>();
                request.getSession().setAttribute("numeros", numeros);
            }else{
                numeros = (ArrayList<Integer>)request.getSession().getAttribute("numeros");
            }
            Random r = new Random();
            int pregunta = 0;
            boolean hayPreguntas = true;
            if(numeros.isEmpty()){
                pregunta = r.nextInt(totalDePreguntas -(1)+1) + 1;
            }
            if(numeros.size()==totalDePreguntas){
                hayPreguntas=false;
            }
            if(pregunta==0){
                pregunta = r.nextInt(totalDePreguntas -(1)+1) + 1;
            }
            while(numeros.contains(pregunta) && !numeros.isEmpty()&&hayPreguntas){
                pregunta = r.nextInt(totalDePreguntas -(1)+1) + 1;    
            }
            System.out.println("------"+pregunta);
            numeros.add(pregunta);
            request.getSession().setAttribute("numeros", numeros);
            Pregunta p = OperacionesBaseDeDatos.getPreguntaConId(pregunta);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pregunta</title>");
            out.println("</head>");
            out.println("<body>");
            if(hayPreguntas){
                out.println("<h1>" + p.getPregunta() + "</h1>");
                Iterator i = p.getRespuestas().entrySet().iterator();
                request.getSession().setAttribute("respuestaCorrecta", p.getRespuestaCorrecta());
                out.println("<form action='/Trivial/ComprobarRespuesta' method='post'>");
                while (i.hasNext()) {
                    Map.Entry par = (Map.Entry) i.next();
                    out.println("<input type='radio' name='respuesta' value='"+par.getKey()+"'>" + par.getKey()+")"+par.getValue() + "</input>");
                }
                out.println("<br><br><input type='submit' value='Contestar'>");
            }else{
                out.println("<h1>No hay preguntas</h1>");
            }
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
