
package modelo;

import java.time.LocalDate;

public class CompraServicio extends CompraReclamada {
    private String nombreServicio;
    
    // Constructor
    public CompraServicio() {    }
    public CompraServicio(String nombreServicio, int numeroCompra, LocalDate fecha, String canalCompra, String direccion, double montoReclamado) {
        super(numeroCompra, fecha, canalCompra, direccion, montoReclamado);
        this.nombreServicio = nombreServicio;
    }

    // Encapsulamiento
    public String getNombreServicio() {        return nombreServicio;    }
    public void setNombreServicio(String nombreServicio) {        this.nombreServicio = nombreServicio;    }
    
    // Override de método heredado
    @Override
    public String mostrarDetallesCompra() {
        return super.mostrarDetallesCompra() +
                "\nServicio contratado: " + nombreServicio;
    } 
    
}
