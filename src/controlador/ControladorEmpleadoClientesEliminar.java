/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Empleado;
import vista.VistaEmpleadoClientesEliminar;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoClientesEliminar implements ActionListener,MouseListener{
    VistaEmpleadoClientesEliminar vista;
    Empleado empleado;
    // DAO    
    ClienteDAO clienteDAO;    
    
    // Table models  
    DefaultTableModel modeloCli;   

    public ControladorEmpleadoClientesEliminar(VistaEmpleadoClientesEliminar vista, Empleado empleado) {
        this.vista = vista;
        this.empleado = empleado;
        
        vista.btnBuscarCliente.addActionListener(this);
        vista.tbClientes.addMouseListener(this);
    }

    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    }   
    
    public void listarClientes() {
        modeloCli = (DefaultTableModel)vista.tbClientes.getModel();
        // Consultar la BD
        clienteDAO = new ClienteDAO();
        List<Cliente> lista;
        // Según el usuario ingresado. Si la caja se deja vacía, devuelve la lista completa.
        String usuario = vista.txtBuscarUsuarioCliente.getText().strip();
        lista = clienteDAO.listarPorUsuario(usuario);
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloCli.addRow(fila);
        }        
        vista.tbClientes.setModel(modeloCli);
    }
    
    public void eliminarCliente() {        
        int fila = vista.tbClientes.getSelectedRow();        
        clienteDAO = new ClienteDAO();
        int codigoCliente;
        if (fila != -1) {
            try {               
                codigoCliente = Integer.parseInt(vista.tbClientes.getValueAt(fila, 0).toString());
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error: "+e);
                return;
            }
            
            // Obtener cliente                
            Cliente cliente = clienteDAO.buscarPorCodigo(codigoCliente);            
            // Mostrar mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Desea eliminar este registro de cliente?\n"+cliente.getUsuario()+"\nEsta acción no se puede deshacer.",
                "Confirmar eliminación.",
                JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int res = clienteDAO.eliminar(codigoCliente);
                switch (res) {
                    case 1 -> JOptionPane.showMessageDialog(vista, "Se ha eliminado el cliente");
                    case 1451 -> JOptionPane.showMessageDialog(vista, "No puede eliminar este cliente porque está asociado a algunas solicitudes en el sistema.");
                    default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el cliente.");
                }
            }
            else {
                JOptionPane.showMessageDialog(vista, "Eliminación cancelada");
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarCliente) {
            limpiarTabla(vista.tbClientes);
            listarClientes();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tbClientes) {
            eliminarCliente();
            limpiarTabla(vista.tbClientes);
            listarClientes();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {            }

    @Override
    public void mouseReleased(MouseEvent e) {            }

    @Override
    public void mouseEntered(MouseEvent e) {            }

    @Override
    public void mouseExited(MouseEvent e) {            }
    
    
}
