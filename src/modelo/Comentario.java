package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comentario {
    // Atributos
    private int idComentario;     // PK
    private LocalDateTime fechaHora;
    private String contenido;
    // Relaciones
    private Cliente cliente;    // Asociacion
    
    // Constructor    
    public Comentario() {    }
    public Comentario(int idComentario, LocalDateTime fechaHora, String contenido, Cliente cliente) {
        this.idComentario = idComentario;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.cliente = cliente;
    }    
    
    // Encapsulamiento
    public int getIdComentario() {        return idComentario;    }
    public void setIdComentario(int idComentario) {        this.idComentario = idComentario;    }
    public LocalDateTime getFechaHora() {        return fechaHora;    }
    public void setFechaHora(LocalDateTime fechaHora) {        this.fechaHora = fechaHora;    }
    public String getContenido() {        return contenido;    }
    public void setContenido(String contenido) {        this.contenido = contenido;    }
    public Cliente getCliente() {        return cliente;    }
    public void setCliente(Cliente cliente) {        this.cliente = cliente;    }    
    
    // Métodos propios
    public void setFechaHoraActual() {
        this.fechaHora = LocalDateTime.now();
    }
    
    public String mostrarCliente() {
        // Devolver nombre y apellido
        return cliente.getNombres() + " " + cliente.getApellidos();
        
    }
    
    public String mostrarComentario() {        
        // Formato de fecha-hora
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        // Informacion
        return "Autor: " + mostrarCliente() +
               "\nFecha: " + fechaHora.format(fmt) +
               '\n' + contenido;
    } 
}
