package modelo;

public interface EstadoVariante {
    // Constantes para estado de las solicitudes y evaluaciones
    static final int EN_ESPERA = 1;
    static final int EN_TRAMITE = 2;
    static final int FINALIZADO = 3;
    // Constantes para estado de las encuestas de satisfaccion
    static final int INACTIVA = 1;
    static final int ACTIVA = 2;
    static final int COMPLETADA = 3;
    // Método para mostrar el estado de la entidad implementada
    String mostrarEstado();
}