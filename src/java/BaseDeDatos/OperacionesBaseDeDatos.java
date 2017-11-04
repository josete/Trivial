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
import java.util.HashMap;
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
}
