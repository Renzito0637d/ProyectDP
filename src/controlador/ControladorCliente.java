package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.Cliente;
import vista.VistaCliente;
import vista.*;

/**
 *
 * @author Renzo
 */
public class ControladorCliente implements ActionListener{   
    VistaClienteSolicitudes soli=null;
    // La vista correspondiente a este controlador
    VistaClientes vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
    // El usuario que ha accedido a través del login
    Cliente cliente;
    public ControladorCliente(VistaClientes vista,ControladorLogin controladorPrevio, Cliente cliente) {
        this.vista=vista;
        this.controladorPrevio = controladorPrevio;
        this.cliente = cliente;
        vista.btnCuenta.addActionListener(this);
        vista.btnSolicitudes.addActionListener(this);
        vista.btnEncuestas.addActionListener(this);
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
    private void ponerTitulo(){
        vista.lbTitle.setText("¡Hola, "+cliente.getNombres()+" "+cliente.getApellidos()+"! Has accedido a tu panel como cliente");        
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
        soli=new VistaClienteSolicitudes();
        CambiarPanel(soli);
        new ControladorClienteSolicitudes(soli, cliente);
        vista.btnSolicitudes.setSelected(true);
        ponerTitulo();
    }
    
    private void setSelectedButton(javax.swing.JButton selectedButton) {
        javax.swing.JButton[] botones = {
            vista.btnCuenta, 
            vista.btnEncuestas, 
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
        if(e.getSource()==vista.btnCuenta){            
            VistaClienteCuenta cuenta=new VistaClienteCuenta();
            CambiarPanel(cuenta);
            new ControladorClienteCuenta(cuenta, cliente);
            setSelectedButton(vista.btnCuenta);
        }
        if(e.getSource()==vista.btnSolicitudes){            
            VistaClienteSolicitudes soli=new VistaClienteSolicitudes();            
            CambiarPanel(soli);
            new ControladorClienteSolicitudes(soli, cliente);
            setSelectedButton(vista.btnSolicitudes);
        }
        if(e.getSource()==vista.btnEncuestas){            
            VistaClienteEncuestas encu=new VistaClienteEncuestas();
            CambiarPanel(encu);
            new ControladorClienteEncuestas(encu);
            setSelectedButton(vista.btnEncuestas);
        }
        if(e.getSource()==vista.btnSalir){
            salir();
        }
    }
    
    
}
