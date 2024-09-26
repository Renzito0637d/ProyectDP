package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Evaluacion implements EstadoVariante{
    // Atributos
    private int numeroEvaluacion;    // PK
    private LocalDateTime fechaHora;
    private int estado;
    private String descripcion;
    // Relaciones
    private Empleado empleado;                      // Asociacion
    private ArrayList<Comentario> listaComentarios; // Agregacion
    
    // Constructor    
    public Evaluacion() {    }
    public Evaluacion(int numeroEvaluacion, LocalDateTime fechaHora, int estado, String descripcion, Empleado empleado) {
        this.numeroEvaluacion = numeroEvaluacion;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.descripcion = descripcion;
        this.empleado = empleado;
    }    
    
    // Encapsulamiento
    public int getNumeroEvaluacion() {        return numeroEvaluacion;    }
    public void setNumeroEvaluacion(int numeroEvaluacion) {        this.numeroEvaluacion = numeroEvaluacion;    }
    public LocalDateTime getFechaHora() {        return fechaHora;    }
    public void setFechaHora(LocalDateTime fechaHora) {        this.fechaHora = fechaHora;    }
    public int getEstado() {        return estado;    }
    public void setEstado(int estado) {        this.estado = estado;    }
    public String getDescripcion() {        return descripcion;    }
    public void setDescripcion(String descripcion) {        this.descripcion = descripcion;    }
    public Empleado getEmpleado() {        return empleado;    }
    public void setEmpleado(Empleado empleado) {        this.empleado = empleado;    }
    
    // Implementación de método abstracto
    @Override
    public String mostrarEstado() {
        if (estado == EN_ESPERA) return "En Espera";
        if (estado == EN_TRAMITE) return "En Trámite";
        if (estado == FINALIZADO) return "Finalizado";
        return null;
    }
    
    
    // Metodos propios
        
    public void setFechaHoraActual() {
        this.fechaHora = LocalDateTime.now();
    }   
    
    public String mostrarEvaluador() {
        // Si el empleado es null (ej. porque es la primera evaluacion creada por cliente)
        if (empleado == null) return "Creado por cliente";
        // Obtener nombre, apellido y departamento
        return empleado.getNombres() + " " + empleado.getApellidos() + " - " + empleado.obtenerDepartamento().getNombre();
    }
    
    public String[] mostrarRegistroTabla() {        
        // Formato de fecha-hora
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");
        
        String[] registro = {
            String.valueOf(numeroEvaluacion),
            fechaHora.format(fmtFecha), 
            fechaHora.format(fmtHora), 
            mostrarEstado(), 
            mostrarEvaluador()
        };
        return registro;
    }
    
    public String mostrarInformacionCaja() {
        // Formato de fecha-hora
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        // Informacion de la evaluacion
        return "EVALUACIÓN " + numeroEvaluacion +
                "\nFecha: " + fechaHora.format(fmt) +
                "\nEstado: " + mostrarEstado() + 
                "\nEvaluador: " + mostrarEvaluador() +
                "\nDescripcion\n" + descripcion;        
    }
    
}
