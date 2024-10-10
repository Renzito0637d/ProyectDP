package controlador;

import modelo.ArticuloDAO;
import modelo.EvaluacionDAO;
import modelo.SolicitudDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Articulo;
import modelo.ClienteDAO;
import modelo.CompraProducto;
import modelo.CompraReclamada;
import modelo.CompraServicio;
import modelo.Evaluacion;
import modelo.Solicitud;
import vista.VistaCliente;
import vista.VistaLogin;

public class ControladorCliente implements ActionListener, MouseListener {
    // La vista correspondiente a este controlador
    VistaCliente vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
    // El usuario que ha accedido a través del login
    Cliente cliente;
    
    // DAO
    ArticuloDAO articuloDAO;
    SolicitudDAO solicitudDAO;
    EvaluacionDAO evaluacionDAO;
    
    // Table models
    DefaultTableModel modeloSol;
    DefaultTableModel modeloEva;
    DefaultTableModel modeloEnc;
    
    DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public ControladorCliente(){};
    
    public ControladorCliente(VistaCliente vista, ControladorLogin controladorPrevio, Cliente cliente) {
        this.vista = vista;
        this.controladorPrevio = controladorPrevio;
        this.cliente = cliente;
        // Eventos - Tab Crear Solicitud
        this.vista.rbtnServicio.addActionListener(this);
        this.vista.rbtnProducto.addActionListener(this);
        this.vista.btnCrearSolicitud.addActionListener(this);
        this.vista.btnLimpiarSolicitud.addActionListener(this);
        this.vista.btnSalirCrearSolicitud.addActionListener(this);
        // Eventos - Tab Ver Mis Solicitudes
        this.vista.btnActualizarListaSol.addActionListener(this);
        this.vista.tbSolicitudes.addMouseListener(this);
        this.vista.tbEvaluaciones.addMouseListener(this);
    }
    
    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);
        vista.setSize(800, 600);
        vista.setVisible(true);
    }
    
    // Eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        // EVENTOS - TAB CREAR SOLICITUD
        if (e.getSource() == vista.rbtnServicio) {
            
        }
        if (e.getSource() == vista.rbtnProducto) {
            
        }
        if (e.getSource() == vista.btnLimpiarSolicitud) {
        
        }  
        if (e.getSource() == vista.btnCrearSolicitud) {
        
        }
        if (e.getSource() == vista.btnSalirCrearSolicitud) {
            salir();
        }        
        
        // EVENTOS - TAB VER MIS SOLICITUDES
        if (e.getSource() == vista.btnActualizarListaSol) {
        }
        

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // EVENTOS - TAB VER MIS SOLICITUDES dando clic a una fila
        if (e.getSource() == vista.tbSolicitudes) {
        }        
        if (e.getSource() == vista.tbEvaluaciones) {
        }

    }
    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {    }
    
    // MÉTODOS PROPIOS aun faltan
        
    // PASAR A OTRA PANTALLA
    public void salir() {
        // Regresar a la ventana Login
        controladorPrevio.iniciar();
        // Cerrar la ventana actual
        vista.dispose();
    }
    

}
