/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import BaseDeDatos.ConexionBaseDeDatos;
import BaseDeDatos.OperacionesBaseDeDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Portatil
 */
public class Pregunta{

    int id;
    String pregunta;
    //Es la letra de la respuesta
    String respuestaCorrecta;
    int temaid;
    Map<String, String> respuestas;

    public Pregunta(int id, String pregunta, String respuestaCorrecta, int tema, Map<String, String> respuestas) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.temaid = tema;
        this.respuestas = respuestas;
    }
    
    public Pregunta(int id, String pregunta, String respuestaCorrecta, int tema) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.temaid = tema;
    }

    public Pregunta(String pregunta, String respuestaCorrecta, String tema, Map<String, String> respuestas) {
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        Tema t = new Tema(tema);
        temaid=OperacionesBaseDeDatos.insertarTema(t);
        //temaid = t.insertarEnBaseDeDatos();
        this.respuestas = respuestas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getTemaid() {
        return temaid;
    }

    public void setTema(int tema) {
        this.temaid = tema;
    }

    public Map<String, String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<String, String> respuestas) {
        this.respuestas = respuestas;
    }
}
