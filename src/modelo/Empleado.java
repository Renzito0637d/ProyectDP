package modelo;

public class Empleado extends Persona
                      implements Administrador {
    // Atributos
    private int codigoEmpleado;
    
    // Constructor    
    public Empleado() {    }
    public Empleado(int codigoEmpleado, String nombres, String apellidos, String email, String telefono, String usuario, String clave) {
        super(nombres, apellidos, email, telefono, usuario, clave);
        this.codigoEmpleado = codigoEmpleado;
    }    
    
    // Encapsulamiento
    public int getCodigoEmpleado() {        return codigoEmpleado;    }
    public void setCodigoEmpleado(int codigoEmpleado) {        this.codigoEmpleado = codigoEmpleado;    }
    
    // Métodos propios
    public Departamento obtenerDepartamento() {
        // Consultar en la BD el Departamento asociado al Empleado
        DepartamentoDAO depaDAO = new DepartamentoDAO();
        Departamento departamento = depaDAO.buscarPorEmpleado(codigoEmpleado);         
        return departamento;
    }
    
    public String[] mostrarRegistroTabla() {
        String[] registro = {
            String.format("%04d", codigoEmpleado),
            nombres,
            apellidos,
            email,
            telefono,
            usuario,
            obtenerDepartamento().getNombre()
        };
        return registro;
    }
    
    // Implementación de métodos abstractos   patron ISP 

    @Override
    public boolean verTabListaClientes() {
        if (obtenerDepartamento().getCodigoDepartamento() == 4) { // Si es de Gerencia podra ver
            return true;
        }
        return false;
    }

    @Override
    public boolean verTabListaEmpleados() {
        if (obtenerDepartamento().getCodigoDepartamento() == 4) { // Si es de Gerencia podra ver
            return true;
        }
        return false;
    }

    @Override
    public boolean verTabRegistroEmpleados() {
        if (obtenerDepartamento().getCodigoDepartamento() == 4) { // Gerencia
            return true;
        }
        return false;
    }
    
}
