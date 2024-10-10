
package modelo;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Create
    public int agregar(Empleado bean, int codigoDepartamento) {
        String sql = "{CALL agregarEmpleado(?, ?, ?, ?, ?, ?, ?)}";
        
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
            ps.setInt(7, codigoDepartamento);
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {            
            return -1;
        }        
    }
    
    // Update
    public int actualizar(Empleado bean, int codigoDepartamento) {
        String sql = "{CALL actualizarEmpleado(?, ?, ?, ?, ?, ?, ?, ?)}";
        
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
            ps.setInt(7, codigoDepartamento);
            ps.setInt(8, bean.getCodigoEmpleado());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public int eliminar(int id) {
        String sql = "{CALL eliminarEmpleado(?)}";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Forma la sentencia delete con la PK brindada
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
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
        List<Empleado> lista = new ArrayList<>();
        String sql = "{CALL listarEmpleados()}";
        
        try {
            // Conecta y ejecuta consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Empleado bean = new Empleado();
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
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
        List<Empleado> lista = new ArrayList<>();
        String sql = "{CALL listarEmpleadosPorUsuario(?)}";
        
        try {
            // Conecta y prepara consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario+'%');
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Empleado bean = new Empleado();
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
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
        List<Empleado> lista = new ArrayList<>();
        String sql = "{CALL listarEmpleadosPorDepartamento(?)}";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoDepartamento);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Empleado bean = new Empleado();
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
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
    
    public List listarPorUsuarioyDepartamento(String usuario, int codigoDepartamento) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "{CALL listarEmpleadosPorUsuarioYDepartamento(?, ?)}";
        
        try {
            // Conecta y prepara consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario+'%');
            ps.setInt(2, codigoDepartamento);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Empleado bean = new Empleado();
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
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
    
    public Empleado buscarPorCredenciales(String usuario, String clave) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Empleado bean = new Empleado();
        bean.setCodigoEmpleado(-1);
        String sql = "{CALL buscarEmpleadoPorCredenciales(?, ?)}";
        
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
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
    public Empleado buscarPorCodigo(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Empleado bean = new Empleado();
        bean.setCodigoEmpleado(-1);
        String sql = "{CALL buscarEmpleadoPorCodigo(?)}";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoEmpleado(rs.getInt(1));
                bean.setNombres(rs.getString(2));
                bean.setApellidos(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setTelefono(rs.getString(5));
                bean.setUsuario(rs.getString(6));
                bean.setClave(rs.getString(7));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
