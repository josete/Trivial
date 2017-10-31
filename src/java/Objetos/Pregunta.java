/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import BaseDeDatos.ConexionBaseDeDatos;
import BaseDeDatos.Insertable;
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
public class Pregunta implements Insertable {

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
        temaid = t.insertarEnBaseDeDatos();
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

    public void insertarRespuestas() {
        String sql = "insert into respuestas (respuesta,letraRespuesta,Preguntas_idPreguntas) values(?,?,?)";
        Iterator i = respuestas.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry par = (Map.Entry)i.next();
            try {
                PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, par.getValue().toString());
                preparedStatement.setString(2, par.getKey().toString());
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                i.remove();
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int insertarEnBaseDeDatos() {
        int auto_id = -1;
        String sql = "insert into preguntas (pregunta,Temas_idTemas,respuestaCorrectaLetra) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pregunta);
            preparedStatement.setInt(2, temaid);
            preparedStatement.setString(3, respuestaCorrecta);
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            id = auto_id;
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_id;
    }

}
