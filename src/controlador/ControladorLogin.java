package controlador;

import modelo.ClienteDAO;
import modelo.EmpleadoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Persona;
import vista.VistaCliente;
import vista.VistaEmpleado;
import vista.VistaLogin;
import vista.VistaRegistroCliente;

public class ControladorLogin implements ActionListener {
    // La vista correspondiente a este controlador
    VistaLogin vista;    
    // La persona que intenta ingresar al sistema
    Persona persona;
    
    // DAO
    ClienteDAO clienteDAO = new ClienteDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();    

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        this.vista.btnIngresarLogin.addActionListener(this);
        this.vista.btnLimpiarLogin.addActionListener(this);
        this.vista.btnRegistrarLogin.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);
        limpiarTodo();
        vista.setVisible(true);
    }
    
    //EVENTOS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnLimpiarLogin) {
            limpiarTodo();
        }
        if (e.getSource() == vista.btnIngresarLogin) {
            //APLICANDO EL FACADE
            autenticarElUsuario();
        }
        if (e.getSource() == vista.btnRegistrarLogin) {
            // Abrir la ventana RegistrarCliente
            VistaRegistroCliente vistaRC = new VistaRegistroCliente();
            ControladorRegistroCliente ctrl = new ControladorRegistroCliente(vistaRC, this);
            ctrl.iniciar();
            // Cerrar la ventana actual
            vista.dispose();
        }
    }
    
    //METODO FACADE - PARA LA AUTENTIFICACION DE UN USUARIO sea CLIENTE o EMPLEADO
    public void autenticarElUsuario(){
       // Obtener las credenciales
            String usuario = vista.txtUsuarioLogin.getText();
            String clave = vista.txtClaveLogin.getText();
            // Selecciona un radiobutton
            if (vista.rbtnCliente.isSelected()) {
                persona = clienteDAO.buscarPorCredenciales(usuario, clave);
                // Evaluar que la busqueda haya sido exitosa
                if (persona instanceof Cliente cliente && cliente.getCodigoCliente() != -1) {
                    // Abrir la ventana Cliente, pasando el objeto cliente como argumento
                    VistaCliente vistaC = new VistaCliente();
                    ControladorCliente ctrl = new ControladorCliente(vistaC, this, cliente);
                    ctrl.iniciar();
                    
                    //PRUEBAS en el terminal
                    
                    //System.out.println("INGRESO COMO CLIENTE");
                    
                    //System.out.println("ID: " + cliente.getCodigoCliente() +
                    //               " \nNombres: " + cliente.getNombres() +
                    //               " \nApellidos: " + cliente.getApellidos() +
                    //               " \nEmail: " + cliente.getEmail() +
                    //               " \nTelefono: " + cliente.getTelefono() +
                    //               " \nUsuario: " + cliente.getUsuario() +
                    //               " \nDireccion: " + cliente.getDireccion());
                    
                    
                    
                    // Cerrar la ventana actual
                    vista.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "No existe ningún usuario con estas credenciales. Vuelva a intentarlo.");
                }
            } else if (vista.rbtnEmpleado.isSelected()) {
                persona = empleadoDAO.buscarPorCredenciales(usuario, clave);
                // Evaluar que la busqueda haya sido exitosa
                if (persona instanceof Empleado empleado && empleado.getCodigoEmpleado() != -1) {
                    // Abrir la ventana Empleado, pasando el objeto empleado como argumento
                    VistaEmpleado vistaE = new VistaEmpleado();
                    ControladorEmpleado ctrl = new ControladorEmpleado(vistaE, this, empleado);
                    ctrl.iniciar();
                    
                    //PRUEBAS en el terminal
                    
                    //System.out.println("INGRESO COMO EMEPLADO");
                    //List<Empleado> datos= empleadoDAO.listarPorUsuario(usuario);
                    //
                    // System.out.println("ID: " + empleado.getCodigoEmpleado() +
                    //               " \nNombres: " + empleado.getNombres() +
                    //               " \nApellidos: " + empleado.getApellidos() +
                    //               " \nEmail: " + empleado.getEmail() +
                    //               " \nTelefono: " + empleado.getTelefono() +
                    //               " \nUsuario: " + empleado.getUsuario());
                    
                    
                    // Cerrar la ventana actual
                    vista.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "No existe ningún usuario con estas credenciales. Vuelva a intentarlo.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una opción de ingreso: \n * Cliente \n * Personal de Tambo");
            } 
    }
    
    public void limpiarTodo() {
        // Limpiar las cajas de texto 
        vista.txtUsuarioLogin.setText("");
        vista.txtClaveLogin.setText("");
        // Desmarcar botones
        vista.rbtnCliente.setSelected(false);
        vista.rbtnEmpleado.setSelected(false);
        vista.chkClaveLogin.setSelected(false);
    }    
    
}
