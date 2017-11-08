/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Pregunta;
import Objetos.Tema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class OperacionesBaseDeDatos {

    public static int getNumeroDePreguntas() {
        int cantidad = 0;
        try {
            String sql = "Select count(*) from preguntas";
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public static Pregunta getPreguntaConId(int id) {
        Pregunta p = null;
        try {
            String sql = "Select * from preguntas where idPreguntas = ?";
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                p = new Pregunta(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3));
                p.setRespuestas(getRespuestasDePregunta(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    private static Map<String, String> getRespuestasDePregunta(int id) {
        Map<String, String> respuestas = new HashMap<>();
        try {
            String sql = "Select * from respuestas where Preguntas_idPreguntas = ?";
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                respuestas.put(rs.getString(3), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestas;
    }
    
     public static void insertarRespuestas(Pregunta pregunta) {
        String sql = "insert into respuestas (respuesta,letraRespuesta,Preguntas_idPreguntas) values(?,?,?)";
        Iterator i = pregunta.getRespuestas().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry par = (Map.Entry)i.next();
            try {
                PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, par.getValue().toString());
                preparedStatement.setString(2, par.getKey().toString());
                preparedStatement.setInt(3, pregunta.getId());
                preparedStatement.executeUpdate();
                i.remove();
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int insertarPregunta(Pregunta pregunta) {
        int auto_id = -1;
        String sql = "insert into preguntas (pregunta,Temas_idTemas,respuestaCorrectaLetra) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pregunta.getPregunta());
            preparedStatement.setInt(2, pregunta.getTemaid());
            preparedStatement.setString(3, pregunta.getRespuestaCorrecta());
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            pregunta.setId(auto_id);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_id;
    }
    
     public static int insertarTema(Tema tema) {
        int auto_id=-1;
        if (comprobarSiExiste(tema.getNombre())==-1) {
            String sql = "insert into temas (nombre) values(?)";
            try {
                PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, tema.getNombre());
                preparedStatement.executeUpdate();
                //Consigue el ultimo id insertado
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                auto_id = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            auto_id = comprobarSiExiste(tema.getNombre());//Cambiar
        }
        return auto_id;
    }
     
     private static int comprobarSiExiste(String nombre) {
        int id = -1;
        try {
            String sql = "Select * from temas where nombre = ?";
            PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
