package controlador;

import modelo.ArticuloDAO;
import modelo.SolicitudDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Articulo;
import modelo.CompraProducto;
import modelo.CompraReclamada;
import modelo.CompraServicio;
import modelo.ISolicitudDAO;
import modelo.Solicitud;
import vista.VistaClienteSolicitudesCrear;

public class ControladorClienteSolicitudesCrear implements ActionListener {
    // La vista correspondiente a este controlador
    private final VistaClienteSolicitudesCrear vista;
    // El usuario que ha accedido a través del login
    private final Cliente cliente;
    // DAO
    private ArticuloDAO articuloDAO;

    public ControladorClienteSolicitudesCrear(VistaClienteSolicitudesCrear vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;

        vista.rbtnServicio.addActionListener(this);
        vista.rbtnProducto.addActionListener(this);
        vista.btnCrearSolicitud.addActionListener(this);
        vista.btnLimpiarSolicitud.addActionListener(this);
        vista.rbtnReclamo.addActionListener(this);
        vista.rbtnQueja.addActionListener(this);
        cargarListaServicios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.rbtnServicio) {
            cargarListaServicios();
        }
        if (e.getSource() == vista.rbtnProducto) {
            cargarListaProductos();
        }
        if (e.getSource() == vista.btnLimpiarSolicitud) {
            limpiarFormularioCrearSolicitud();
        }
        if (e.getSource() == vista.btnCrearSolicitud) {
            crearSolicitud();
        }
    }

    // Métodos principales
    private void configurarComboBox(javax.swing.JComboBox<?> visible, javax.swing.JComboBox<?> oculto, boolean habilitar) {
        visible.setVisible(habilitar);
        visible.setEnabled(habilitar);
        oculto.setVisible(!habilitar);
        oculto.setEnabled(!habilitar);
    }
    
    private void cargarListaServicios() {
        configurarComboBox(vista.cbxNombreServicio, vista.cbxNombreProducto, true);
    }

    private void cargarListaProductos() {
        configurarComboBox(vista.cbxNombreProducto, vista.cbxNombreServicio, true);

        articuloDAO = new ArticuloDAO();
        vista.cbxNombreProducto.removeAllItems();
        vista.cbxNombreProducto.addItem(new Articulo(-1, "", "Seleccione", 0, 0));

        articuloDAO.listarTodos().forEach(articulo -> vista.cbxNombreProducto.addItem((Articulo) articulo));
        vista.cbxNombreProducto.setSelectedIndex(0);
    }

    private void limpiarFormularioCrearSolicitud() {
        vista.datePickerFechaCompra.clear();
        vista.txtMontoCompra.setText("0.00");
        vista.txtDireccionCompra.setText("");
        vista.txaDescripcionSolicitud.setText("");
        vista.rbtnQueja.setSelected(false);
        vista.rbtnReclamo.setSelected(false);
        vista.cbxCanalCompra.setSelectedIndex(0);
        vista.cbxMotivoSolicitud.setSelectedIndex(0);
        vista.rbtnServicio.setSelected(true);
        cargarListaServicios();
    }

    private void crearSolicitud() {
    System.out.println("Iniciando creación de solicitud...");
    if (!validarFormulario()) {
        System.out.println("Formulario inválido. Cancelando solicitud.");
        return;
    }

    CompraReclamada compra = vista.rbtnServicio.isSelected()
            ? crearCompraServicio()
            : crearCompraProducto();

    Solicitud solicitud = new Solicitud();
    solicitud.setTipoSolicitud(vista.rbtnQueja.isSelected() ? "QUEJA" : "RECLAMO");
    solicitud.setCategoriaMotivo(vista.cbxMotivoSolicitud.getSelectedItem().toString());
    solicitud.setDescripcion(vista.txaDescripcionSolicitud.getText());
    solicitud.setFechaIngresoActual();
    solicitud.setEstadoActual(Solicitud.EN_ESPERA);
    solicitud.setCompra(compra);

    ISolicitudDAO solicitudDAO = new SolicitudDAOConPDFDecorator(new SolicitudDAO());
    int res = solicitudDAO.agregar(solicitud, cliente.getCodigoCliente());
    if (res != 1) {
        mostrarMensaje("Solicitud registrada exitosamente y PDF generado.");
    } else {
        mostrarMensaje("Error al registrar la solicitud. Código: " + res);
    }
    limpiarFormularioCrearSolicitud();
}


    // Métodos de validacion
    private boolean validarFormulario() {
        if (vista.datePickerFechaCompra == null) {
            mostrarMensaje("Debe seleccionar una fecha de compra válida.");
            return false;
        }
        if (!validarSeleccion(vista.cbxCanalCompra, "Debe seleccionar un Canal de compra.")) return false;
        if (leerMontoReclamado() == -1) {
            mostrarMensaje("Monto inválido. Use un formato numérico sin comas (,).");
            return false;
        }
        if (!vista.rbtnProducto.isSelected() && !vista.rbtnServicio.isSelected()) {
            mostrarMensaje("Debe seleccionar Producto o Servicio.");
            return false;
        }
        if (!validarSeleccion(vista.cbxMotivoSolicitud, "Debe seleccionar un Motivo de solicitud.")) return false;
        return true;
    }
    private boolean validarSeleccion(javax.swing.JComboBox<?> comboBox, String mensaje) {
        if (comboBox.getSelectedIndex() == 0) {
            mostrarMensaje(mensaje);            
            return false;
        }
        return true;
    }
    private CompraServicio crearCompraServicio() {
        CompraServicio compra = new CompraServicio();
        compra.setFecha(vista.datePickerFechaCompra.getDate());
        compra.setCanalCompra(vista.cbxCanalCompra.getSelectedItem().toString());
        compra.setDireccion(vista.txtDireccionCompra.getText());
        compra.setMontoReclamado(leerMontoReclamado());
        compra.setNombreServicio(vista.cbxNombreServicio.getSelectedItem().toString());
        return compra;
    }

    private CompraProducto crearCompraProducto() {
        CompraProducto compra = new CompraProducto();
        compra.setFecha(vista.datePickerFechaCompra.getDate());
        compra.setCanalCompra(vista.cbxCanalCompra.getSelectedItem().toString());
        compra.setDireccion(vista.txtDireccionCompra.getText());
        compra.setMontoReclamado(leerMontoReclamado());
        compra.setArticulo((Articulo) vista.cbxNombreProducto.getSelectedItem());
        return compra;
    }

    private double leerMontoReclamado() {
        try {
            return Double.parseDouble(vista.txtMontoCompra.getText());
        } catch (Exception e) {
            return -1;
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }

    
}
