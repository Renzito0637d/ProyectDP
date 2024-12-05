package vista;

import javax.swing.JLabel;

/**
 *
 * @author Renzo
 */
public class EmpleadoProxy implements InterfazBienvenida{

    private String bienvenida;
    private InterfazBienvenida empleado;
    
    public EmpleadoProxy(String bienvenida) {
        this.bienvenida = bienvenida;
    }
    
    @Override
    public void mostrarBienv(JLabel label) {
        if(empleado==null){
           empleado =new EmpleadoProxy(bienvenida);
        }
        empleado.mostrarBienv(label);
    }
    
}
