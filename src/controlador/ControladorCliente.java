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

public class ControladorCliente implements ActionListener, MouseListener {
    // La vista correspondiente a este controlador
    VistaCliente vista;
    // El controlador desde donde se ha ingresado a esta vista
    ControladorLogin controladorPrevio;
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
    
    public ControladorCliente(VistaCliente vista, ControladorLogin controladorPrevio, Cliente cliente) {
        this.vista = vista;
        this.controladorPrevio = controladorPrevio;
        this.cliente = cliente;
        // Eventos - Tab Crear Solicitud
        this.vista.rbtnServicio.addActionListener(this);
        this.vista.rbtnProducto.addActionListener(this);
        this.vista.btnCrearSolicitud.addActionListener(this);
        this.vista.btnLimpiarSolicitud.addActionListener(this);
        this.vista.btnSalirCrearSolicitud.addActionListener(this);
        // Eventos - Tab Ver Mis Solicitudes
        this.vista.btnActualizarListaSol.addActionListener(this);
        this.vista.tbSolicitudes.addMouseListener(this);
        this.vista.tbEvaluaciones.addMouseListener(this);
    }
    
    public void iniciar() {
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);
        vista.setSize(800, 600);
        limpiarTodo();
        configurarTablas();
        vista.setVisible(true);
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
        if (e.getSource() == vista.btnSalirCrearSolicitud) {
            salir();
        }        
        
        // EVENTOS - TAB VER MIS SOLICITUDES
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
    
    // LIMPIEZA Y ACTUALIZACIÓN DE CONTENIDO
    public void configurarTablas() {
        // Tabla solicitudes
        modeloSol = new DefaultTableModel();
        modeloSol.addColumn("ID");
        modeloSol.addColumn("Tipo de Solicitud");
        modeloSol.addColumn("Cliente");
        modeloSol.addColumn("Fecha Ingreso");
        modeloSol.addColumn("Estado Actual");
        modeloSol.addColumn("Departamento Evaluador");
        vista.tbSolicitudes.setModel(modeloSol);
        vista.tbSolicitudes.setDefaultEditor(Object.class, null);
        
        // Tabla evaluaciones
        modeloEva = new DefaultTableModel();
        modeloEva.addColumn("Número Evaluación");
        modeloEva.addColumn("Fecha");
        modeloEva.addColumn("Hora");
        modeloEva.addColumn("Estado");
        modeloEva.addColumn("Empleado Evaluador");
        vista.tbEvaluaciones.setModel(modeloEva);
        vista.tbEvaluaciones.setDefaultEditor(Object.class, null);
        
    }
    
    public void listarSolicitudes() {
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
    
    public void limpiarDetallesEvaluacion() {
        vista.txaDetalleEvaluacion.setText("");
    }
    public void limpiarTodo() {
        limpiarTabla(vista.tbSolicitudes);
        limpiarTabla(vista.tbEvaluaciones);
        limpiarFormularioCrearSolicitud();
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

    public int leerUltimaEvaluacionDeSolicitudSeleccionada() {
        int fila = vista.tbSolicitudes.getSelectedRow();
        if (fila == -1) {
            // No se ha seleccionado ninguna solicitud
            return -1;
        } else {
            try {
                int idSolicitud = Integer.parseInt(vista.tbSolicitudes.getValueAt(fila, 0).toString());
                evaluacionDAO = new EvaluacionDAO();
                Evaluacion ev = evaluacionDAO.buscarUltimaEvaluacionDeSolicitud(idSolicitud);
                return ev.getNumeroEvaluacion();
            } catch (Exception e) {
                return -1;
            }
        }
    }
    
    // PASAR A OTRA PANTALLA
    public void salir() {
        // Regresar a la ventana Login
        controladorPrevio.iniciar();
        // Cerrar la ventana actual
        vista.dispose();
    }
    

}
