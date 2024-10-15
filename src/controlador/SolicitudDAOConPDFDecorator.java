/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Solicitud;
import modelo.SolicitudDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.ISolicitudDAO;

public class SolicitudDAOConPDFDecorator implements ISolicitudDAO {
    
    private final SolicitudDAO solicitudDAO;
    private ClienteDAO clienteDAO;
    
    public SolicitudDAOConPDFDecorator(SolicitudDAO solicitudDAO) {
        this.solicitudDAO = solicitudDAO;
        this.clienteDAO= new ClienteDAO();
    }
    
    @Override
    public int agregar(Solicitud solicitud, int codigoCliente) {
        // Llamar al método original para agregar la solicitud en la BD
        int result = solicitudDAO.agregar(solicitud, codigoCliente);
        
        if (result == 1) {
            // Generar PDF si la solicitud fue registrada con éxito
            generarPDF(solicitud,codigoCliente);
        }
        
        return result;
    }
    
   private void generarPDF(Solicitud solicitud, int codigoCliente) {
        Document document = new Document();
        try {
            // Ruta y nombre del archivo PDF
// Generar un número aleatorio entre 0 y 999999
        int randomNumber = (int) (Math.random() * 1000000);
        
        // Crear el nombre del archivo con el número aleatorio
        String fileName = "Solicitud_" + randomNumber + ".pdf";
 
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
            // Abrir el documento
            document.open();
            
            // Añadir contenido al PDF
            document.add(new Paragraph("Solicitud de " + solicitud.getTipoSolicitud()));
            document.add(new Paragraph("Fecha de Ingreso: " + solicitud.getFechaIngreso().toString()));           

            // Obtener el cliente utilizando el ClienteDAO
            Cliente cliente = clienteDAO.buscarPorCodigo(codigoCliente);
            if (cliente.getCodigoCliente() != -1) { // Verificar que el cliente se encontró
                document.add(new Paragraph("Cliente: " + cliente.getNombres() + " " + cliente.getApellidos()));
                document.add(new Paragraph("Email: " + cliente.getEmail()));
                document.add(new Paragraph("Teléfono: " + cliente.getTelefono()));
                document.add(new Paragraph("Dirección: " + cliente.getDireccion()));
            } else {
                document.add(new Paragraph("Cliente no encontrado."));
            }

            document.add(new Paragraph("Estado Actual: En espera"));
            document.add(new Paragraph("Descripción: " + solicitud.getDescripcion()));
            
            // Detalles de la compra
            document.add(new Paragraph("Detalles de la Compra:"));
            document.add(new Paragraph("Fecha de la compra: " + solicitud.getCompra().getFecha()));
            document.add(new Paragraph("Monto reclamado: " + solicitud.getCompra().getMontoReclamado()));
            document.add(new Paragraph("Canal de Compra: " + solicitud.getCompra().getCanalCompra()));
            
            // Cerrar el documento
            document.close();
            
            System.out.println("PDF generado exitosamente: " + fileName);
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int actualizar(Solicitud bean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Solicitud> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Solicitud> listarPorCliente(int codigoCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
