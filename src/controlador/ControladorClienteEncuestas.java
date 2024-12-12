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
import modelo.Cliente;
import vista.VistaClienteEncuestas;
import vista.VistaClienteEncuestasCompletar;
import vista.VistaClienteEncuestasLista;

/**
 *
 * @author Renzo
 */
public class ControladorClienteEncuestas implements ActionListener{
    VistaClienteEncuestas vista;
    Cliente cliente;

    public ControladorClienteEncuestas(VistaClienteEncuestas vista,Cliente cliente) {
        this.vista=vista;
        this.cliente=cliente;
        vista.btnLista.addActionListener(this);
        vista.btnCompletar.addActionListener(this);
        vista.btnOtros.addActionListener(this);
        
        VistaClienteEncuestasLista lis=new VistaClienteEncuestasLista();
        CambiarPanel(lis);
        new ControladorClienteEncuestasLista(lis, cliente);
        setSelectedButton(vista.btnLista);
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
            new ControladorClienteEncuestasLista(lis, cliente);
            setSelectedButton(vista.btnLista);
        }
        if(e.getSource()==vista.btnCompletar){
            VistaClienteEncuestasCompletar com=new VistaClienteEncuestasCompletar();
            CambiarPanel(com);
            new ControladorClienteEncuestasCompletar(com, cliente);
            setSelectedButton(vista.btnCompletar);
        }
        if(e.getSource()==vista.btnOtros){
            setSelectedButton(vista.btnOtros);
        }
    }
    
    
}
