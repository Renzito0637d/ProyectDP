/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Encuesta;
import modelo.EncuestaDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;
import vista.VistaClienteEncuestasCompletar;
import vista.VistaEncuesta;

/**
 *
 * @author Renzo
 */
public class ControladorClienteEncuestasCompletar implements ActionListener{
    VistaClienteEncuestasCompletar vista;
    
    Cliente cliente;
    // DAO
    SolicitudDAO solicitudDAO;
    EncuestaDAO encuestaDAO;
    
    // Table models
    DefaultTableModel modeloEnc;

    public ControladorClienteEncuestasCompletar(VistaClienteEncuestasCompletar vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
        vista.btnActualizarListaEncuestas.addActionListener(this);
        vista.btnVerEncuesta.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnActualizarListaEncuestas){
            limpiarTabla(vista.tbEncuestas);
            listarEncuestas();
        }
        if(e.getSource()==vista.btnVerEncuesta){
            verEncuesta();                        
        }
    }
    
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    
    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
    }   
    
    public static void limpiarPanel(JPanel panel) { 
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
    
    public void listarEncuestas() {
        modeloEnc = (DefaultTableModel)vista.tbEncuestas.getModel();
        // Consultar la BD
        solicitudDAO = new SolicitudDAO();
        List<Solicitud> lista;
        // Según la selección del combobox
        lista = solicitudDAO.listarPorEncuestayCliente(Encuesta.ACTIVA, cliente.getCodigoCliente());
        // Llenar modelo y configurar tabla
        Object[] fila;
        for (int i = 0; i < lista.size(); i++) {
            fila = lista.get(i).mostrarRegistroTabla();            
            modeloEnc.addRow(fila);
        }        
        vista.tbEncuestas.setModel(modeloEnc);        
    }
    
    public void verEncuesta() {
        int fila = vista.tbEncuestas.getSelectedRow();
        if (fila == -1) {
            // No se ha seleccionado ninguna solicitud
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un registro de la tabla.");
        } else {
            try {
                // Buscar la encuesta correspondiente en la BD
                int idSolicitud = Integer.parseInt(vista.tbEncuestas.getValueAt(fila, 0).toString());
                encuestaDAO = new EncuestaDAO();
                Encuesta encuesta =encuestaDAO.buscarPorSolicitud(idSolicitud);
                
                // Abrir la ventana Encuesta, pasando los argumentos necesarios
                
                            VistaEncuesta enc =new VistaEncuesta();
            CambiarPanel(enc);
                new ControladorEncuestas(enc, encuesta);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Hubo un problema. ERROR: "+e.getMessage());
            }
        }
    }
    
}
