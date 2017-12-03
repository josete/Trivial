/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Objetos.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Familia
 */
public class FiltroLogin implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Inicializo el filtro");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Atiendo la peticion del filtro");
        HttpServletRequest r = (HttpServletRequest)request;
        if((Usuario)r.getSession().getAttribute("usuario")!=null){
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destruyo el filtro");
    }
    
}
