/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import BaseDeDatos.OperacionesBaseDeDatos;
import Objetos.Puntuacion;
import Objetos.Sala;
import Objetos.Usuario;
import java.io.IOException;
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
public class FinalizarPartidaMultijugadorServlet extends HttpServlet {

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
        //Resetear valores de la conexion
        request.getSession().removeAttribute("preguntasAMostrar");
        request.getSession().removeAttribute("idUltimaPregunta");
        request.getSession().removeAttribute("numeros");
        request.getSession().removeAttribute("preguntasRealizadas");
        request.getSession().removeAttribute("preguntasRealizadasPorcentaje");
        request.getSession().removeAttribute("correcion");
        //Guardar resultados en la base de datos
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Puntuacion puntuacion;
        if(request.getSession().getAttribute("puntuacion")!=null){
        puntuacion = new Puntuacion(u.getId(),
                (int) request.getSession().getAttribute("puntuacion"),
                (int) request.getSession().getAttribute("maxRacha"));
        }else{
            puntuacion = new Puntuacion(u.getId(),0,0);
        }
        OperacionesBaseDeDatos.insertarPuntuacion(puntuacion);
        //Almacenar valores en las cookies para poder mostrarlos
        Cookie[] cookies = request.getCookies();
        boolean esta = false;
        for (Cookie c : cookies) {
            if (c.getName().equals("puntuacion")) {
                c.setValue(String.valueOf(request.getSession().getAttribute("puntuacion")));
                response.addCookie(c);
                esta = true;
            }
            if (c.getName().equals("maxRacha")) {
                c.setValue(String.valueOf(request.getSession().getAttribute("maxRacha")));
                response.addCookie(c);
                esta = true;
            }
        }
        if (!esta) {
            Cookie puntuacionCookie = new Cookie("puntuacion", String.valueOf(request.getSession().getAttribute("puntuacion")));
            Cookie maxRachaCookie = new Cookie("maxRacha", String.valueOf(request.getSession().getAttribute("maxRacha")));
            response.addCookie(puntuacionCookie);
            response.addCookie(maxRachaCookie);
        }
        //Pasar turno
        Sala s = (Sala) request.getSession().getAttribute("sala");
        s.correrTurno();
        request.getSession().setAttribute("ganador", s.getGanador());
        //Resetear puntuaciones
        request.getSession().removeAttribute("puntuacion");
        request.getSession().removeAttribute("racha");
        request.getSession().removeAttribute("maxRacha");
        request.getSession().removeAttribute("sala");
        //Redirigir al menu de decision con la puntuacion total
        response.sendRedirect("/Trivial/finalizarMultijugador.jsp");
        /*RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/finalizarMultijugador.jsp");
        dispatcher.forward(request, response);*/
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
