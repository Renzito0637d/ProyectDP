package modelo;

import java.util.ArrayList;

public class Departamento {
    // Atributos
    private int codigoDepartamento;
    private String nombre, email, telefono;
    // Relaciones
    private ArrayList<Empleado> listaEmpleados; // Agregacion
    
    // Constructor    
    public Departamento() {    }
    public Departamento(int codigoDepartamento, String nombre, String email, String telefono) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Encapsulamiento
    public int getCodigoDepartamento() {        return codigoDepartamento;    }
    public void setCodigoDepartamento(int codigoDepartamento) {        this.codigoDepartamento = codigoDepartamento;    }
    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }
    public String getTelefono() {        return telefono;    }
    public void setTelefono(String telefono) {        this.telefono = telefono;    }
    public ArrayList<Empleado> getListaEmpleados() {        return listaEmpleados;    }
    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {        this.listaEmpleados = listaEmpleados;    }
    
    // Métodos propios
    public void obtenerListaEmpleados() {
        EmpleadoDAO dao = new EmpleadoDAO();
        listaEmpleados = new ArrayList<> (dao.listarPorDepartamento(codigoDepartamento));
    }
    
    public String mostrarInformacion() {
        return "Departamento de " + nombre + '\n' +
                "E-mail: " + email + '\n' + 
                "Teléfono: " + telefono + '\n';
    }

}
