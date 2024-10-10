package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Solicitud implements EstadoVariante{
    // Atributos
    private int idSolicitud;    // PK
    private String tipoSolicitud, categoriaMotivo, descripcion;
    private LocalDate fechaIngreso;
    private int estadoActual;
    // Relaciones
    private CompraReclamada compra;                  // Composicion
    private Departamento departamentoEvaluador;      // Asociacion
    public ArrayList<Evaluacion> listaEvaluaciones; // Agregacion
    
    // Constructor    
    public Solicitud() {    }
    public Solicitud(int idSolicitud, String tipoSolicitud, String categoriaMotivo, String descripcion, LocalDate fechaIngreso, int estadoActual, CompraReclamada compra, Departamento departamentoEvaluador) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.categoriaMotivo = categoriaMotivo;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estadoActual = estadoActual;
        this.compra = compra;
        this.departamentoEvaluador = departamentoEvaluador;
    }    
    
    // Encapsulamiento
    public int getIdSolicitud() {        return idSolicitud;    }
    public void setIdSolicitud(int idSolicitud) {        this.idSolicitud = idSolicitud;    }
    public String getTipoSolicitud() {        return tipoSolicitud;    }
    public void setTipoSolicitud(String tipoSolicitud) {        this.tipoSolicitud = tipoSolicitud;    }
    public String getCategoriaMotivo() {        return categoriaMotivo;    }
    public void setCategoriaMotivo(String categoriaMotivo) {        this.categoriaMotivo = categoriaMotivo;    }
    public String getDescripcion() {        return descripcion;    }
    public void setDescripcion(String descripcion) {        this.descripcion = descripcion;    }
    public LocalDate getFechaIngreso() {        return fechaIngreso;    }
    public void setFechaIngreso(LocalDate fechaIngreso) {        this.fechaIngreso = fechaIngreso;    }
    public int getEstadoActual() {        return estadoActual;    }
    public void setEstadoActual(int estadoActual) {        this.estadoActual = estadoActual;    }
    public CompraReclamada getCompra() {        return compra;    }
    public void setCompra(CompraReclamada compra) {        this.compra = compra;    }
    public Departamento getDepartamentoEvaluador() {        return departamentoEvaluador;    }
    public void setDepartamentoEvaluador(Departamento departamentoEvaluador) {        this.departamentoEvaluador = departamentoEvaluador;    }    
    
    // Implementación de método abstracto
    @Override
    public String mostrarEstado() {
        if (estadoActual == EN_ESPERA) return "En Espera";
        if (estadoActual == EN_TRAMITE) return "En Trámite";
        if (estadoActual == FINALIZADO) return "Finalizado";
        return null;
    }
    
    // Metodos propios
    public void obtenerListaEvaluaciones() {
        EvaluacionDAO dao = new EvaluacionDAO();
        listaEvaluaciones = new ArrayList<>(dao.listarPorSolicitud(idSolicitud));
    }
    
    public void setFechaIngresoActual() {
        this.fechaIngreso = LocalDate.now();
    }
    
    public LocalDate fechaCierre() {
        if (estadoActual == FINALIZADO) {
            for (Evaluacion ev : listaEvaluaciones) {
                if (ev.getEstado() == FINALIZADO) {
                    return ev.getFechaHora().toLocalDate();
                }
            }
        }
        return null;
    }
    
    public long tiempoEspera() {
        if (fechaCierre() == null) return -1;
        return fechaIngreso.until(fechaCierre(), ChronoUnit.DAYS);
    }
    
    public String mostrarCliente() {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscarPorSolicitud(idSolicitud);
        // Obtener nombre y apellido
        return cliente.getNombres() + " " + cliente.getApellidos();
    }
    
    public String mostrarDepartamentoEvaluador() {
        // Obtener nombre
        return departamentoEvaluador.getNombre();
    }
    
    public String[] mostrarRegistroTabla() {        
        // Formato de fecha-hora
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String[] registro = {
            String.format("%04d", idSolicitud),
            tipoSolicitud,
            mostrarCliente(),
            fechaIngreso.format(fmtFecha), 
            mostrarEstado(), 
            mostrarDepartamentoEvaluador()
        };
        return registro;
    }
    
    public String mostrarDetalleSolicitud() {
        // Formato de fecha-hora
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Detalles de la solicitud
        return "SOLICITUD " + idSolicitud +
                "\nTipo de solicitud: " + tipoSolicitud +
                "\tEstado actual: " + mostrarEstado() +
                "\nDepartamento a cargo de la evaluación actual: " + mostrarDepartamentoEvaluador() +
                "\nFecha de ingreso de la solicitud: " + fechaIngreso.format(fmt) +
                "\nCliente: " + mostrarCliente() +
                '\n' + compra.mostrarDetallesCompra() +
                "\n\nMotivo de la solicitud: " + categoriaMotivo +
                '\n' + descripcion;       
    }
    
    public String mostrarReporteSolicitud() {
        // Formato de fecha-hora
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "SOLICITUD " + idSolicitud +
                "\nTipo de solicitud: " + tipoSolicitud +
                "\tEstado: " + mostrarEstado() +
                "\tCliente: " + mostrarCliente() +
                "\nDepartamento a cargo de la evaluación actual: " + mostrarDepartamentoEvaluador() +
                "\nMotivo de la solicitud: " + categoriaMotivo +
                '\n' + descripcion +                
                "\nFecha Ingreso: " + fechaIngreso.format(fmt) +
                "\tFecha Cierre: " + (fechaCierre() == null ? "-" : fechaCierre().format(fmt)) +
                "\nTiempo de Espera: " + (tiempoEspera() == -1 ? "-" : (tiempoEspera() + " días") );
    }
    
}
