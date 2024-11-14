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
    private void resetButtons() {
        vista.btnLista.setSelected(false);
        vista.btnCompletar.setSelected(false);
        vista.btnOtros.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaClienteEncuestasLista lis=new VistaClienteEncuestasLista();
            CambiarPanel(lis);
            resetButtons();
        }
        if(e.getSource()==vista.btnCompletar){
            VistaClienteEncuestasCompletar com=new VistaClienteEncuestasCompletar();
            CambiarPanel(com);
            resetButtons();
        }
        if(e.getSource()==vista.btnOtros){
            
        }
    }
    
    
}
