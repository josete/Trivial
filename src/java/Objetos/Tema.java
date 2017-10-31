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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Portatil
 */
public class Tema implements Insertable {

    int id;
    String nombre;

    public Tema(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Tema(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int insertarEnBaseDeDatos() {
        int auto_id=-1;
        if (comprobarSiExiste()==-1) {
            String sql = "insert into temas (nombre) values(?)";
            try {
                PreparedStatement preparedStatement = ConexionBaseDeDatos.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, nombre);
                preparedStatement.executeUpdate();
                //Consigue el ultimo id insertado
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                auto_id = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            auto_id = comprobarSiExiste();//Cambiar
        }
        return auto_id;
    }

    private int comprobarSiExiste() {
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
