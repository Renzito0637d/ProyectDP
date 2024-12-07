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
import modelo.Departamento;
import modelo.DepartamentoDAO;
import modelo.Empleado;
import modelo.Encuesta;
import modelo.EncuestaDAO;
import modelo.Evaluacion;
import modelo.EvaluacionDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;
import vista.VistaEmpleadoSolicitudesEvaluar;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoSolicitudesEvaluar implements ActionListener,MouseListener{
    VistaEmpleadoSolicitudesEvaluar vista;
    Empleado empleado;
    // DAO
    SolicitudDAO solicitudDAO;
    EvaluacionDAO evaluacionDAO;
    DepartamentoDAO departamentoDAO;
    EncuestaDAO encuestaDAO;
    
    // Table models
    DefaultTableModel modeloSol;
    DefaultTableModel modeloEva;

    public ControladorEmpleadoSolicitudesEvaluar(VistaEmpleadoSolicitudesEvaluar vista, Empleado empleado) {
        this.vista = vista;
        this.empleado = empleado;
        vista.btnActualizarListaSol.addActionListener(this);
        vista.tbSolicitudes.addMouseListener(this);
        vista.tbEvaluaciones.addMouseListener(this);
        vista.btnEvaluarSol.addActionListener(this);
        vista.btnLimpiarSol.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnActualizarListaSol){
            limpiarTabla(vista.tbSolicitudes);
            listarSolicitudes();
        }
        if (e.getSource() == vista.rbtnFinalizado) {
            // Una evaluación en estado finalizado solo puede enviarse a Gerencia
            // Desactiva el cbx de Departamento
            vista.cbxDepartamentoSol.setEnabled(false);
            // Ubica el selector en "Gerencia"
            vista.cbxDepartamentoSol.setSelectedIndex(4);
        }
        if (e.getSource() == vista.rbtnTramite) {
            // Activa el cbx de Departamento
            vista.cbxDepartamentoSol.setEnabled(true);
            // Ubica el selector en "Seleccione"
            vista.cbxDepartamentoSol.setSelectedIndex(0);
        }        
        if (e.getSource() == vista.btnEvaluarSol) {
            crearEvaluacion();
        }
        if (e.getSource() == vista.btnLimpiarSol) {
            limpiarTodo();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
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
    
    public void listarEvaluaciones(int idSolicitud) {
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
    
    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    }    

    public void limpiarDetallesEvaluacion() {
        vista.txaDetalleEvaluacion.setText("");
        vista.txaDescripcionEvaluacion.setText("");
    }
    

    
    public void limpiarTodo() {
        limpiarTabla(vista.tbSolicitudes);
        limpiarTabla(vista.tbEvaluaciones);
        vista.txaDetalleSolicitud.setText("");
        limpiarDetallesEvaluacion();               
    }
    
    public void llenarDetallesSolicitud(int idSolicitud) {
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        Solicitud solicitud = solicitudDAO.buscarPorId(idSolicitud);
        
        // Vaciar y llenar caja
        vista.txaDetalleSolicitud.setText("");
        vista.txaDetalleSolicitud.append(solicitud.mostrarDetalleSolicitud());
    }
    
    public void llenarDetallesEvaluacion(int numeroEvaluacion) {
        // Consultar la BD
        evaluacionDAO = new EvaluacionDAO();
        Evaluacion ev = evaluacionDAO.buscarPorId(numeroEvaluacion);
        
        // Vaciar y llenar caja
        vista.txaDetalleEvaluacion.setText("");
        vista.txaDetalleEvaluacion.append(ev.mostrarInformacionCaja());
    }

    // OPERACIONES DE INSERCION-ACTUALIZACION-DELECION
    public void crearEvaluacion() {
        // La evaluacion se crea sobre una solicitud
        // Verificar que una solicitud haya sido seleccionada
        int idSolicitud = leerSolicitudSeleccionada();
        boolean solicitudCheck = idSolicitud != -1;
        // Verificar que algún radio de estado esté seleccionado
        int estado = (vista.rbtnTramite.isSelected() ? Evaluacion.EN_TRAMITE : Evaluacion.FINALIZADO);
        boolean estadoCheck = vista.rbtnTramite.isSelected() || vista.rbtnFinalizado.isSelected();
        // Verificar la seleccion del combobox. No debe estar en el primer item (Seleccione)
        int codigoDepartamento = vista.cbxDepartamentoSol.getSelectedIndex();
        boolean cbxDepartamentoCheck = codigoDepartamento != 0;
        // Verificar que el contenido no esté vacío
        String descripcion = vista.txaDescripcionEvaluacion.getText();
        boolean descripcionCheck = !descripcion.isBlank();
        
        // Verificar
        if (solicitudCheck == false) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una solicitud en la tabla de Solicitudes.");
        }
        else if (estadoCheck == false) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una opción de Estado:\n * EN TRÁMITE \n * FINALIZADO");
        }
        else if (cbxDepartamentoCheck == false) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Departamento.");
        }
        else if (descripcionCheck == false) {
            JOptionPane.showMessageDialog(vista,"La descripción de la evaluación no puede estar vacía.");
        }
        else {
            // Toda está validado. Formar objeto con los atributos            
            Evaluacion eval = new Evaluacion();
            eval.setFechaHoraActual();
            eval.setEstado(estado);
            eval.setDescripcion(descripcion);
            eval.setEmpleado(empleado);
            
            // Insertar en la BD
            evaluacionDAO = new EvaluacionDAO();
            int res = evaluacionDAO.agregar(eval, idSolicitud);
            if (res != 1) {
                JOptionPane.showMessageDialog(vista, "Error: No se pudo crear la evaluación. ERROR: "+res);
            }
            else {
                JOptionPane.showMessageDialog(vista, "Se ha registrado la evaluación con éxito.");
                
                // Actualizar la solicitud para cambiar el departamento evaluador y el estado actual
                solicitudDAO = new SolicitudDAO();
                departamentoDAO = new DepartamentoDAO();
                Solicitud sol = solicitudDAO.buscarPorId(idSolicitud);
                Departamento dep = departamentoDAO.buscarPorCodigo(codigoDepartamento);
                sol.setDepartamentoEvaluador(dep);
                sol.setEstadoActual(estado);
                solicitudDAO.actualizar(sol);

                // Si el estado de la evaluación cambia a FINALIZADO, el proceso ha terminado y
                // debe activarse la Encuesta de la solicitud para el cliente
                if (estado == Evaluacion.FINALIZADO) {
                    // Recuperar la encuesta
                    encuestaDAO = new EncuestaDAO();
                    Encuesta encuesta = encuestaDAO.buscarPorSolicitud(idSolicitud);
                    // Activar la encuesta, en caso aún esté inactiva
                    if (encuesta.getEstado() == Encuesta.INACTIVA) {
                        encuesta.activarEncuesta();
                        encuestaDAO.actualizar(encuesta);
                    }
                }
            }
            
            limpiarTodo();            
        }
    }
    


    public int leerSolicitudSeleccionada() {
        int fila = vista.tbSolicitudes.getSelectedRow();
        if (fila == -1) {
            // No se ha seleccionado ninguna solicitud
            return -1;
        } else {
            try {
                int idSolicitud = Integer.parseInt(vista.tbSolicitudes.getValueAt(fila, 0).toString());
                return idSolicitud;
            } catch (Exception e) {
                return -1;
            }
        }
    }
}
