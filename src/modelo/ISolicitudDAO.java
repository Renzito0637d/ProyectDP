package modelo;

import java.util.List;

public interface ISolicitudDAO {
    int agregar(Solicitud bean, int codigoCliente);
    int actualizar(Solicitud bean);
    void eliminar(int id);
    List<Solicitud> listarTodos();
    List<Solicitud> listarPorCliente(int codigoCliente);
    // Agrega otros métodos según sea necesario
}
