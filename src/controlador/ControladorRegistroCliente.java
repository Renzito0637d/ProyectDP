package controlador;

import modelo.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;
import modelo.Cliente;
import vista.VistaRegistroCliente;

public class ControladorRegistroCliente implements ActionListener {
    // La vista correspondiente a este controlador
    VistaRegistroCliente vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;

    // DAO
    ClienteDAO clienteDAO;
    
    public ControladorRegistroCliente(VistaRegistroCliente vista, ControladorLogin controladorPrevio) {
        this.vista = vista;
        this.controladorPrevio = controladorPrevio;
        this.vista.btnAutoGenerarUsuario.addActionListener(this);
        this.vista.btnRegistrarRC.addActionListener(this);
        this.vista.btnLimpiarRC.addActionListener(this);
        this.vista.btnVolverRC.addActionListener(this);
    }
    
    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);
        vista.setSize(500, 600);
        vista.setVisible(true);
    }

    // Eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrarRC) {
        
        }
        if (e.getSource() == vista.btnAutoGenerarUsuario) {            
        
        } 
        if (e.getSource() == vista.btnLimpiarRC) {
        
        }        
        if (e.getSource() == vista.btnVolverRC) {
            salir();
        }
    }
    
    // MÉTODOS PROPIOS por agergar   
    
    // PASAR A OTRA PANTALLA
    public void salir() {
        // Regresar a la ventana Login
        controladorPrevio.iniciar();
        // Cerrar la ventana actual
        vista.dispose();
    }
}
