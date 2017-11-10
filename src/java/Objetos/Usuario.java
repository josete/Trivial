/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Portatil
 */
public class Usuario {
    
    int id;
    String nombre;
    String passsword;
    String email;

    public Usuario(int id, String nombre, String passsword, String email) {
        this.id = id;
        this.nombre = nombre;
        this.passsword = passsword;
        this.email = email;
    }

    public Usuario(String nombre, String passsword, String email) {
        this.nombre = nombre;
        this.passsword = passsword;
        this.email = email;
    }

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
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

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
