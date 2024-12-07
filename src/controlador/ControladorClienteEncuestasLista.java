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
import modelo.Cliente;
import modelo.Encuesta;
import modelo.EncuestaDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;
import vista.VistaClienteEncuestasLista;

/**
 *
 * @author Renzo
 */
public class ControladorClienteEncuestasLista implements ActionListener{
    
    VistaClienteEncuestasLista vista;
    // El usuario que ha accedido a través del login
    Cliente cliente;
    
    // DAO
    SolicitudDAO solicitudDAO;        
    EncuestaDAO encuestaDAO;
    
    // Table models
    DefaultTableModel modeloEnc;

    public ControladorClienteEncuestasLista(VistaClienteEncuestasLista vista,Cliente cliente) {
        this.vista=vista;
        this.cliente=cliente;
        vista.btnActualizarListaEncuestas.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnActualizarListaEncuestas){
            limpiarTabla(vista.tbEncuestas);
            listarEncuestas();
        }
    }
    
    public void limpiarTabla(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;        
        }
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
        vista.txtNumeroEncuestas.setText(""+modeloEnc.getRowCount());
    }
}
