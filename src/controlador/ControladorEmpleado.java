package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Empleado;
import vista.VistaClienteSolicitudes;
import vista.VistaEmpleadoClientes;
import vista.VistaEmpleadoEmpleados;
import vista.VistaEmpleadoSolicitudes;
import vista.VistaEmpleados;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleado implements ActionListener{
    // La vista correspondiente a este controlador
    VistaEmpleados vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
    // El usuario del empleado que ha accedido a través del login
    Empleado empleado;

    public ControladorEmpleado(VistaEmpleados vista,ControladorLogin controladorPrevio, Empleado empleado) {
        this.vista =  vista;
        this.controladorPrevio = controladorPrevio;
        this.empleado = empleado;
        vista.btnSolicitudes.addActionListener(this);
        vista.btnClientes.addActionListener(this);
        vista.btnEmpleados.addActionListener(this);
        vista.btnSalir.addActionListener(this);
    }
    
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);          
        vista.setVisible(true);
        
    }
    private void resetButtons() {
        vista.btnClientes.setSelected(false);
        vista.btnEmpleados.setSelected(false);
        vista.btnSalir.setSelected(false);
        vista.btnSolicitudes.setSelected(false);
    }
    // PASAR A OTRA PANTALLA
    public void salir() {
        // Regresar a la ventana Login
        controladorPrevio.iniciar();
        // Cerrar la ventana actual
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {      
        if(e.getSource()==vista.btnSolicitudes){
            VistaEmpleadoSolicitudes soli=new VistaEmpleadoSolicitudes();
            CambiarPanel(soli);    
            new ControladorEmpleadoSolicitudes(soli);
            resetButtons();
        }
        if(e.getSource()==vista.btnClientes){
            VistaEmpleadoClientes cli=new VistaEmpleadoClientes();
            CambiarPanel(cli);
            new ControladorEmpleadoClientes(cli);
            resetButtons();
        }
        if(e.getSource()==vista.btnEmpleados){
            VistaEmpleadoEmpleados emp=new VistaEmpleadoEmpleados();
            CambiarPanel(emp);
            new ControladorEmpleadoEmpleados(emp);
            resetButtons();
        }
    }
}
