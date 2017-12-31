/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

/**
 *
 * @author Familia
 */
public class LogToFile {

    static File archivo;
    static FileWriter fichero = null;
    static PrintWriter pw = null;

    public LogToFile(String nombreArchivo) {
        archivo = new File(nombreArchivo);
        System.out.println(archivo.getAbsolutePath());
        try {
            fichero = new FileWriter(archivo);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LogToFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw = new PrintWriter(fichero);
    }

    public static void escribir(String mensaje) {
        pw.println(mensaje);
    }

    public static void cerrar() {
        try {
            if (null != fichero) {
                fichero.close();
            }
        } catch (Exception e) {
            escribir(e.toString());
        }
    }

}
