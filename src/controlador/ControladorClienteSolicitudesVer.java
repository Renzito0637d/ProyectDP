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
import modelo.Cliente;
import modelo.Evaluacion;
import modelo.EvaluacionDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;
import vista.VistaClienteSolicitudesHistorial;

/**
 *
 * @author Renzo
 */
public class ControladorClienteSolicitudesVer implements ActionListener, MouseListener{

    VistaClienteSolicitudesHistorial vista;
    Cliente cliente;
    // DAO
    SolicitudDAO solicitudDAO;
    EvaluacionDAO evaluacionDAO;

    public ControladorClienteSolicitudesVer(VistaClienteSolicitudesHistorial vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
        this.vista.btnActualizarListaSol.addActionListener(this);
        this.vista.tbSolicitudes.addMouseListener(this);
        this.vista.tbEvaluaciones.addMouseListener(this);
        limpiarTodo();
    }
        
    // Table models
    DefaultTableModel modeloSol;
    DefaultTableModel modeloEva;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnActualizarListaSol) {
            limpiarTabla(vista.tbSolicitudes);
            listarSolicitudes();
        }              
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // EVENTOS - TAB VER MIS SOLICITUDES
        if (e.getSource() == vista.tbSolicitudes) {
            // Vaciar la tabla de Evaluaciones
            limpiarTabla(vista.tbEvaluaciones);
            // Seleccionar una fila en la tabla de Solicitudes
            int fila = vista.tbSolicitudes.getSelectedRow();
            if (fila != -1) {
                int idSolicitud = Integer.parseInt(vista.tbSolicitudes.getValueAt(fila, 0).toString());
                // Llenar la información de la solicitud
                llenarDetallesSolicitud(idSolicitud);
                // Llenar la tabla de Evaluaciones
                listarEvaluaciones(idSolicitud);
            }
        }        
        if (e.getSource() == vista.tbEvaluaciones) {
            // Seleccionar una fila en la tabla de Evaluaciones
            int fila = vista.tbEvaluaciones.getSelectedRow();
            if (fila != -1) {
                int numeroEvaluacion = Integer.parseInt(vista.tbEvaluaciones.getValueAt(fila, 0).toString());
                // Llenar la información de la evaluación
                llenarDetallesEvaluacion(numeroEvaluacion);
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
    
    private void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    }
    
    private void limpiarDetallesEvaluacion() {
        vista.txaDetalleEvaluacion.setText("");        
    }
    
    private void limpiarTodo() {
        limpiarTabla(vista.tbSolicitudes);
        limpiarTabla(vista.tbEvaluaciones);       
        limpiarDetallesEvaluacion();
    }
    
    private void llenarDetallesSolicitud(int idSolicitud) {
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        Solicitud solicitud = solicitudDAO.buscarPorId(idSolicitud);
        
        // Vaciar y llenar caja
        vista.txaDetalleSolicitud.setText("");
        vista.txaDetalleSolicitud.append(solicitud.mostrarDetalleSolicitud());
    }
    
    private void llenarDetallesEvaluacion(int numeroEvaluacion) {
        // Consultar la BD
        evaluacionDAO = new EvaluacionDAO();
        Evaluacion ev = evaluacionDAO.buscarPorId(numeroEvaluacion);        
        
        // Vaciar y llenar caja
        vista.txaDetalleEvaluacion.setText("");
        vista.txaDetalleEvaluacion.append(ev.mostrarInformacionCaja());
    }

    private void listarEvaluaciones(int idSolicitud) {
        modeloEva = (DefaultTableModel)vista.tbEvaluaciones.getModel();
        // Consultar la BD
        evaluacionDAO = new EvaluacionDAO();
        List<Evaluacion> lista = evaluacionDAO.listarPorSolicitud(idSolicitud);
        
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloEva.addRow(fila);
        }        
        vista.tbEvaluaciones.setModel(modeloEva);
    }
    
    private void listarSolicitudes() {
        modeloSol = (DefaultTableModel)vista.tbSolicitudes.getModel();
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        List<Solicitud> lista;
        // Según la selección del combobox
        lista = switch (vista.cbxFiltrarEstadoSol.getSelectedIndex()) {
            case 1 -> solicitudDAO.listarPorEstadoyCliente(Solicitud.EN_ESPERA, cliente.getCodigoCliente());
            case 2 -> solicitudDAO.listarPorEstadoyCliente(Solicitud.EN_TRAMITE, cliente.getCodigoCliente());
            case 3 -> solicitudDAO.listarPorEstadoyCliente(Solicitud.FINALIZADO, cliente.getCodigoCliente());
            default -> solicitudDAO.listarPorCliente(cliente.getCodigoCliente());
        };
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloSol.addRow(fila);
        }        
        vista.tbSolicitudes.setModel(modeloSol);
    }
    
}
