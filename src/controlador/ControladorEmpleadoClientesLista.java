/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
/**
 *
 * @author Renzo
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Empleado;
import vista.VistaEmpleadoClientesLista;

public class ControladorEmpleadoClientesLista implements ActionListener{
    VistaEmpleadoClientesLista vista;
    Empleado empleado;
    // DAO    
    ClienteDAO clienteDAO;    
    
    // Table models  
    DefaultTableModel modeloCli;    

    public ControladorEmpleadoClientesLista(VistaEmpleadoClientesLista vista, Empleado empleado) {
        this.vista = vista;
        this.empleado = empleado;
        vista.btnBuscarCliente.addActionListener(this);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarCliente) {
            limpiarTabla(vista.tbClientes);
            listarClientes();
        }
    }
}
