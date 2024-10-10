package modelo;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Types;
import modelo.Articulo;
import modelo.CompraProducto;
import modelo.CompraReclamada;
import modelo.CompraServicio;

public class CompraDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    ArticuloDAO articuloDAO;
    DateTimeFormatter fmtSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Create
    public int agregar(CompraReclamada bean, int idSolicitud) {
        String sql = "{CALL agregar_compra_reclamada(?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getCanalCompra());
            ps.setString(2, bean.getFecha().format(fmtSQL));
            ps.setString(3, bean.getDireccion());
            ps.setDouble(4, bean.getMontoReclamado());
            ps.setInt(7, idSolicitud);
            // Si es de clase CompraServicio
            if (bean instanceof CompraServicio compra) {
                ps.setString(5, compra.getNombreServicio());
                ps.setNull(6, Types.INTEGER); // codigo_producto queda en null
            }
            // Si es de clase CompraProducto
            if (bean instanceof CompraProducto compra) {
                ps.setString(5, null); // nombre_servicio queda en null
                ps.setInt(6, compra.getArticulo().getCodigoProducto());
            }
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            // El codigo de error 1062 indica duplicidad de llave única (id_solicitud)
            return e.getErrorCode();
        }        
    }
    
    // Update
    public int actualizar(CompraReclamada bean) {
        String sql = "{CALL actualizar_compra_reclamada(?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
            ps.setString(1, bean.getCanalCompra());
            ps.setString(2, bean.getFecha().format(fmtSQL));
            ps.setString(3, bean.getDireccion());
            ps.setDouble(4, bean.getMontoReclamado());
            ps.setInt(7, bean.getNumeroCompra());
            // Si es de clase CompraServicio
            if (bean instanceof CompraServicio compra) {
                ps.setString(5, compra.getNombreServicio());
                ps.setNull(6, Types.INTEGER); // codigo_producto queda en null
            }
            // Si es de clase CompraProducto
            if (bean instanceof CompraProducto compra) {
                ps.setString(5, null); // nombre_servicio queda en null
                ps.setInt(6, compra.getArticulo().getCodigoProducto());
            }
            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "{CALL eliminar_compra_reclamada(?)}";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Forma la sentencia delete con la PK brindada
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta el delete
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Reads 
    public CompraReclamada buscarPorId(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve null
        CompraReclamada bean;
        String sql = "{CALL buscarCompraReclamadaID(?)}";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                // Determinar la clase de la CompraReclamada
                if (rs.getString(6) != null) { // 6: nombre_servicio
                    bean = new CompraServicio();
                    ((CompraServicio)bean).setNombreServicio(rs.getString(6));
                } else { 
                    bean = new CompraProducto();
                    articuloDAO = new ArticuloDAO();
                    Articulo articulo = articuloDAO.buscarPorCodigo(rs.getInt(7));
                    ((CompraProducto)bean).setArticulo(articulo);
                }                
                bean.setNumeroCompra(rs.getInt(1));
                bean.setCanalCompra(rs.getString(2));
                bean.setFecha(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setDireccion(rs.getString(4));
                bean.setMontoReclamado(rs.getDouble(5));
                
                con.close();
                return bean;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return null;
    }
    
    public CompraReclamada buscarPorSolicitud(int idSolicitud) {
        // Si no se encuentra ninguna coincidencia, devuelve null
        CompraReclamada bean;
        String sql = "{CALL buscarCompraReclamadaPorSolicitud(?)}";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                // Determinar la clase de la CompraReclamada
                if (rs.getString(6) != null) { // 6: nombre_servicio
                    bean = new CompraServicio();
                    ((CompraServicio)bean).setNombreServicio(rs.getString(6));
                } else { 
                    bean = new CompraProducto();
                    articuloDAO = new ArticuloDAO();
                    Articulo articulo = articuloDAO.buscarPorCodigo(rs.getInt(7));
                    ((CompraProducto)bean).setArticulo(articulo);
                }                
                bean.setNumeroCompra(rs.getInt(1));
                bean.setCanalCompra(rs.getString(2));
                bean.setFecha(LocalDate.parse(rs.getString(3), fmtSQL));
                bean.setDireccion(rs.getString(4));
                bean.setMontoReclamado(rs.getDouble(5));
                
                con.close();
                return bean;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return null;
    }
}
