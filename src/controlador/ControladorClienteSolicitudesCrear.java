package controlador;

import modelo.ArticuloDAO;
import modelo.EvaluacionDAO;
import modelo.SolicitudDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Articulo;
import modelo.CompraProducto;
import modelo.CompraReclamada;
import modelo.CompraServicio;
import modelo.Evaluacion;
import modelo.ISolicitudDAO;
import modelo.Solicitud;
import vista.VistaCliente;
import vista.VistaClienteSolicitudesCrear;
import vista.VistaClientes;

public class ControladorClienteSolicitudesCrear implements ActionListener, MouseListener {
    // La vista correspondiente a este controlador
    VistaClienteSolicitudesCrear vista;
    // El usuario que ha accedido a través del login
    Cliente cliente;
    
    // DAO
    ArticuloDAO articuloDAO;
    SolicitudDAO solicitudDAO;
    EvaluacionDAO evaluacionDAO;
    
    // Table models
    DefaultTableModel modeloSol;
    DefaultTableModel modeloEva;

    
    DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public ControladorClienteSolicitudesCrear(VistaClienteSolicitudesCrear vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
        // Eventos - Tab Crear Solicitud
        this.vista.rbtnServicio.addActionListener(this);
        this.vista.rbtnProducto.addActionListener(this);
        this.vista.btnCrearSolicitud.addActionListener(this);
        this.vista.btnLimpiarSolicitud.addActionListener(this);
        this.vista.rbtnReclamo.addActionListener(this);
        // Eventos - Tab Ver Mis Solicitudes
        this.vista.rbtnQueja.addActionListener(this);
        cargarListaServicios();
    }

    // Eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        // EVENTOS - TAB CREAR SOLICITUD
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

    @Override
    public void mouseClicked(MouseEvent e) {


    }
    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {    }
    
    // MÉTODOS PROPIOS
    
    // ACTIVACION Y LLENADO DE COMBOBOX
    public void cargarListaServicios() {
        // Mostrar y activar el cbx
        vista.cbxNombreServicio.setVisible(true);
        vista.cbxNombreServicio.setEnabled(true);
        // Esconder y desactivar el cbx alternativo
        vista.cbxNombreProducto.setVisible(false);
        vista.cbxNombreProducto.setEnabled(false);
        
        vista.cbxNombreServicio.setSelectedIndex(0);
    }
    
    public void cargarListaProductos() {
        // Mostrar y activar el cbx
        vista.cbxNombreProducto.setVisible(true);
        vista.cbxNombreProducto.setEnabled(true);
        // Esconder y desactivar el cbx alternativo
        vista.cbxNombreServicio.setVisible(false);
        vista.cbxNombreServicio.setEnabled(false);
        // Actualizar la lista de productos
        vista.cbxNombreProducto.removeAllItems();
        vista.cbxNombreProducto.addItem(new Articulo(-1, "", "Seleccione", 0, 0));
        
        articuloDAO = new ArticuloDAO();
        for (var articulo : articuloDAO.listarTodos()) {
            vista.cbxNombreProducto.addItem((Articulo)articulo);
        }
        vista.cbxNombreProducto.setSelectedIndex(0);
    }
     
    
    public void limpiarFormularioCrearSolicitud() {
        // Limpiar cajas de texto
        vista.txtFechaCompra.setText("");
        vista.txtMontoCompra.setText("0.00");
        vista.txtDireccionCompra.setText("");
        vista.txaDescripcionSolicitud.setText("");        
        // Desmarcar botones
        vista.rbtnQueja.setSelected(false);
        vista.rbtnReclamo.setSelected(false);
        // Reiniciar comboboxes
        vista.cbxCanalCompra.setSelectedIndex(0);
        vista.cbxMotivoSolicitud.setSelectedIndex(0);
        // Marcar "servicio" como categoria del bien y cargar lista por defecto
        vista.rbtnServicio.setSelected(true);
        cargarListaServicios();
    }    
    
    public void limpiarTodo() {
        limpiarFormularioCrearSolicitud();
        
    }
    
    
    
    

