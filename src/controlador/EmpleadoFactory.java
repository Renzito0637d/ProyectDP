package controlador;

import modelo.Empleado;
import vista.VistaEmpleados;
public class EmpleadoFactory {
    
    public static VistaEmpleados crearVistaEmpleado(Empleado empleado) {
        VistaEmpleados vistaEmpleado = new VistaEmpleados();
        
        // Mostrar u ocultar pestañas según los permisos del empleado
        if (!empleado.verTabListaClientes()) {
            vistaEmpleado.btnClientes.setEnabled(false);
        }
        if (!empleado.verTabListaEmpleados()) {
            vistaEmpleado.btnEmpleados.setEnabled(false);
        }
        
        return vistaEmpleado;  // Retorna la vista configurada
    }
            
}
