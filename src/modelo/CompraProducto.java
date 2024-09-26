
package modelo;

import java.time.LocalDate;

public class CompraProducto extends CompraReclamada {
    // Relaciones
    private Articulo articulo; // Asociacion
    
    // Constructor
    public CompraProducto() {    }
    public CompraProducto(Articulo articulo, int numeroCompra, LocalDate fecha, String canalCompra, String direccion, double montoReclamado) {
        super(numeroCompra, fecha, canalCompra, direccion, montoReclamado);
        this.articulo = articulo;
    }    
    
    // Encapsulamiento
    public Articulo getArticulo() {        return articulo;    }
    public void setArticulo(Articulo articulo) {        this.articulo = articulo;    }
    
    // Override de método heredado
    @Override
    public String mostrarDetallesCompra() {
        return super.mostrarDetallesCompra() +
                '\n' + articulo.mostrarInformacion();
    }  
}
