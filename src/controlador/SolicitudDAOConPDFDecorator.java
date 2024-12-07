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

    private final ISolicitudDAO solicitudDAO;
    private final ClienteDAO clienteDAO;

    public SolicitudDAOConPDFDecorator(ISolicitudDAO solicitudDAO) {
    this.solicitudDAO = solicitudDAO;
    this.clienteDAO = new ClienteDAO();
    System.out.println("Decorador de SolicitudDAO inicializado.");
}

    @Override
    public int agregar(Solicitud solicitud, int codigoCliente) {
        // Llamar al método original para agregar la solicitud en la BD
        int result = solicitudDAO.agregar(solicitud, codigoCliente);

        if (result != 1) {
            // Generar PDF si la solicitud fue registrada con éxito
            boolean pdfGenerated = generarPDF(solicitud, codigoCliente);
            if (!pdfGenerated) {
                System.err.println("El PDF no pudo ser generado.");
            }
        }

        return result;
    }

    private boolean generarPDF(Solicitud solicitud, int codigoCliente) {
        Document document = new Document();
        try {
            // Generar un nombre de archivo único
            int randomNumber = (int) (Math.random() * 1000000);
            String filePath = System.getProperty("user.home") + "/Documents/Solicitud_" + randomNumber + ".pdf";

            System.out.println("Generando PDF en la ruta: " + filePath);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Abrir el documento
            document.open();

            // Añadir contenido al PDF
            document.add(new Paragraph("Solicitud de " + (solicitud.getTipoSolicitud() != null ? solicitud.getTipoSolicitud() : "N/A")));
            document.add(new Paragraph("Fecha de Ingreso: " + (solicitud.getFechaIngreso() != null ? solicitud.getFechaIngreso().toString() : "N/A")));

            // Obtener y añadir los datos del cliente
            Cliente cliente = clienteDAO.buscarPorCodigo(codigoCliente);
            if (cliente != null && cliente.getCodigoCliente() != -1) {
                document.add(new Paragraph("Cliente: " + cliente.getNombres() + " " + cliente.getApellidos()));
                document.add(new Paragraph("Email: " + cliente.getEmail()));
                document.add(new Paragraph("Teléfono: " + cliente.getTelefono()));
                document.add(new Paragraph("Dirección: " + cliente.getDireccion()));
            } else {
                document.add(new Paragraph("Cliente no encontrado."));
            }

            // Añadir detalles de la compra
            if (solicitud.getCompra() != null) {
                document.add(new Paragraph("Detalles de la Compra:"));
                document.add(new Paragraph("Fecha de la compra: " + (solicitud.getCompra().getFecha() != null ? solicitud.getCompra().getFecha() : "N/A")));
                document.add(new Paragraph("Monto reclamado: " + solicitud.getCompra().getMontoReclamado()));
                document.add(new Paragraph("Canal de Compra: " + (solicitud.getCompra().getCanalCompra() != null ? solicitud.getCompra().getCanalCompra() : "N/A")));
            } else {
                document.add(new Paragraph("No se registraron detalles de la compra."));
            }

            // Cerrar el documento
            document.close();
            System.out.println("PDF generado exitosamente en: " + filePath);
            return true;

        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar el PDF: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int actualizar(Solicitud bean) {
        return solicitudDAO.actualizar(bean);
    }

    @Override
    public void eliminar(int id) {
        solicitudDAO.eliminar(id);
    }

    @Override
    public List<Solicitud> listarTodos() {
        return solicitudDAO.listarTodos();
    }

    @Override
    public List<Solicitud> listarPorCliente(int codigoCliente) {
        return solicitudDAO.listarPorCliente(codigoCliente);
    }
}