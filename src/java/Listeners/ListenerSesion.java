/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Objetos.LogToFile;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Familia
 */
public class ListenerSesion implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LogToFile.escribir("Sesión creada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LogToFile.escribir("Sesión destruida");
        
    }
    
}
