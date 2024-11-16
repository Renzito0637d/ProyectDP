/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaClienteEncuestas;
import vista.VistaClienteEncuestasCompletar;
import vista.VistaClienteEncuestasLista;

/**
 *
 * @author Renzo
 */
public class ControladorClienteEncuestas implements ActionListener{
    VistaClienteEncuestas vista;

    public ControladorClienteEncuestas(VistaClienteEncuestas vista) {
        this.vista=vista;
        vista.btnLista.addActionListener(this);
        vista.btnCompletar.addActionListener(this);
        vista.btnOtros.addActionListener(this);
    }
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    private void setSelectedButton(javax.swing.JButton selectedButton) {
        javax.swing.JButton[] botones = {
            vista.btnLista, 
            vista.btnCompletar, 
            vista.btnOtros
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaClienteEncuestasLista lis=new VistaClienteEncuestasLista();
            CambiarPanel(lis);
            setSelectedButton(vista.btnLista);
        }
        if(e.getSource()==vista.btnCompletar){
            VistaClienteEncuestasCompletar com=new VistaClienteEncuestasCompletar();
            CambiarPanel(com);
            setSelectedButton(vista.btnCompletar);
        }
        if(e.getSource()==vista.btnOtros){
            setSelectedButton(vista.btnOtros);
        }
    }
    
    
}
