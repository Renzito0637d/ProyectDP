/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JPanel;

/**
 *
 * @author Renzo
 */
//PATRON BRIGDE para el intercambio de paneles en la VISTA CLIENTE
public interface PanelInterface {
    JPanel crearPanel();  // Método para crear el panel
}