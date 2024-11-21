/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControladorClienteSolicitudes;
import javax.swing.JPanel;
import modelo.Cliente;

/**
 *
 * @author Renzo
 */
public class PanelSolicitudes implements PanelInterface {
    private Cliente cliente;

    public PanelSolicitudes(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public JPanel crearPanel() {
        VistaClienteSolicitudes panel = new VistaClienteSolicitudes();
        // Configuración adicional si es necesario
        new ControladorClienteSolicitudes(panel, cliente);
        return panel;
    }
}
