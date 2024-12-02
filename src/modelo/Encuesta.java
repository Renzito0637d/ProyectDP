/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Renzo
 */
public class Encuesta implements EstadoVariante{
    private int idEncuesta;
    private LocalDateTime fechaHoraLlenado;
    private int estado;
    private int respuesta1, respuesta2, respuesta3;
    
    // Constructor
    public Encuesta() {    }
    public Encuesta(int idEncuesta, LocalDateTime fechaHoraLlenado, int estado, int respuesta1, int respuesta2, int respuesta3) {
        this.idEncuesta = idEncuesta;
        this.fechaHoraLlenado = fechaHoraLlenado;
        this.estado = estado;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
    }  

    // Encapsulamiento
    public int getIdEncuesta() {        return idEncuesta;    }
    public void setIdEncuesta(int idEncuesta) {        this.idEncuesta = idEncuesta;    }
    public LocalDateTime getFechaHoraLlenado() {        return fechaHoraLlenado;    }
    public void setFechaHoraLlenado(LocalDateTime fechaHoraLlenado) {        this.fechaHoraLlenado = fechaHoraLlenado;    }
    public int getEstado() {        return estado;    }
    public void setEstado(int estado) {        this.estado = estado;    }
    public int getRespuesta1() {        return respuesta1;    }
    public void setRespuesta1(int respuesta1) {        this.respuesta1 = respuesta1;    }
    public int getRespuesta2() {        return respuesta2;    }
    public void setRespuesta2(int respuesta2) {        this.respuesta2 = respuesta2;    }
    public int getRespuesta3() {        return respuesta3;    }
    public void setRespuesta3(int respuesta3) {        this.respuesta3 = respuesta3;    }

    // Implementación de método abstracto
    @Override
    public String mostrarEstado() {
        if (estado == INACTIVA) return "Inactiva";
        if (estado == ACTIVA) return "Activa";
        if (estado == COMPLETADA) return "Completada";
        return null;
    }
    
    // Métodos propios
    public void setFechaHoraLlenadoActual() {
        this.fechaHoraLlenado = LocalDateTime.now();
    }
    
    public void activarEncuesta() {
        estado = ACTIVA;
    }
    
    public void completarEncuesta() {
        estado = COMPLETADA;
        setFechaHoraLlenadoActual();
    }

}
