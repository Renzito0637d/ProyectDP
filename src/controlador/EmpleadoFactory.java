package controlador;

import modelo.Empleado;
import vista.VistaEmpleado;
public class EmpleadoFactory {
    
    public static VistaEmpleado crearVistaEmpleado(Empleado empleado) {
        VistaEmpleado vistaEmpleado = new VistaEmpleado();
        
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
