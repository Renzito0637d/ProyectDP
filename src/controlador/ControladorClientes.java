package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Cliente;
import vista.VistaCliente;
import vista.*;

/**
 *
 * @author Renzo
 */
public class ControladorClientes implements ActionListener{    
    // La vista correspondiente a este controlador
    VistaClientes vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
    // El usuario que ha accedido a través del login
    Cliente cliente;
    public ControladorClientes(VistaClientes vista,ControladorLogin controladorPrevio, Cliente cliente) {
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
    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);          
        vista.setVisible(true);
    }
    private void resetButtons() {
        vista.btnCuenta.setSelected(false);
        vista.btnEncuestas.setSelected(false);
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
        if(e.getSource()==vista.btnCuenta){
            VistaClienteCuenta cuenta=new VistaClienteCuenta();
            CambiarPanel(cuenta);
            new ControladorClienteCuenta(cuenta);
            resetButtons();
        }
        if(e.getSource()==vista.btnSolicitudes){
            VistaClienteSolicitudes soli=new VistaClienteSolicitudes();
            new ControladorClienteSolicitudes(soli);
            CambiarPanel(soli);
            
        }
        if(e.getSource()==vista.btnSalir){
            salir();
        }
    }
    
    
}
