/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.VistaEmpleadoEmpleadosAgregar;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoEmpleadosAgregar implements ActionListener{
    VistaEmpleadoEmpleadosAgregar vista;    
    
    EmpleadoDAO empleadoDAO;        

    public ControladorEmpleadoEmpleadosAgregar(VistaEmpleadoEmpleadosAgregar vista) {
        this.vista = vista;
        vista.btnAutoGenerarUsuario.addActionListener(this);
        vista.btnLimpiarRE.addActionListener(this);
        vista.btnRegistrarRE.addActionListener(this);
    }
    
    public String autoUsuario() {
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        String nombres = vista.txtNombreRE.getText().replace(" ", "");
        String apellidos = vista.txtApellidoRE.getText().replace(" ", "");
        return (nombres + '.' + apellidos + '.' + uuid).toLowerCase();
    }

    public void registrarEmpleado() {
        // Verificar que nombres, apellidos, usuario y clave no estén vacíos
        String nombres = vista.txtNombreRE.getText();
        boolean txtNombreCheck = !nombres.isBlank();
        String apellidos = vista.txtApellidoRE.getText();
        boolean txtApellidoCheck = !nombres.isBlank();
        String usuario = vista.txtUsuarioRE.getText();
        boolean txtUsuarioCheck = !usuario.isBlank();
        String clave = vista.txtClaveRE.getText();
        boolean txtClaveCheck = !clave.isBlank();
        // Confirmar la clave repetida
        String clave2 = vista.txtClaveRepetidaRE.getText();
        boolean confirmarClaveCheck = clave.equals(clave2);
        // Verificar la seleccion del combobox. No debe estar en el primer item (Seleccione)
        int codigoDepartamento = vista.cbxDepartamentoRE.getSelectedIndex();
        boolean cbxDepartamentoCheck = codigoDepartamento != 0;
        
        // Evaluar
        if (txtNombreCheck == false) {
            JOptionPane.showMessageDialog(vista, "El campo Nombres no puede estar vacío.");
        } else if (txtApellidoCheck == false) {
            JOptionPane.showMessageDialog(vista, "El campo Apellidos no puede estar vacío.");
        } else if (cbxDepartamentoCheck == false) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Departamento.");
        } else if (txtUsuarioCheck == false) {
            JOptionPane.showMessageDialog(vista, "El campo Nombre de Usuario no puede estar vacío.");
        } else if (txtClaveCheck == false) {
            JOptionPane.showMessageDialog(vista, "El campo Contraseña no puede estar vacío.");
        } else if (confirmarClaveCheck == false) {
            JOptionPane.showMessageDialog(vista, "Los campos de Contraseña no coinciden.");
        } else {
            // Toda está validado. Leer datos
            String email = vista.txtEmailRE.getText();
            String telefono = vista.txtTelefonoRE.getText();
            
            // Formar objeto con los atributos
            Empleado empleadoNuevo = new Empleado();
            empleadoNuevo.setNombres(nombres);
            empleadoNuevo.setApellidos(apellidos);
            empleadoNuevo.setEmail(email);
            empleadoNuevo.setTelefono(telefono);
            empleadoNuevo.setUsuario(usuario);
            empleadoNuevo.setClave(clave);
            
            // Insertar en la BD
            empleadoDAO = new EmpleadoDAO();
            int res = empleadoDAO.agregar(empleadoNuevo, codigoDepartamento);
            switch (res) {
                case 1 ->
                    JOptionPane.showMessageDialog(vista, "El empleado ha sido registrado con éxito.");
                case 1062 ->
                    JOptionPane.showMessageDialog(vista, "El Nombre de Usuario escogido ya existe. Intente con otro nombre.");
                default ->
                    JOptionPane.showMessageDialog(vista, "Error: No se pudo registrar el empleado.");
            }
            limpiarFormularioRegistroEmpleado();
        }
    }
    
    public void limpiarFormularioRegistroEmpleado() {
        // Limpiar las cajas de texto 
        vista.txtNombreRE.setText("");
        vista.txtApellidoRE.setText("");
        vista.txtEmailRE.setText("");
        vista.txtTelefonoRE.setText("");
        vista.txtUsuarioRE.setText("");
        vista.txtClaveRE.setText("");
        vista.txtClaveRepetidaRE.setText("");
        // Deseleccionar combobox
        vista.cbxDepartamentoRE.setSelectedIndex(0);
        // Desmarcar botones
        vista.chkVerClaveRE.setSelected(false);
        vista.chkVerClaveRepetidaRE.setSelected(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrarRE) {
            registrarEmpleado();
        }
        if (e.getSource() == vista.btnAutoGenerarUsuario) {            
            vista.txtUsuarioRE.setText(autoUsuario());
        } 
        if (e.getSource() == vista.btnLimpiarRE) {
            limpiarFormularioRegistroEmpleado();
        }  
    }
    
    
}
