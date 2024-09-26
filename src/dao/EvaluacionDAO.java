package dao;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import modelo.Empleado;
import modelo.Evaluacion;

public class EvaluacionDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    EmpleadoDAO empleadoDAO;
    DateTimeFormatter fmtSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Create
    public int agregar(Evaluacion bean, int idSolicitud) {
        String sql = "INSERT INTO evaluacion (fecha_hora, estado, descripcion, id_solicitud, codigo_empleado) VALUES (?,?,?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getFechaHora().format(fmtSQL));
            ps.setInt(2, bean.getEstado());
            ps.setString(3, bean.getDescripcion());
            ps.setInt(4, idSolicitud);
            // Asignar empleado
            Empleado emp = bean.getEmpleado();
            if (emp == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, emp.getCodigoEmpleado());
            }            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return e.getErrorCode();
        }        
    }
    
    // Update
    public int actualizar(Evaluacion bean) {
        String sql = "UPDATE evaluacion SET fecha_hora = ?, estado = ?, descripcion = ? WHERE numero_evaluacion = ?";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getFechaHora().format(fmtSQL));
            ps.setInt(2, bean.getEstado());
            ps.setString(3, bean.getDescripcion());
            ps.setInt(4, bean.getNumeroEvaluacion());
            
            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM evaluacion WHERE numero_evaluacion = " + id;
        
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
    public List listarPorSolicitud(int idSolicitud) {
        List<Evaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluacion WHERE id_solicitud = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            // Ejecuta la consulta
            empleadoDAO = new EmpleadoDAO();
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Evaluacion bean = new Evaluacion();
                bean.setNumeroEvaluacion(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setEstado(rs.getInt(3));
                bean.setDescripcion(rs.getString(4));
                // Buscar empleado
                int codigoEmpleado = rs.getInt(6);
                if (codigoEmpleado == 0) { // ResultSet devuelve 0 si el valor es null en una columna int
                    bean.setEmpleado(null);
                } else {
                    bean.setEmpleado(empleadoDAO.buscarPorCodigo(codigoEmpleado));
                }
                
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
    
    public List listarPorEmpleado(int codigoEmpleado) {
        List<Evaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM evaluacion WHERE codigo_empleado = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoEmpleado);
            // Ejecuta la consulta
            empleadoDAO = new EmpleadoDAO();
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Evaluacion bean = new Evaluacion();
                bean.setNumeroEvaluacion(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setEstado(rs.getInt(3));
                bean.setDescripcion(rs.getString(4));
                // Buscar empleado
                codigoEmpleado = rs.getInt(6);
                if (codigoEmpleado == 0) { // ResultSet devuelve 0 si el valor es null en una columna int
                    bean.setEmpleado(null);
                } else {
                    bean.setEmpleado(empleadoDAO.buscarPorCodigo(codigoEmpleado));
                }
                
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
    
    public Evaluacion buscarPorId(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Evaluacion bean = new Evaluacion();
        bean.setNumeroEvaluacion(-1);
        String sql = "SELECT * FROM evaluacion WHERE numero_evaluacion = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            empleadoDAO = new EmpleadoDAO();
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setNumeroEvaluacion(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setEstado(rs.getInt(3));
                bean.setDescripcion(rs.getString(4));
                // Buscar empleado
                int codigoEmpleado = rs.getInt(6);
                if (codigoEmpleado == 0) { // ResultSet devuelve 0 si el valor es null en una columna int
                    bean.setEmpleado(null);
                } else {
                    bean.setEmpleado(empleadoDAO.buscarPorCodigo(codigoEmpleado));
                }
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
    public Evaluacion buscarUltimaEvaluacionDeSolicitud(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Evaluacion bean = new Evaluacion();
        bean.setNumeroEvaluacion(-1);
        String sql = "SELECT * FROM evaluacion WHERE id_solicitud = ? ORDER BY fecha_hora DESC LIMIT 1";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            empleadoDAO = new EmpleadoDAO();
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setNumeroEvaluacion(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setEstado(rs.getInt(3));
                bean.setDescripcion(rs.getString(4));
                // Buscar empleado
                int codigoEmpleado = rs.getInt(6);
                if (codigoEmpleado == 0) { // ResultSet devuelve 0 si el valor es null en una columna int
                    bean.setEmpleado(null);
                } else {
                    bean.setEmpleado(empleadoDAO.buscarPorCodigo(codigoEmpleado));
                }
                con.close();
                return bean;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }

}
