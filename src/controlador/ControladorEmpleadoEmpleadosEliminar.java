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
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.VistaEmpleadoEmpleadosEliminar;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoEmpleadosEliminar implements ActionListener,MouseListener{
    VistaEmpleadoEmpleadosEliminar vista;    
    
    EmpleadoDAO empleadoDAO;
    
    DefaultTableModel modeloEmp;

    public ControladorEmpleadoEmpleadosEliminar(VistaEmpleadoEmpleadosEliminar vista) {
        this.vista = vista;       
        
        vista.btnBuscarEmpleado.addActionListener(this);
        this.vista.tbEmpleados.addMouseListener(this);
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
    
    public void eliminarEmpleado() {        
        int fila = vista.tbEmpleados.getSelectedRow();        
        empleadoDAO = new EmpleadoDAO();
        int codigoEmpleado;
        if (fila != -1) {
            try {               
                codigoEmpleado = Integer.parseInt(vista.tbEmpleados.getValueAt(fila, 0).toString());
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error: "+e);
                return;
            }
            
            // Obtener empleado
            Empleado empleado = empleadoDAO.buscarPorCodigo(codigoEmpleado);
            // Mostrar mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Desea eliminar este registro de empleado?\n"+empleado.getUsuario()+"\nEsta acción no se puede deshacer.",
                "Confirmar eliminación.",
                JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int res = empleadoDAO.eliminar(codigoEmpleado);
                switch (res) {
                    case 1 -> JOptionPane.showMessageDialog(vista, "Se ha eliminado el empleado");
                    case 1451 -> JOptionPane.showMessageDialog(vista, "No puede eliminar este empleado porque está asociado a algunas evaluaciones en el sistema.");
                    default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el empleado.");
                }
            }
            else {
                JOptionPane.showMessageDialog(vista, "Eliminación cancelada");
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarEmpleado) {
            limpiarTabla(vista.tbEmpleados);
            listarEmpleados();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tbEmpleados) {
            eliminarEmpleado();
            limpiarTabla(vista.tbEmpleados);
            listarEmpleados();
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
