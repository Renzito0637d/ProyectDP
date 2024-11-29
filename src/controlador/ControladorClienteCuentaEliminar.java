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
import vista.VistaClienteCuentaEliminar;

/**
 *
 * @author Renzo
 */
public class ControladorClienteCuentaEliminar implements ActionListener{
    VistaClienteCuentaEliminar vista;
    Cliente cliente;
    ControladorLogin controladorPrevio;
    ControladorCliente ctlCliente;
    
    ClienteDAO clienteDAO;

    public ControladorClienteCuentaEliminar(VistaClienteCuentaEliminar vista, Cliente cliente,ControladorCliente ctlCliente) {
        this.vista = vista;
        this.cliente = cliente;
        this.ctlCliente=ctlCliente;
        
        vista.btnEliminarCU.addActionListener(this);
        vista.lbUser.setText(cliente.getUsuario());
    }
    
    public void eliminarCliente(){
        clienteDAO = new ClienteDAO();
        // Mostrar mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Desea eliminar su cuenta?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación.",
                JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int res = clienteDAO.eliminar(cliente.getCodigoCliente());
                switch (res) {
                    case 1 ->{
                        JOptionPane.showMessageDialog(vista, "Se ha eliminado el cliente");
                        ctlCliente.salir();}                    
                    case 1451 -> JOptionPane.showMessageDialog(vista, "No puede eliminar este cliente porque está asociado a algunas solicitudes en el sistema.");
                    default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el cliente.");
                }
            }
            else {
                JOptionPane.showMessageDialog(vista, "Eliminación cancelada");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnEliminarCU){
            eliminarCliente();                                           
        }
    }
    
    
}
