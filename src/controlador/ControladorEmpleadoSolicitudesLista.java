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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.Departamento;
import modelo.DepartamentoDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.EvaluacionDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;
import vista.VistaEmpleadoSolicitudesLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoSolicitudesLista implements ActionListener,MouseListener{
    VistaEmpleadoSolicitudesLista vista;
    Empleado empleado;
    // DAO
    SolicitudDAO solicitudDAO;
    EvaluacionDAO evaluacionDAO;
    DepartamentoDAO departamentoDAO;
    ClienteDAO clienteDAO;
    EmpleadoDAO empleadoDAO;
    
    // Table models
    DefaultTableModel modeloSol;

    public ControladorEmpleadoSolicitudesLista(VistaEmpleadoSolicitudesLista vista, Empleado empleado) {
        this.vista=vista;
        this.empleado=empleado;
        
        vista.btnActualizarListaSol.addActionListener(this);
        vista.tbSolicitudes.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnActualizarListaSol){
            limpiarTabla(vista.tbSolicitudes);
            listarSolicitudes();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tbSolicitudes) {                        
            // Seleccionar una fila en la tabla de Solicitudes
            int fila = vista.tbSolicitudes.getSelectedRow();
            if (fila != -1) {
                int idSolicitud = Integer.parseInt(vista.tbSolicitudes.getValueAt(fila, 0).toString());
                // Llenar la información de la solicitud
                llenarDetallesSolicitud(idSolicitud);                                
            }
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
    
    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    }   
    
    public void listarSolicitudes() {
        modeloSol = (DefaultTableModel)vista.tbSolicitudes.getModel();
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        List<Solicitud> lista;
        // Según el departamento del empleado ...
        Departamento dep = empleado.obtenerDepartamento();
        // ... y la selección del combobox
        lista = switch (vista.cbxFiltrarEstadoSol.getSelectedIndex()) {
            case 1 -> solicitudDAO.listarPorEstadoyDepartamento(Solicitud.EN_ESPERA, dep.getCodigoDepartamento());
            case 2 -> solicitudDAO.listarPorEstadoyDepartamento(Solicitud.EN_TRAMITE, dep.getCodigoDepartamento());
            case 3 -> solicitudDAO.listarPorEstadoyDepartamento(Solicitud.FINALIZADO, dep.getCodigoDepartamento());
            default -> solicitudDAO.listarPorDepartamento(dep.getCodigoDepartamento());
        };
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloSol.addRow(fila);
        }        
        vista.tbSolicitudes.setModel(modeloSol);
    }
    public void llenarDetallesSolicitud(int idSolicitud) {
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        Solicitud solicitud = solicitudDAO.buscarPorId(idSolicitud);
        
        // Vaciar y llenar caja
        vista.txaDetalleSolicitud.setText("");
        vista.txaDetalleSolicitud.append(solicitud.mostrarDetalleSolicitud());
    }
}
