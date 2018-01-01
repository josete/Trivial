/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import BaseDeDatos.OperacionesBaseDeDatos;
import Objetos.Pregunta;
import Objetos.Sala;
import Objetos.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Familia
 */
public class MultijugadorServlet extends HttpServlet {

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

        Sala s = (Sala) request.getSession().getAttribute("sala");
        s.listo((Usuario) request.getSession().getAttribute("usuario"));
        //Se seleccionan preguntas al azar de la base de datos
        if (s.getPreguntasCola() == null) {
            int totalDePreguntas = OperacionesBaseDeDatos.getNumeroDePreguntas();
            ArrayList<Integer> numeros = new ArrayList<>();
            Random r = new Random();
            int numeroPreguntas = 5 * s.getUsuarios().size();
            for (int i = 0; i < numeroPreguntas; i++) {
                int idPregunta = r.nextInt(totalDePreguntas - (1) + 1) + 1;
                while (numeros.contains(idPregunta)) {
                    idPregunta = r.nextInt(totalDePreguntas - (1) + 1) + 1;
                }
                numeros.add(idPregunta);
                Pregunta pregunta = OperacionesBaseDeDatos.getPreguntaConId(idPregunta);
                s.anadirPregunta(pregunta);
            }
            s.convertirACola();
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/EsperandoJugadores.jsp");
        dispatcher.forward(request, response);
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
