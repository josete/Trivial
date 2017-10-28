/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import BaseDeDatos.Insertable;
import java.util.Map;

/**
 *
 * @author Portatil
 */
public class Pregunta implements Insertable{
    
    int id;
    String pregunta;
    //Es la letra de la respuesta
    String respuestaCorrecta;
    int temaid;
    String tema;
    Map<String,String> respuestas;

    public Pregunta(int id, String pregunta, String respuestaCorrecta, int tema, Map<String, String> respuestas) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.temaid = tema;
        this.respuestas = respuestas;
    }

    public Pregunta(String pregunta, String respuestaCorrecta, String tema, Map<String, String> respuestas) {
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.tema = tema;
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

    @Override
    public void insertarEnBaseDeDatos() {
        //Insertar tema si no existe
        /*String sql = "insert into preguntas (pregunta,Temas_idTemas,respuestaCorrecta) values '"+pregunta+"',"+
                algo+",'"+respuestaCorrecta+"'";*/
    }
    
    
}
