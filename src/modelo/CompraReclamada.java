package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class CompraReclamada {
    protected int numeroCompra;
    protected LocalDate fecha;
    protected String canalCompra, direccion;
    protected double montoReclamado;

    // Constructor
    public CompraReclamada() {    }
    public CompraReclamada(int numeroCompra, LocalDate fecha, String canalCompra, String direccion, double montoReclamado) {
        this.numeroCompra = numeroCompra;
        this.fecha = fecha;
        this.canalCompra = canalCompra;
        this.direccion = direccion;
        this.montoReclamado = montoReclamado;
    }    
    
    // Encapsulamiento
    public int getNumeroCompra() {        return numeroCompra;    }
    public void setNumeroCompra(int numeroCompra) {        this.numeroCompra = numeroCompra;    }
    public LocalDate getFecha() {        return fecha;    }
    public void setFecha(LocalDate fecha) {        this.fecha = fecha;    }
    public String getCanalCompra() {        return canalCompra;    }
    public void setCanalCompra(String canalCompra) {        this.canalCompra = canalCompra;    }
    public String getDireccion() {        return direccion;    }
    public void setDireccion(String direccion) {        this.direccion = direccion;    }
    public double getMontoReclamado() {        return montoReclamado;    }
    public void setMontoReclamado(double montoReclamado) {        this.montoReclamado = montoReclamado;    }
        
    // Métodos propios
    public String mostrarDetallesCompra() {
        // Formato de fecha-hora
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return "\nDetalles de la compra\nFecha del incidente: " + fecha.format(fmt) +
                "\nCanal de compra: " + canalCompra +
                "\nDireccion del establecimiento: " + direccion +
                "\nMonto reclamado: S/. %.2f".formatted(montoReclamado);
    }
}
