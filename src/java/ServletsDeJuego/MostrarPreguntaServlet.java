/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import BaseDeDatos.OperacionesBaseDeDatos;
import Objetos.Pregunta;
import Objetos.Puntuacion;
import Objetos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Familia
 */
public class MostrarPreguntaServlet extends HttpServlet {

    String colorDeportes;
    String colorHistoria;
    String colorArte;
    String colorGeografia;
    String colorCiencias;
    String colorEspectaculos;
    String colorLiteratura;

    @Override
    public void init() throws ServletException {
        colorDeportes = this.getInitParameter("colorDeportes");
        colorHistoria = this.getInitParameter("colorHistoria");
        colorArte = this.getInitParameter("colorArte");
        colorGeografia = this.getInitParameter("colorGeografia");
        colorCiencias = this.getInitParameter("colorCiencias");
        colorEspectaculos = this.getInitParameter("colorEspectaculos");
        colorLiteratura = this.getInitParameter("colorLiteratura");
    }

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
            int totalDePreguntas = OperacionesBaseDeDatos.getNumeroDePreguntas();
            ArrayList<Integer> numeros;
            int totalPreguntasAMostrar = 0;
            if (request.getSession().getAttribute("preguntasAMostrar") != null) {
                totalPreguntasAMostrar = (int) request.getSession().getAttribute("preguntasAMostrar");
            } else {
                request.getSession().setAttribute("preguntasAMostrar", 5);
                totalPreguntasAMostrar = 5;
            }
            if (request.getSession().getAttribute("numeros") == null) {
                numeros = new ArrayList<>();
                request.getSession().setAttribute("numeros", numeros);
            } else {
                numeros = (ArrayList<Integer>) request.getSession().getAttribute("numeros");
            }
            if (request.getSession().getAttribute("preguntasRealizadas") == null) {
                request.getSession().setAttribute("preguntasRealizadas",0);
                request.getSession().setAttribute("preguntasTotales",5);
                request.getSession().setAttribute("preguntasRealizadasPorcentaje",0);
            }
            Random r = new Random();
            int pregunta = 0;
            boolean hayPreguntas = true;
            if (numeros.isEmpty()) {
                pregunta = r.nextInt(totalDePreguntas - (1) + 1) + 1;
                totalPreguntasAMostrar--;
            } else {
                pregunta = (int) request.getSession().getAttribute("idUltimaPregunta");
            }
            if (numeros.size() == totalDePreguntas || totalPreguntasAMostrar <= 0) {
                hayPreguntas = false;
            }
            while (numeros.contains(pregunta) && !numeros.isEmpty() && hayPreguntas) {
                pregunta = r.nextInt(totalDePreguntas - (1) + 1) + 1;
                totalPreguntasAMostrar--;
            }
            System.out.println("------" + pregunta);
            numeros.add(pregunta);
            request.getSession().setAttribute("idUltimaPregunta", pregunta);
            request.getSession().setAttribute("preguntasAMostrar", totalPreguntasAMostrar);
            request.getSession().setAttribute("numeros", numeros);
            Pregunta p = OperacionesBaseDeDatos.getPreguntaConId(pregunta);
            switch (OperacionesBaseDeDatos.getTemaConId(p.getTemaid())) {
                case "Deportes":
                    request.setAttribute("colorFondo", colorDeportes);
                    break;
                case "Historia":
                    request.setAttribute("colorFondo", colorHistoria);
                    break;
                case "Arte":
                    request.setAttribute("colorFondo", colorArte);
                    break;
                case "Geografía":
                    request.setAttribute("colorFondo", colorGeografia);
                    break;
                case "Ciencias y Naturaleza":
                    request.setAttribute("colorFondo", colorCiencias);
                    break;
                case "Espectáculos":
                    request.setAttribute("colorFondo", colorEspectaculos);
                    break;
                case "Literatura":
                    request.setAttribute("colorFondo", colorLiteratura);
                    break;
            }
            if (hayPreguntas) {
                System.out.println((int)request.getSession().getAttribute("preguntasAMostrar"));
                Iterator i = p.getRespuestas().entrySet().iterator();
                request.getSession().setAttribute("pregunta", p);
                request.getSession().setAttribute("respuestaCorrecta", p.getRespuestaCorrecta());
                while (i.hasNext()) {
                    Map.Entry par = (Map.Entry) i.next();
                    request.getSession().setAttribute("res" + par.getKey().toString().toUpperCase(), par.getValue());
                }
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/preguntas.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("/Trivial/FinalizarPartidaServlet");
                /*out.println("<h1>No hay preguntas</h1>");
                out.println("Tu puntuacion obtenidad es: " + request.getSession().getAttribute("puntuacion") + "<br>");
                out.println("Tu racha ha sido de : " + request.getSession().getAttribute("racha") + " preguntas");*/
                //Reset para poder volver a jugar
                /*request.getSession().removeAttribute("preguntasAMostrar");
                request.getSession().removeAttribute("idUltimaPregunta");
                request.getSession().removeAttribute("numeros");
                request.getSession().removeAttribute("preguntasRealizadas");
                request.getSession().removeAttribute("preguntasRealizadasPorcentaje");
                //Guardar resultados en la base de datos
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");
                Puntuacion puntuacion = new Puntuacion(u.getId(),
                        (int) request.getSession().getAttribute("puntuacion"),
                        (int) request.getSession().getAttribute("maxRacha"));
                OperacionesBaseDeDatos.insertarPuntuacion(puntuacion);
                //Resetear puntuaciones
                request.getSession().removeAttribute("puntuacion");
                request.getSession().removeAttribute("racha");
                request.getSession().removeAttribute("maxRacha");*/
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
