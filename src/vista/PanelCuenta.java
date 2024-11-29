/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorCliente;
import controlador.ControladorClienteCuenta;
import javax.swing.JPanel;
import modelo.Cliente;

/**
 *
 * @author Renzo
 */
//PATRON BRIGDE
public class PanelCuenta implements PanelInterface {
    private Cliente cliente;
    private ControladorCliente ctlCliente;
    
    public PanelCuenta(Cliente cliente,ControladorCliente ctlCliente) {
        this.cliente = cliente;
        this.ctlCliente=ctlCliente;
    }

    @Override
    public JPanel crearPanel() {
        VistaClienteCuenta panel = new VistaClienteCuenta();
        // Puedes hacer cualquier inicialización adicional aquí, por ejemplo:
        new ControladorClienteCuenta(panel, cliente,ctlCliente);
        return panel;
    }
}