    // OPERACIONES DE INSERCION-ACTUALIZACION-DELECION
    public void crearSolicitud() {
        // Verificar que los radios estén seleccionados
        boolean radiosPSCheck = vista.rbtnProducto.isSelected() || vista.rbtnServicio.isSelected();
        boolean radiosQRCheck = vista.rbtnQueja.isSelected() || vista.rbtnReclamo.isSelected();
        // Validar la lectura de la fecha de compra
        LocalDate fechaCompra = leerFechaCompra();
        boolean txtFechaCheck = fechaCompra != null;
        // Validar la lectura del monto reclamado
        double monto = leerMontoReclamado();
        boolean txtMontoCheck = monto != -1;
        // Verificar la seleccion de los combobox. No deben estar en el primer item (Seleccione)
        boolean cbxCanalCheck = vista.cbxCanalCompra.getSelectedIndex() != 0;
        boolean cbxServicioCheck = vista.rbtnServicio.isSelected() && vista.cbxNombreServicio.getSelectedIndex() != 0;
        boolean cbxProductoCheck = vista.rbtnProducto.isSelected() && vista.cbxNombreProducto.getSelectedIndex() != 0;
        boolean cbxMotivoCheck = vista.cbxMotivoSolicitud.getSelectedIndex() != 0;
        
        // Evaluar
        if (txtFechaCheck == false) {
            JOptionPane.showMessageDialog(vista, "La Fecha del incidente ingresada no es válida. \nIntente una entrada similar a esta: "+LocalDate.now().format(fmtFecha));
        }
        else if (cbxCanalCheck == false) {
            JOptionPane.showMessageDialog(vista,"Debe seleccionar un Canal de compra.");
        }
        else if (txtMontoCheck == false) {
            JOptionPane.showMessageDialog(vista, "El monto reclamado ingresado no es válido. \nDebe ingresar un valor numérico. No utilice comas (,)");
        }
        else if (radiosPSCheck == false) {
            JOptionPane.showMessageDialog(vista,"Debe seleccionar una Categoría del Bien: \n * Producto \n * Servicio.");
        }
        else if ((cbxServicioCheck || cbxProductoCheck)  == false) {
            JOptionPane.showMessageDialog(vista,"Debe escribir o seleccionar un Producto / Servicio.");
        }
        else if (radiosQRCheck == false) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Tipo de Solicitud:\n * Queja \n * Reclamo.");
        }
        else if (cbxMotivoCheck == false) {
            JOptionPane.showMessageDialog(vista,"Debe seleccionar una opción para el Motivo de su solicitud.");
        }
        else {
            // Toda está validado. Leer atributos
            String canal = vista.cbxCanalCompra.getSelectedItem().toString();
            String direccion = vista.txtDireccionCompra.getText();
            String tipoSolicitud = (vista.rbtnQueja.isSelected()) ? "QUEJA" : "RECLAMO";
            String categoriaMotivo = vista.cbxMotivoSolicitud.getSelectedItem().toString();
            String descripcion = vista.txaDescripcionSolicitud.getText();
            
            // Formar objeto de la clase CompraReclamada (componente de Solicitud)
            CompraReclamada compra;
            if (vista.rbtnServicio.isSelected()) {
                compra = new CompraServicio();
                compra.setFecha(fechaCompra);
                compra.setCanalCompra(canal);
                compra.setDireccion(direccion);
                compra.setMontoReclamado(monto);
                String nombreServicio = vista.cbxNombreServicio.getSelectedItem().toString();
                ((CompraServicio)compra).setNombreServicio(nombreServicio);
            } else {
                compra = new CompraProducto();
                compra.setFecha(fechaCompra);
                compra.setCanalCompra(canal);
                compra.setDireccion(direccion);
                compra.setMontoReclamado(monto);
                Articulo articulo = (Articulo) vista.cbxNombreProducto.getSelectedItem();
                ((CompraProducto)compra).setArticulo(articulo);
            }
            
            // Nota: No es necesario formar los otros componentes de Solicitud
            // porque al momento de crearse toman un valor por defecto
            
            // Formar objeto de la clase Solicitud
            Solicitud solicitud = new Solicitud();
            solicitud.setTipoSolicitud(tipoSolicitud);
            solicitud.setCategoriaMotivo(categoriaMotivo);
            solicitud.setDescripcion(descripcion);
            solicitud.setFechaIngresoActual();
            solicitud.setEstadoActual(Solicitud.EN_ESPERA);
            solicitud.setCompra(compra);
            
            
            
            // Insertar en la BD y generar pdf con DECORATOR

            ISolicitudDAO solicitudDAO = new SolicitudDAOConPDFDecorator(new SolicitudDAO());
            int res = solicitudDAO.agregar(solicitud, cliente.getCodigoCliente());
            switch (res) {
                case 1 ->
                    JOptionPane.showMessageDialog(vista, "La solicitud ha sido registrada con éxito.");
                default ->
                    JOptionPane.showMessageDialog(vista, "Error: No se pudo registrar la solicitud. ERROR: "+res);
            }                                
            switch (res) {
                case 1 -> JOptionPane.showMessageDialog(vista, "La solicitud ha sido registrada con éxito y se ha generado un PDF.");
                default -> JOptionPane.showMessageDialog(vista, "Error: No se pudo registrar la solicitud. ERROR: " + res);
            }
            
        }        
    }
    
    
    // LECTURA DE DATOS Y VALIDACIONES
    public LocalDate leerFechaCompra() {
        try {
            String fecha = vista.txtFechaCompra.getText();
            return LocalDate.parse(fecha, fmtFecha);
        } catch (Exception e) {
            return null;
        }
    }
    
    public double leerMontoReclamado() {
        try {
            String monto = vista.txtMontoCompra.getText();
            return Double.parseDouble(monto);
        } catch (Exception e) {
            return -1;
        }
    }

    
    
}
