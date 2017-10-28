/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import BaseDeDatos.ConexionBaseDeDatos;
import BaseDeDatos.Insertable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Portatil
 */
public class Tema implements Insertable{
    
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
    public void insertarEnBaseDeDatos() {
        if(!comprobarSiExiste()){
            String sql = "insert into temas nombre values('"+nombre+"')";
            try {
                Statement s = ConexionBaseDeDatos.connection.createStatement();
                s.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean comprobarSiExiste(){
        boolean existe = false;
        try {
            String sql = "Select * from temas where nombre ="+nombre;
            Statement s = ConexionBaseDeDatos.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            existe = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Tema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    
}
