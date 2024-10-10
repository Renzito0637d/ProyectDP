package controlador;

import modelo.ClienteDAO;
import modelo.DepartamentoDAO;
import modelo.EmpleadoDAO;
import modelo.EvaluacionDAO;
import modelo.SolicitudDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.Cliente;
import modelo.Departamento;

import modelo.Evaluacion;
import modelo.Solicitud;
import vista.VistaEmpleado;

public class ControladorEmpleado implements ActionListener, MouseListener {
    // La vista correspondiente a este controlador
    VistaEmpleado vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
    // El usuario del empleado que ha accedido a través del login
    Empleado empleado;
    
    
    public ControladorEmpleado(VistaEmpleado vista, ControladorLogin controladorPrevio, Empleado empleado) {
        this.vista =  EmpleadoFactory.crearVistaEmpleado(empleado); //Aplicando el FACTORY del empleado
        this.controladorPrevio = controladorPrevio;
        this.empleado = empleado;
        // Eventos - Tab Solicitudes
        this.vista.btnActualizarListaSol.addActionListener(this);
        this.vista.tbSolicitudes.addMouseListener(this);
        this.vista.tbEvaluaciones.addMouseListener(this);
        this.vista.rbtnTramite.addActionListener(this);
        this.vista.rbtnFinalizado.addActionListener(this);
        this.vista.btnEvaluarSol.addActionListener(this);
        this.vista.btnLimpiarSol.addActionListener(this);
        this.vista.btnSalirSol.addActionListener(this);
        // Eventos - Tab Lista Clientes
        this.vista.btnBuscarCliente.addActionListener(this);
        this.vista.tbClientes.addMouseListener(this);
        this.vista.btnSalirCliente.addActionListener(this);
        // Eventos - Tab Lista Empleados
        this.vista.btnBuscarEmpleado.addActionListener(this);
        this.vista.tbEmpleados.addMouseListener(this);
        this.vista.btnSalirEmpleado.addActionListener(this);
        // Eventos - Tab Registrar Empleado
        this.vista.btnAutoGenerarUsuario.addActionListener(this);
        this.vista.btnRegistrarRE.addActionListener(this);
        this.vista.btnLimpiarRE.addActionListener(this);
        this.vista.btnSalirRE.addActionListener(this);
        
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
        // EVENTOS - TAB SOLICITUDES
        if (e.getSource() == vista.btnActualizarListaSol) {
        
        }
        if (e.getSource() == vista.rbtnFinalizado) {
        }
        if (e.getSource() == vista.rbtnTramite) {
        }        
        if (e.getSource() == vista.btnEvaluarSol) {
        }
        if (e.getSource() == vista.btnLimpiarSol) {
        }
        if (e.getSource() == vista.btnSalirSol) {
            salir();
        }
        
        
        // EVENTOS - TAB LISTA CLIENTES
        if (e.getSource() == vista.btnBuscarCliente) {
        }
        if (e.getSource() == vista.btnSalirCliente) {
            salir();
        }
        
        // EVENTOS - TAB LISTA EMPLEADOS
        if (e.getSource() == vista.btnBuscarEmpleado) {
        }
        if (e.getSource() == vista.btnSalirEmpleado) {
            salir();
        }
        
        // EVENTOS - TAB REGISTRO EMPLEADOS
        if (e.getSource() == vista.btnRegistrarRE) {
        }
        if (e.getSource() == vista.btnAutoGenerarUsuario) {            
        } 
        if (e.getSource() == vista.btnLimpiarRE) {
        
        }        
        if (e.getSource() == vista.btnSalirRE) {
            salir();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }
    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {    }
    
    // MÉTODOS PROPIOS por agregar

    
    // PASAR A OTRA PANTALLA
    public void salir() {
        // Regresar a la ventana Login
        controladorPrevio.iniciar();
        // Cerrar la ventana actual
        vista.dispose();
    }

}
