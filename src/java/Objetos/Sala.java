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
    ArrayList<Usuario> finalizada;
    int turno;
    int pasos = 0;
    

    public Sala(int id) {
        this.id = id;
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
        listo = new HashMap<>();
        finalizada = new ArrayList<>();
    }

    public Sala() {
        usuarios = new ArrayList<>();
        temas = new ArrayList<>();
        listo = new HashMap<>();
        finalizada = new ArrayList<>();
    }
    
    public void anadirUsuario(Usuario u){
        this.usuarios.add(u);
        turno = usuarios.get(0).getId();
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
        if(pasos>=usuarios.size()){
            pasos=0;
        }
        if(finalizada.contains(usuarios.get(pasos))){
            correrTurno();
        }else{
            turno = usuarios.get(pasos).getId();
        }
    }
    
    public void eliminarJugadorDeLaLista(Usuario u){
        finalizada.add(u);
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