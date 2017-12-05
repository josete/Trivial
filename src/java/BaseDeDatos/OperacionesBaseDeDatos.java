/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Objetos.Pregunta;
import Objetos.Puntuacion;
import Objetos.Sala;
import Objetos.Tema;
import Objetos.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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

    private static void cerrarRecursos(ResultSet rs, PreparedStatement ps, Connection c) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Operaciones con preguntas">
    public static int getNumeroDePreguntas() {
        int cantidad = 0;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            String sql = "Select count(*) from preguntas";
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return cantidad;
    }

    public static Pregunta getPreguntaConId(int id) {
        Pregunta p = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            String sql = "Select * from preguntas where idPreguntas = ?";
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                p = new Pregunta(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3));
                p.setRespuestas(getRespuestasDePregunta(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return p;
    }

    private static Map<String, String> getRespuestasDePregunta(int id) {
        Map<String, String> respuestas = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            String sql = "Select * from respuestas where Preguntas_idPreguntas = ?";
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                respuestas.put(rs.getString(3), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return respuestas;
    }

    public static void insertarRespuestas(Pregunta pregunta) {
        String sql = "insert into respuestas (respuesta,letraRespuesta,Preguntas_idPreguntas) values(?,?,?)";
        Iterator i = pregunta.getRespuestas().entrySet().iterator();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        while (i.hasNext()) {
            Map.Entry par = (Map.Entry) i.next();
            try {
                c = ConexionBaseDeDatos.getConexion();
                preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, par.getValue().toString());
                preparedStatement.setString(2, par.getKey().toString());
                preparedStatement.setInt(3, pregunta.getId());
                preparedStatement.executeUpdate();
                i.remove();
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cerrarRecursos(rs, preparedStatement, c);
            }
        }
    }

    public static int insertarPregunta(Pregunta pregunta) {
        int auto_id = -1;
        String sql = "insert into preguntas (pregunta,Temas_idTemas,respuestaCorrectaLetra) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pregunta.getPregunta());
            preparedStatement.setInt(2, pregunta.getTemaid());
            preparedStatement.setString(3, pregunta.getRespuestaCorrecta());
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            pregunta.setId(auto_id);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return auto_id;
    }

    public static int insertarTema(Tema tema) {
        int auto_id = -1;
        if (comprobarSiExiste(tema.getNombre()) == -1) {
            String sql = "insert into temas (nombre) values(?)";
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            Connection c = null;
            try {
                c = ConexionBaseDeDatos.getConexion();
                preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, tema.getNombre());
                preparedStatement.executeUpdate();
                //Consigue el ultimo id insertado
                rs = preparedStatement.getGeneratedKeys();
                rs.next();
                auto_id = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cerrarRecursos(rs, preparedStatement, c);
            }
        } else {
            auto_id = comprobarSiExiste(tema.getNombre());//Cambiar
        }
        return auto_id;
    }

    private static int comprobarSiExiste(String nombre) {
        int id = -1;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            String sql = "Select * from temas where nombre = ?";
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return id;
    }
    
    public static String getTemaConId(int idTema) {
        String tema = "";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            String sql = "Select * from temas where idTemas = ?";
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, idTema);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tema = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return tema;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Operaciones con usuarios">

    public static int insertarUsuario(Usuario usuario) {
        int auto_id = -1;
        String sql = "insert into usuarios (nombre,password,email) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getPasssword());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            usuario.setId(auto_id);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return auto_id;
    }

    public static Usuario getUsuario(String nombre, String contrasena) {
        Usuario u = null;
        MessageDigest md;
        StringBuffer sb = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            contrasena += "misupersalt";
            md = MessageDigest.getInstance("SHA-256");
            md.update(contrasena.getBytes());
            byte byteData[] = md.digest();
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NuevoUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from usuarios where nombre=? and password=?";
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, sb.toString());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return u;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Operaciones con salas">
    public static int insertarSala(Sala sala) {
        int auto_id = -1;
        String sql = "insert into partidas (idPartidas) values (null)";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            sala.setId(auto_id);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return auto_id;
    }
    public static int insertarUsuarioEnSala(Sala sala, Usuario u) {
        int auto_id = -1;
        String sql = "insert into usuariosenpartida (Usuarios_idUsuarios,Partidas_idPartidas) values (?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.setInt(1, sala.getId());
            preparedStatement.setInt(2, u.getId());
            //Consigue el ultimo id insertado
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            sala.setId(auto_id);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return auto_id;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Operaciones con puntuaciones">
    public static int insertarPuntuacion(Puntuacion puntuacion) {
        int auto_id = -1;
        String sql = "insert into puntuaciones (id_jugador,max_puntuacion,max_racha) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection c = null;
        try {
            c = ConexionBaseDeDatos.getConexion();
            preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, puntuacion.getIdJugador());
            preparedStatement.setInt(2, puntuacion.getPuntuacion());
            preparedStatement.setInt(3, puntuacion.getRacha());
            preparedStatement.executeUpdate();
            //Consigue el ultimo id insertado
            rs = preparedStatement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos(rs, preparedStatement, c);
        }
        return auto_id;
    }
    //</editor-fold>
}
