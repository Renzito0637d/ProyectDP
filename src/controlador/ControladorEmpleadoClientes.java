package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Empleado;
import vista.VistaEmpleadoClientes;
import vista.VistaEmpleadoClientesAgregar;
import vista.VistaEmpleadoClientesEliminar;
import vista.VistaEmpleadoClientesLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoClientes implements ActionListener{
    VistaEmpleadoClientes vista;
    Empleado empleado;

    public ControladorEmpleadoClientes(VistaEmpleadoClientes vista,Empleado empleado) {
        this.vista=vista;
        this.empleado=empleado;
        vista.btnLista.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        
        VistaEmpleadoClientesLista lis=new VistaEmpleadoClientesLista();
        CambiarPanel(lis);
        new ControladorEmpleadoClientesLista(lis, empleado);
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
            VistaEmpleadoClientesLista lis=new VistaEmpleadoClientesLista();
            CambiarPanel(lis);
            new ControladorEmpleadoClientesLista(lis, empleado);
            setSelectedButton(vista.btnLista);
        }
        if(e.getSource()==vista.btnAgregar){
            VistaEmpleadoClientesAgregar agre=new VistaEmpleadoClientesAgregar();
            CambiarPanel(agre);
            new ControladorEmpleadoClientesAgregar(agre, empleado);
            setSelectedButton(vista.btnAgregar);
        }
        if(e.getSource()==vista.btnEliminar){
            VistaEmpleadoClientesEliminar eli=new VistaEmpleadoClientesEliminar();
            CambiarPanel(eli);
            new ControladorEmpleadoClientesEliminar(eli, empleado);
            setSelectedButton(vista.btnEliminar);
        }
    }
    
}
