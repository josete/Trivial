/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Portatil
 */
public class Sala {
    
    int id;
    ArrayList<Usuario> usuarios;
    ArrayList<Tema> temas;

    public Sala(int id) {
        this.id = id;
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
    }

    public Sala() {
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
    }
    
    public void anadirUsuario(Usuario u){
        this.usuarios.add(u);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    
    
    
    
}