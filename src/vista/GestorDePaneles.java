/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorCliente;
import javax.swing.JPanel;

/**
 *
 * @author Renzo
 */
public class GestorDePaneles {
    private ControladorCliente controlador;
    private PanelInterface panelActual;  // Referencia a la implementación concreta del panel

    public GestorDePaneles(ControladorCliente controlador, PanelInterface panelInicial) {
        this.controlador = controlador;
        this.panelActual = panelInicial;
    }

    // Método para cambiar el panel
    public void cambiarPanel() {
        JPanel nuevoPanel = panelActual.crearPanel(); // Crea el panel correspondiente
        controlador.CambiarPanel(nuevoPanel); // Llama al controlador para cambiar el panel en la vista
    }

    // Método para cambiar el panel, usando una nueva implementación de PanelInterface
    public void setPanel(PanelInterface nuevoPanel) {
        this.panelActual = nuevoPanel;
    }
}

