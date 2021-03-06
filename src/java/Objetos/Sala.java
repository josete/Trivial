/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Portatil
 */
public class Sala {

    int id;
    ArrayList<Usuario> usuarios;
    ArrayList<Pregunta> preguntas;
    Map<Integer, Boolean> listo;
    ArrayList<Usuario> finalizada;
    Queue<Pregunta> preguntasCola;
    int turno;
    int pasos = 0;
    boolean bloqueada = false;
    int preguntaActual = 0;
    Map<String, Integer> puntuaciones;

    public Sala(int id) {
        this.id = id;
        usuarios = new ArrayList<>();
        preguntas = new ArrayList<>();
        listo = new HashMap<>();
        finalizada = new ArrayList<>();
        puntuaciones = new HashMap<>();
    }

    public Sala() {
        usuarios = new ArrayList<>();
        preguntas = new ArrayList<>();
        listo = new HashMap<>();
        finalizada = new ArrayList<>();
        puntuaciones = new HashMap<>();
    }

    public void anadirUsuario(Usuario u) {
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

    public void listo(Usuario u) {
        for (Usuario us : usuarios) {
            if (us.getId() == u.getId()) {
                listo.put(usuarios.indexOf(us), Boolean.TRUE);
            }
        }
    }

    public void correrTurno() {
        pasos++;
        if (pasos >= usuarios.size()) {
            pasos = 0;
        }
        if (finalizada.contains(usuarios.get(pasos))) {
            correrTurno();
        } else {
            turno = usuarios.get(pasos).getId();
        }
    }

    public void eliminarJugadorDeLaLista(Usuario u) {
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

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void anadirPregunta(Pregunta p) {
        preguntas.add(p);
    }

    public void convertirACola() {
        preguntasCola = new LinkedList<>(preguntas);
    }

    public Queue<Pregunta> getPreguntasCola() {
        return preguntasCola;
    }

    public void setPreguntasCola(Queue<Pregunta> preguntasCola) {
        this.preguntasCola = preguntasCola;
    }

    public Pregunta getSiguientePregunta() {
        return preguntasCola.poll();
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public void bloquear() {
        this.bloqueada = true;
    }

    public int getPreguntaActual() {
        return preguntaActual;
    }

    public void setPreguntaActual(int preguntaActual) {
        this.preguntaActual = preguntaActual;
    }

    public void siguientePregunta() {
        this.preguntaActual++;
    }

    public void anadirPuntuacion(Usuario u, int puntuacion) {
        puntuaciones.put(u.getNombre(), puntuacion);
    }

    public String getGanador() {
        int maxPuntuacion = 0;
        String nombre = "";
        Iterator it = puntuaciones.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if((Integer)pair.getValue()>maxPuntuacion){
                maxPuntuacion = (Integer)pair.getValue();
                nombre = pair.getKey().toString();
            }
        }
        return nombre;
    }

}
