package dao;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Create
    public int agregar(Cliente bean) {
        String sql = "INSERT INTO cliente (nombres, apellidos, email, telefono, usuario, clave, direccion) VALUES (?,?,?,?,?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getNombres());
            ps.setString(2, bean.getApellidos());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getTelefono());
            ps.setString(5, bean.getUsuario());
            ps.setString(6, bean.getClave());
            ps.setString(7, bean.getDireccion());            
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            // El codigo de error 1062 indica duplicidad de llave única (usuario)
            return e.getErrorCode();
        }        
    }
    
    // Update
    public int actualizar(Cliente bean) {
        String sql = "UPDATE cliente SET nombres = ?, apellidos = ?, email = ?, telefono = ?, usuario = ?, clave = ?, direccion = ? WHERE codigo_cliente = ?";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
            ps.setString(1, bean.getNombres());
            ps.setString(2, bean.getApellidos());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getTelefono());
            ps.setString(5, bean.getUsuario());
            ps.setString(6, bean.getClave());
            ps.setString(7, bean.getDireccion());            
            ps.setInt(8, bean.getCodigoCliente());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public int eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE codigo_cliente = " + id;
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Forma la sentencia delete con la PK brindada
            ps = con.prepareStatement(sql);
            // Ejecuta el delete
            return ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            // El codigo de error 1451 indica restricción de FK
            return e.getErrorCode();
        }
    }
    
    // Reads
    public List listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        
        try {
            // Conecta y ejecuta consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Cliente bean = new Cliente();
                bean.setCodigoCliente(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
                bean.setDireccion(rs.getString(8));
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
    
    public List listarPorUsuario(String usuario) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE usuario LIKE ?";
        
        try {
            // Conecta y prepara consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario+'%');
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Cliente bean = new Cliente();
                bean.setCodigoCliente(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
                bean.setDireccion(rs.getString(8));
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
    
    public Cliente buscarPorCredenciales(String usuario, String clave) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Cliente bean = new Cliente();
        bean.setCodigoCliente(-1);
        String sql = "SELECT * FROM cliente WHERE usuario = ? AND clave = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoCliente(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
                bean.setDireccion(rs.getString(8));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
    public Cliente buscarPorCodigo(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Cliente bean = new Cliente();
        bean.setCodigoCliente(-1);
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoCliente(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
                bean.setDireccion(rs.getString(8));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
    public Cliente buscarPorSolicitud(int idSolicitud) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Cliente bean = new Cliente();
        bean.setCodigoCliente(-1);
        String sql = "SELECT * FROM cliente c "
                + "INNER JOIN solicitud s ON c.codigo_cliente = s.codigo_cliente "
                + "WHERE s.id_solicitud = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoCliente(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
                bean.setDireccion(rs.getString(8));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
