/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import vista.VistaClienteCuentaActualizar;

/**
 *
 * @author Renzo
 */
public class ControladorClienteCuentaActualizar implements ActionListener{
    VistaClienteCuentaActualizar vista;
    ControladorCliente ctlCliente;
    
    Cliente cliente;
    ClienteDAO clienteDAO;

    public ControladorClienteCuentaActualizar(VistaClienteCuentaActualizar vista, Cliente cliente,ControladorCliente ctlCliente) {
        this.vista = vista;
        this.cliente = cliente;
        this.ctlCliente=ctlCliente;
        vista.btnActualizar.addActionListener(this);
        cargarDatos();
    }
    private void cargarDatos(){
        vista.txtUser.setText(cliente.getUsuario());
        vista.txtNombre.setText(cliente.getNombres());
        vista.txtApellido.setText(cliente.getApellidos());
        vista.txtCorreo.setText(cliente.getEmail());
        vista.txtDirccion.setText(cliente.getDireccion());
        vista.txtTelefono.setText(cliente.getTelefono());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnActualizar){
            actualizarCuenta();
        }        
    }
    
    private void actualizarCuenta(){
        String user=vista.txtUser.getText();
        String nombre=vista.txtNombre.getText();
        String apellido=vista.txtApellido.getText();
        String email=vista.txtCorreo.getText();
        String tele=vista.txtTelefono.getText();
        String dire=vista.txtDirccion.getText();
        int code=cliente.getCodigoCliente();
        String contra=cliente.getClave();
        
        Cliente clieAc=new Cliente();
        clieAc.setCodigoCliente(code);
        clieAc.setUsuario(user);
        clieAc.setNombres(nombre);
        clieAc.setApellidos(apellido);
        clieAc.setEmail(email);
        clieAc.setTelefono(tele);
        clieAc.setDireccion(dire);
        
        clieAc.setClave(contra);
        System.out.println("Código Cliente enviado: " + cliente.getCodigoCliente());

        clienteDAO = new ClienteDAO();
        int res =clienteDAO.actualizar(clieAc);
        switch (res) {
            case 1 -> {JOptionPane.showMessageDialog(vista, "El cliente ha sido actualizado con éxito. Reinicio requerido");
                      ctlCliente.salir();
            }
            case 1062 -> JOptionPane.showMessageDialog(vista, "El Nombre de Usuario escogido ya existe. Intente con otro nombre.");
            default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo actualizar el cliente.");
        }
    }
}
