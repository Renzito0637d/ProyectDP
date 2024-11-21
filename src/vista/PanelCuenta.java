/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorClienteCuenta;
import javax.swing.JPanel;
import modelo.Cliente;

/**
 *
 * @author Renzo
 */
public class PanelCuenta implements PanelInterface {
    private Cliente cliente;

    public PanelCuenta(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public JPanel crearPanel() {
        VistaClienteCuenta panel = new VistaClienteCuenta();
        // Puedes hacer cualquier inicialización adicional aquí, por ejemplo:
        new ControladorClienteCuenta(panel, cliente);
        return panel;
    }
}
