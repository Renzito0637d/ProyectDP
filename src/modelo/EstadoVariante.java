package modelo;

public interface EstadoVariante {
    // Constantes para estado de las solicitudes y evaluaciones
    static final int EN_ESPERA = 1;
    static final int EN_TRAMITE = 2;
    static final int FINALIZADO = 3;
    
    // Método para mostrar el estado de la entidad implementada
    String mostrarEstado();
}