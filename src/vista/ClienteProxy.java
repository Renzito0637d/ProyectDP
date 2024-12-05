package vista;

import javax.swing.JLabel;

/**
 *
 * @author Renzo
 */
//patron PROXY para dar la bienvida al usuario
public class ClienteProxy implements InterfazBienvenida{
    
    private String bienvenida;
    private InterfazBienvenida cliente;
    
    public ClienteProxy(String bienvenida) {
        this.bienvenida = bienvenida;
    }

    @Override
    public void mostrarBienv(JLabel label) {
        if(cliente==null){
           cliente =new ClienteProxy(bienvenida);
        }
        cliente.mostrarBienv(label);
    }    
    
}
