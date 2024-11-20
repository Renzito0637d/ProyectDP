package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Cliente;
import vista.VistaClienteCuenta;
import vista.VistaClienteCuentaActualizar;
import vista.VistaClienteCuentaDetalles;
import vista.VistaClienteCuentaEliminar;

/**
 *
 * @author Renzo
 */
public class ControladorClienteCuenta implements ActionListener{
    VistaClienteCuenta vista;
    VistaClienteCuentaDetalles deta;
    Cliente cliente;
    

    public ControladorClienteCuenta(VistaClienteCuenta vista, Cliente cliente) {
        this.vista=vista;
        this.cliente=cliente;
        vista.btnDetalles.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        
        deta=new VistaClienteCuentaDetalles();
        new ControladorClienteCuentaDetalles(deta, cliente);
        CambiarPanel(deta);
        vista.btnDetalles.setSelected(true);
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
            vista.btnDetalles, 
            vista.btnActualizar, 
            vista.btnEliminar
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnDetalles){
            VistaClienteCuentaDetalles deta=new VistaClienteCuentaDetalles();
            new ControladorClienteCuentaDetalles(deta, cliente);
            CambiarPanel(deta);
            setSelectedButton(vista.btnDetalles);
        }
        if(e.getSource()==vista.btnActualizar){
            VistaClienteCuentaActualizar actu=new VistaClienteCuentaActualizar();
            CambiarPanel(actu);
            setSelectedButton(vista.btnActualizar);
        }
        if(e.getSource()==vista.btnEliminar){
            VistaClienteCuentaEliminar eli=new VistaClienteCuentaEliminar();
            CambiarPanel(eli);
            setSelectedButton(vista.btnEliminar);
        }
    }
    
}
