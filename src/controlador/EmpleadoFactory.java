package controlador;

import modelo.Empleado;
import vista.VistaEmpleado;
public class EmpleadoFactory {
    
    public static VistaEmpleado crearVistaEmpleado(Empleado empleado) {
        VistaEmpleado vistaEmpleado = new VistaEmpleado();
        
        // Mostrar u ocultar pestañas según los permisos del empleado
        if (!empleado.verTabListaClientes()) {
            vistaEmpleado.jtppVistaEmpleado.remove(vistaEmpleado.scrlListaClientes);
        }
        if (!empleado.verTabListaEmpleados()) {
            vistaEmpleado.jtppVistaEmpleado.remove(vistaEmpleado.scrlListaEmpleados);
        }
        if (!empleado.verTabRegistroEmpleados()) {
            vistaEmpleado.jtppVistaEmpleado.remove(vistaEmpleado.scrlRegistroEmpleados);
        }
        
        return vistaEmpleado;  // Retorna la vista configurada
    }
    
}
