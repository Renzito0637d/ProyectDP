/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControladorClienteEncuestas;
import javax.swing.JPanel;

/**
 *
 * @author Renzo
 */
//PATRON BRIGDE
public class PanelEncuestas implements PanelInterface {
    public PanelEncuestas() {
    }

    @Override
    public JPanel crearPanel() {
        VistaClienteEncuestas panel = new VistaClienteEncuestas();
        new ControladorClienteEncuestas(panel);
        return panel;
    }
}
