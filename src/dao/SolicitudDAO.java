package dao;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Evaluacion;
import modelo.Solicitud;

public class SolicitudDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    CompraDAO compraDAO;
    DepartamentoDAO departamentoDAO;
    EvaluacionDAO evaluacionDAO;
    DateTimeFormatter fmtSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Create
    public int agregar(Solicitud bean, int codigoCliente) {
        String sql = "INSERT INTO solicitud (tipo_solicitud, fecha_ingreso, estado_actual, "
                + "codigo_cliente, codigo_departamento_evaluador) VALUES (?,?,?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Se prepara la sentencia con la capacidad de recuperar la PK creada en la inserción
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys;
            // Convierte el objeto en una sentencia SQL de insert            
            ps.setString(1, bean.getTipoSolicitud());
            ps.setString(2, bean.getFechaIngreso().format(fmtSQL));
            ps.setInt(3, bean.getEstadoActual());
            ps.setInt(4, codigoCliente);
            // Por defecto, todas las solicitudes recién creadas están a cargo 
            // del departamento de Atención al Cliente (codigoDepartamento = 1)
            ps.setInt(5, 1); 
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            int res = ps.executeUpdate(); // 1
            // Recuperar la PK de la fila insertada
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idSolicitud = generatedKeys.getInt(1);
                
                // Insertar fila en la tabla Motivo
                agregarMotivo(bean, idSolicitud);
                
                // Insertar fila en la tabla CompraReclamada
                compraDAO = new CompraDAO();
                res *= compraDAO.agregar(bean.getCompra(), idSolicitud);
                
                
                // Insertar 1 fila en la tabla Evaluacion
                // Por defecto, la primera evaluacion se crea con la fecha-hora actual, estado en espera,
                // una descripcion predeterminada, y código de empleado null
                Evaluacion eval = new Evaluacion();
                eval.setFechaHoraActual();
                eval.setEstado(Evaluacion.EN_ESPERA);
                eval.setDescripcion("Enviado por el cliente");
                eval.setEmpleado(null);
                evaluacionDAO = new EvaluacionDAO();
                res *= evaluacionDAO.agregar(eval, idSolicitud);
            }
            return res; // 1
        }
        catch (SQLException e) {
            return e.getErrorCode();
        }
    }
    
    public int agregarMotivo(Solicitud bean, int idSolicitud) {
        String sqlM = "INSERT INTO motivo (categoria, descripcion, id_solicitud) VALUES (?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert            
            ps = con.prepareStatement(sqlM);
            ps.setString(1, bean.getCategoriaMotivo());
            ps.setString(2, bean.getDescripcion());
            ps.setInt(3, idSolicitud);
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            // El codigo de error 1062 indica duplicidad de llave única (id_solicitud)
            return e.getErrorCode();
        }        
    }
    
    // Update
    public int actualizar(Solicitud bean) {
        String sql = "UPDATE solicitud SET estado_actual = ?, codigo_departamento_evaluador = ? "
                + "WHERE id_solicitud = ?";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
            ps.setInt(1, bean.getEstadoActual());
            ps.setInt(2, bean.getDepartamentoEvaluador().getCodigoDepartamento());
            ps.setInt(3, bean.getIdSolicitud());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM solicitud WHERE id_solicitud = " + id;
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Forma la sentencia delete con la PK brindada
            ps = con.prepareStatement(sql);
            // Ejecuta el delete
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Reads
    public List listarTodos() {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud ";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return lista;
    }
    
    public List listarPorCliente(int codigoCliente) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.codigo_cliente = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorDepartamento(int codigoDepartamento) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.codigo_departamento_evaluador = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDepartamento);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.fecha_ingreso BETWEEN ? AND ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaInicio.format(fmtSQL));
            ps.setString(2, fechaFin.format(fmtSQL));
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorEstadoyFecha(int estado, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.estado_actual = ? AND s.fecha_ingreso BETWEEN ? AND ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setString(2, fechaInicio.format(fmtSQL));
            ps.setString(3, fechaFin.format(fmtSQL));
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorEstadoyCliente(int estado, int codigoCliente) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.estado_actual = ? AND s.codigo_cliente = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, codigoCliente);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorEstadoyDepartamento(int estado, int codigoDepartamento) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.estado_actual = ? AND s.codigo_departamento_evaluador = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, codigoDepartamento);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public List listarPorEncuestayCliente(int estado, int codigoCliente) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud s \n"
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud \n"
                + "INNER JOIN encuesta e ON s.id_solicitud = e.id_solicitud\n"
                + "WHERE e.estado = ? AND s.codigo_cliente = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, codigoCliente);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { 
                // Convierte el resultado de la consulta en un objeto
                Solicitud bean = new Solicitud();
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(bean.getIdSolicitud()));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return lista;
    }
    
    public Solicitud buscarPorId(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Solicitud bean = new Solicitud();
        bean.setIdSolicitud(-1);
        String sql = "SELECT * FROM solicitud s "
                + "INNER JOIN motivo m ON s.id_solicitud = m.id_solicitud "
                + "WHERE s.id_solicitud = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            compraDAO = new CompraDAO();
            departamentoDAO = new DepartamentoDAO();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setIdSolicitud(rs.getInt(1));
                bean.setTipoSolicitud(rs.getString(2));
                bean.setFechaIngreso(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setEstadoActual(rs.getInt(4));
                bean.setCategoriaMotivo(rs.getString(8));
                bean.setDescripcion(rs.getString(9));                
                // Recuperar CompraReclamada
                bean.setCompra(compraDAO.buscarPorSolicitud(id));
                // Recuperar DepartamentoEvaluador
                int codigoDepartamento = rs.getInt(6);
                bean.setDepartamentoEvaluador(departamentoDAO.buscarPorCodigo(codigoDepartamento));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
}
