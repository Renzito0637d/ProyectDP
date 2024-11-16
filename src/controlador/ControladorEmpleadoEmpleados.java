package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaEmpleadoEmpleados;
import vista.VistaEmpleadoEmpleadosAgregar;
import vista.VistaEmpleadoEmpleadosEliminar;
import vista.VistaEmpleadoEmpleadosLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoEmpleados implements ActionListener{

    VistaEmpleadoEmpleados vista;

    public ControladorEmpleadoEmpleados(VistaEmpleadoEmpleados vista) {
        this.vista=vista;
        vista.btnLista.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
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
            vista.btnAgregar, 
            vista.btnEliminar
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaEmpleadoEmpleadosLista lis=new VistaEmpleadoEmpleadosLista();
            CambiarPanel(lis);
            setSelectedButton(vista.btnLista);
        }
        if(e.getSource()==vista.btnAgregar){
            VistaEmpleadoEmpleadosAgregar agre=new VistaEmpleadoEmpleadosAgregar();
            CambiarPanel(agre);
            setSelectedButton(vista.btnAgregar);
        }
        if(e.getSource()==vista.btnEliminar){
            VistaEmpleadoEmpleadosEliminar eli=new VistaEmpleadoEmpleadosEliminar();
            CambiarPanel(eli);
            setSelectedButton(vista.btnEliminar);
        }
    }
    
}
