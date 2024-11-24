package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Empleado;
import vista.VistaEmpleadoSolicitudes;
import vista.VistaEmpleadoSolicitudesEvaluar;
import vista.VistaEmpleadoSolicitudesLista;
import vista.VistaEmpleadoSolicitudesSeguimiento;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoSolicitudes implements ActionListener{
    VistaEmpleadoSolicitudes vista;
    Empleado empleado;
    
    public ControladorEmpleadoSolicitudes(VistaEmpleadoSolicitudes vista,Empleado empleado) {
        this.vista=vista;
        this.empleado=empleado;
        vista.btnLista.addActionListener(this);
        vista.btnSeguimiento.addActionListener(this);
        vista.btnEvaluar.addActionListener(this);  
        VistaEmpleadoSolicitudesLista lista=new VistaEmpleadoSolicitudesLista();
        CambiarPanel(lista);
        new ControladorEmpleadoSolicitudesLista(lista, empleado);
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
            vista.btnSeguimiento, 
            vista.btnEvaluar
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaEmpleadoSolicitudesLista lista=new VistaEmpleadoSolicitudesLista();
            CambiarPanel(lista);
            new ControladorEmpleadoSolicitudesLista(lista, empleado);
            setSelectedButton(vista.btnLista);
        }
        if(e.getSource()==vista.btnSeguimiento){
            VistaEmpleadoSolicitudesSeguimiento soli=new VistaEmpleadoSolicitudesSeguimiento();
            CambiarPanel(soli);
            new ControladorEmpleadoSolicitudesSeguimiento(soli, empleado);
            setSelectedButton(vista.btnSeguimiento);
        }
        if(e.getSource()==vista.btnEvaluar){
            VistaEmpleadoSolicitudesEvaluar eva=new VistaEmpleadoSolicitudesEvaluar();
            CambiarPanel(eva);
            new ControladorEmpleadoSolicitudesEvaluar(eva, empleado);
            setSelectedButton(vista.btnEvaluar);
        }
        
    }
    
}
