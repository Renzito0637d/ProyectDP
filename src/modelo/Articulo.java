package modelo;

public class Articulo {
    private int codigoProducto;
    private String categoria, nombre;
    private double precio;
    private int stock;
    
    // Constructor
    public Articulo() {    }
    public Articulo(int codigoProducto, String categoria, String nombre, double precio, int stock) {
        this.codigoProducto = codigoProducto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    // Encapsulamiento
    public int getCodigoProducto() {        return codigoProducto;    }
    public void setCodigoProducto(int codigoProducto) {        this.codigoProducto = codigoProducto;    }
    public String getCategoria() {        return categoria;    }
    public void setCategoria(String categoria) {        this.categoria = categoria;    }
    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    public double getPrecio() {        return precio;    }
    public void setPrecio(double precio) {        this.precio = precio;    }
    public int getStock() {        return stock;    }
    public void setStock(int stock) {        this.stock = stock;    }

    @Override
    public String toString() {
        if (codigoProducto < 0) {
            return nombre;
        } else {
            return nombre + "\t\tS/. %.2f".formatted(precio);
        }
    }
    
    
    // Métodos propios
    public String mostrarInformacion() {
        return "Detalles del artículo comprado" +
                "\nNombre del artículo: " + nombre +
                "\nCodigo de producto: " + codigoProducto +
                "\tCategoria: " +  categoria +
                "\nPrecio Normal: S/. %.2f".formatted(precio);
    }
    
}
