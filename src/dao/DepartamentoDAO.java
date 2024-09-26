
package dao;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Departamento;

public class DepartamentoDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Create
    public int agregar(Departamento bean) {
        String sql = "INSERT INTO departamento (nombre, email, telefono) VALUES (?,?,?)";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getTelefono());
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {            
            return -1;
        }        
    }
    
    // Update
    public int actualizar(Departamento bean) {
        String sql = "UPDATE departamento SET nombre = ?, email = ?, telefono = ? WHERE codigo_departamento = ?";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
             ps = con.prepareStatement(sql);
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getTelefono());
            ps.setInt(4, bean.getCodigoDepartamento());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM departamento WHERE codigo_departamento = " + id;
        
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
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        
        try {
            // Conecta y ejecuta consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Departamento bean = new Departamento();
                bean.setCodigoDepartamento(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setTelefono(rs.getString(4));
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
    
    public Departamento buscarPorEmpleado(int codigoEmpleado) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Departamento bean = new Departamento();
        bean.setCodigoDepartamento(-1);
        String sql = "SELECT d.codigo_departamento, d.nombre, d.email, d.telefono "
                + "FROM departamento d "
                + "INNER JOIN empleado e ON d.codigo_departamento = e.codigo_departamento "
                + "WHERE e.codigo_empleado = ?;";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoEmpleado);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoDepartamento(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setTelefono(rs.getString(4));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
    
    public Departamento buscarPorCodigo(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Departamento bean = new Departamento();
        bean.setCodigoDepartamento(-1);
        String sql = "SELECT * FROM departamento WHERE codigo_departamento = ?";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoDepartamento(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setEmail(rs.getString(3));
                bean.setTelefono(rs.getString(4));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
