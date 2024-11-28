/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.VistaEmpleadoEmpleadosLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoEmpleadosLista implements ActionListener{
    VistaEmpleadoEmpleadosLista vista;
    Empleado empleado;
    
    EmpleadoDAO empleadoDAO;
    
    DefaultTableModel modeloEmp;
    public ControladorEmpleadoEmpleadosLista(VistaEmpleadoEmpleadosLista vista, Empleado empleado) {
        this.vista = vista;
        this.empleado = empleado;
        vista.btnBuscarEmpleado.addActionListener(this);
    }
    
    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    } 
    
    public void listarEmpleados() {
        modeloEmp = (DefaultTableModel)vista.tbEmpleados.getModel();
        // Consultar la BD
        empleadoDAO = new EmpleadoDAO();
        List<Empleado> lista;
        // Según el usuario ingresado ... 
        // Si la caja se deja vacía, devuelve la lista completa.
        String usuario = vista.txtBuscarUsuarioEmpleado.getText().strip();
        // ... y la selección del combobox
        // Si se deja en 'Todos', buscará de todos los departamentos
        int codigoDepartamento = vista.cbxBuscarDepartamento.getSelectedIndex();
        if (codigoDepartamento >= 1 && codigoDepartamento <= 5) {
            lista = empleadoDAO.listarPorUsuarioyDepartamento(usuario, codigoDepartamento);
        } else {
            lista = empleadoDAO.listarPorUsuario(usuario);
        }
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloEmp.addRow(fila);
        }        
        vista.tbEmpleados.setModel(modeloEmp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == vista.btnBuscarEmpleado) {
            limpiarTabla(vista.tbEmpleados);
            listarEmpleados();
        }
    }
    
    
}
