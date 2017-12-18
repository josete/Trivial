/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Portatil
 */
public class Sala {
    
    int id;
    ArrayList<Usuario> usuarios;
    ArrayList<Tema> temas;
    Map<Integer,Boolean> listo;
    int turno;
    int pasos = -1;
    

    public Sala(int id) {
        this.id = id;
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
        listo = new HashMap<>();
    }

    public Sala() {
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
        listo = new HashMap<>();
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
    
    public void listo(Usuario u){        
        for(Usuario us: usuarios){
            if(us.getId()==u.getId()){
                listo.put(usuarios.indexOf(us), Boolean.TRUE);
            }
        }
    }
    
    public void correrTurno(){
        pasos++;
        if(pasos>usuarios.size()){
            pasos=-1;
        }
        turno = usuarios.get(pasos).getId();
    }

    public Map<Integer, Boolean> getListo() {
        return listo;
    }

    public void setListo(Map<Integer, Boolean> listo) {
        this.listo = listo;
    } 

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    
    
}