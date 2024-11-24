/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Empleado;
import vista.VistaEmpleadoClientesAgregar;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoClientesAgregar implements ActionListener{
    VistaEmpleadoClientesAgregar vista;
    Empleado empleado;
    ClienteDAO clienteDAO;   


    public ControladorEmpleadoClientesAgregar(VistaEmpleadoClientesAgregar vista, Empleado empleado) {
        this.vista = vista;
        this.empleado = empleado;
        
        this.vista.btnAutoGenerarUsuario.addActionListener(this);
        this.vista.btnRegistrarRC.addActionListener(this);
        this.vista.btnLimpiarRC.addActionListener(this);        
    }

    public String autoGenerarUsuario() {
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        String nombres = vista.txtNombreRC.getText().replace(" ", "");
        String apellidos = vista.txtApellidoRC.getText().replace(" ", "");
        return (nombres + '.' + apellidos + '.' + uuid).toLowerCase();
    }

    public void limpiar() {
        vista.txtNombreRC.setText("");
        vista.txtApellidoRC.setText("");
        vista.txtEmailRC.setText("");
        vista.txtTelefonoRC.setText("");
        vista.txtDireccionRC.setText("");
        vista.txtUsuarioRC.setText("");
        vista.txtClaveRC.setText("");
        vista.txtClaveRepetidaRC.setText("");
        vista.chkVerClaveRC.setSelected(false);
        vista.chkVerClaveRepetidaRC.setSelected(false);
    }

    public void registrarCliente() {
        String nombres = vista.txtNombreRC.getText();
        boolean txtNombreCheck = !nombres.isBlank();
        String apellidos = vista.txtApellidoRC.getText();
        boolean txtApellidoCheck = !apellidos.isBlank();
        String usuario = vista.txtUsuarioRC.getText();
        boolean txtUsuarioCheck = !usuario.isBlank();
        String clave = vista.txtClaveRC.getText();
        boolean txtClaveCheck = !clave.isBlank();
        String clave2 = vista.txtClaveRepetidaRC.getText();
        boolean confirmarClaveCheck = clave.equals(clave2);

        if (!txtNombreCheck) {
            JOptionPane.showMessageDialog(vista, "El campo Nombres no puede estar vacío.");
        } else if (!txtApellidoCheck) {
            JOptionPane.showMessageDialog(vista, "El campo Apellidos no puede estar vacío.");
        } else if (!txtUsuarioCheck) {
            JOptionPane.showMessageDialog(vista, "El campo Nombre de Usuario no puede estar vacío.");
        } else if (!txtClaveCheck) {
            JOptionPane.showMessageDialog(vista, "El campo Contraseña no puede estar vacío.");
        } else if (!confirmarClaveCheck) {
            JOptionPane.showMessageDialog(vista, "Los campos de Contraseña no coinciden.");
        } else {
            String email = vista.txtEmailRC.getText();
            String telefono = vista.txtTelefonoRC.getText();
            String direccion = vista.txtDireccionRC.getText();
            Cliente cliente = new Cliente();
            cliente.setDireccion(direccion);
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setUsuario(usuario);
            cliente.setClave(clave);
            clienteDAO = new ClienteDAO();
            int res = clienteDAO.agregar(cliente);
            switch (res) {
                case 1 -> JOptionPane.showMessageDialog(vista, "El cliente ha sido registrado con éxito.");
                case 1062 -> JOptionPane.showMessageDialog(vista, "El Nombre de Usuario escogido ya existe. Intente con otro nombre.");
                default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo registrar el cliente.");
            }
            limpiar();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrarRC) {
            registrarCliente();
        }
        if (e.getSource() == vista.btnAutoGenerarUsuario) {            
            vista.txtUsuarioRC.setText(autoGenerarUsuario());
        } 
        if (e.getSource() == vista.btnLimpiarRC) {
            limpiar();
        }                 
    }    
    
}
