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
import modelo.Comentario;

public class ComentarioDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    ClienteDAO clienteDAO;
    DateTimeFormatter fmtSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Create
    public int agregar(Comentario bean, int numeroEvaluacion) {
        String sql = "INSERT INTO comentario (fecha_hora, contenido, numero_evaluacion, codigo_cliente) VALUES (?,?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getFechaHora().format(fmtSQL));
            ps.setString(2, bean.getContenido());
            ps.setInt(3, numeroEvaluacion);
            ps.setInt(4, bean.getCliente().getCodigoCliente());
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return e.getErrorCode();
        }        
    }
    
    // Update
    public int actualizar(Comentario bean) {
        String sql = "UPDATE comentario SET fecha_hora = ?, contenido = ? WHERE id_comentario = ?";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getFechaHora().format(fmtSQL));
            ps.setString(2, bean.getContenido());
            ps.setInt(3, bean.getIdComentario());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM comentario WHERE id_comentario = " + id;
        
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
    public List listarPorCliente(int codigoCliente) {
        List<Comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario WHERE codigo_cliente = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCliente);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            clienteDAO = new ClienteDAO();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Comentario bean = new Comentario();
                bean.setIdComentario(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setContenido(rs.getString(3));
                // Buscar cliente
                bean.setCliente(clienteDAO.buscarPorCodigo(rs.getInt(5)));
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
    
    public List listarPorEvaluacion(int numeroEvaluacion) {
        List<Comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario WHERE numero_evaluacion = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numeroEvaluacion);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            clienteDAO = new ClienteDAO();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Comentario bean = new Comentario();
                bean.setIdComentario(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setContenido(rs.getString(3));
                // Buscar cliente
                bean.setCliente(clienteDAO.buscarPorCodigo(rs.getInt(5)));
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
    
    public List listarPorSolicitud(int idSolicitud) {
        List<Comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario c "
                + "INNER JOIN evaluacion ev ON c.numero_evaluacion = ev.numero_evaluacion "
                + "WHERE ev.id_solicitud = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            clienteDAO = new ClienteDAO();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Comentario bean = new Comentario();
                bean.setIdComentario(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setContenido(rs.getString(3));
                // Buscar cliente
                bean.setCliente(clienteDAO.buscarPorCodigo(rs.getInt(5)));
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
    
    public Comentario buscarPorId(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Comentario bean = new Comentario();
        bean.setIdComentario(-1);
        String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            clienteDAO = new ClienteDAO();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setIdComentario(rs.getInt(1));
                bean.setFechaHora(LocalDateTime.parse(rs.getString(2), fmtSQL));
                bean.setContenido(rs.getString(3));
                // Buscar cliente                
                bean.setCliente(clienteDAO.buscarPorCodigo(rs.getInt(5)));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
