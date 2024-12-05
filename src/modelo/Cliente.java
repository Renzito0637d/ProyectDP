package modelo;

import java.util.ArrayList;
import javax.swing.JLabel;
import vista.InterfazBienvenida;
public class Cliente extends Persona implements InterfazBienvenida{
    // Atributos
    private int codigoCliente;
    private String direccion;
    // Relaciones
    private ArrayList<Solicitud> listaSolicitudes; // Agregacion
    
    // Constructor    
    public Cliente() {    }
    public Cliente(int codigoCliente, String direccion, String nombres, String apellidos, String email, String telefono, String usuario, String clave) {
        super(nombres, apellidos, email, telefono, usuario, clave);
        this.codigoCliente = codigoCliente;
        this.direccion = direccion;
    }    
    
    // Encapsulamiento
    public int getCodigoCliente() {        return codigoCliente;    }
    public void setCodigoCliente(int codigoCliente) {        this.codigoCliente = codigoCliente;    }
    public String getDireccion() {        return direccion;    }
    public void setDireccion(String direccion) {        this.direccion = direccion;    }
        
    // Métodos propios
    public void obtenerListaSolicitudes() {
        SolicitudDAO dao = new SolicitudDAO();
        listaSolicitudes = new ArrayList<> (dao.listarPorCliente(codigoCliente));
    }
    
    public String[] mostrarRegistroTabla() {
        String[] registro = {
            String.format("%04d", codigoCliente),
            nombres,
            apellidos,
            email,
            telefono,
            usuario,
            direccion
        };
        return registro;
    }

    @Override
    public void mostrarBienv(JLabel label) {
        label.setText("¡Hola, "+nombres+" "+apellidos+"! Has accedido a tu panel como cliente");        
    }
    
}
