/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsDeJuego;

import Objetos.LogToFile;
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
public class LoggerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        String archivo = (String)this.getServletContext().getInitParameter("rutaLog");
        new LogToFile(archivo);
    }

    @Override
    public void destroy() {
        LogToFile.cerrar();
    }

    

}
