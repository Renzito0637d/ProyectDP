package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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

    public ControladorEmpleado(ControladorLogin controladorPrevio, Empleado empleado) {
        // Crear la vista configurada con la fábrica
        this.vista = EmpleadoFactory.crearVistaEmpleado(empleado);
        this.controladorPrevio = controladorPrevio;
        this.empleado = empleado;

        // Asignar los action listeners a los botones de la vista
        vista.btnSolicitudes.addActionListener(this);
        vista.btnClientes.addActionListener(this);
        vista.btnEmpleados.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        
        VistaEmpleadoSolicitudes soli=new VistaEmpleadoSolicitudes();
        CambiarPanel(soli);    
        new ControladorEmpleadoSolicitudes(soli,empleado);
        setSelectedButton(vista.btnSolicitudes);
    }
    
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    
    private void ponerTitulo(){
        vista.lbTitle.setText("Bienvenido, "+empleado.getNombres()+" "+empleado.getApellidos()+". Estás dentro del sistema de solicitudes");        
    }
    
    public void iniciar() {
        vista.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        vista.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153,0,153));
         vista.setSize(800, 600);  // Establece un tamaño inicial
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);          
        vista.setVisible(true);
        ponerTitulo();
    }
    private void setSelectedButton(javax.swing.JButton selectedButton) {
        javax.swing.JButton[] botones = {
            vista.btnClientes, 
            vista.btnEmpleados, 
            vista.btnSalir, 
            vista.btnSolicitudes
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
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
            new ControladorEmpleadoSolicitudes(soli,empleado);
            setSelectedButton(vista.btnSolicitudes);
        }
        if(e.getSource()==vista.btnClientes){
            VistaEmpleadoClientes cli=new VistaEmpleadoClientes();
            CambiarPanel(cli);
            new ControladorEmpleadoClientes(cli);
            setSelectedButton(vista.btnClientes);
        }
        if(e.getSource()==vista.btnEmpleados){
            VistaEmpleadoEmpleados emp=new VistaEmpleadoEmpleados();
            CambiarPanel(emp);
            new ControladorEmpleadoEmpleados(emp,empleado);
            setSelectedButton(vista.btnEmpleados);
        }
        if(e.getSource()==vista.btnSalir){
            salir();
            setSelectedButton(vista.btnSalir);
        }
    }
}
