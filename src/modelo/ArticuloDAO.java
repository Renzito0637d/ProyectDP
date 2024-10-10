package modelo;

import modelo.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelo.Articulo;

public class ArticuloDAO {
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Create
    public int agregar(Articulo bean) {
        String sql = "{CALL agregar_articulo(?,?,?,?)}";
    
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de procedimiento almacenado
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getCategoria());
            ps.setDouble(3, bean.getPrecio());
            ps.setInt(4, bean.getStock());
        
            // Ejecuta el procedimiento almacenado
            return ps.executeUpdate(); // 1 si fue exitoso
        }
        catch (SQLException e) {            
            return -1;
        }        
    }

    
    // Update
    public int actualizar(Articulo bean) {
        String sql = "{CALL actualizar_articulo(?, ?, ?, ?, ?)}";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
             ps = con.prepareStatement(sql);
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getCategoria());
            ps.setDouble(3, bean.getPrecio());
            ps.setInt(4, bean.getStock());
            ps.setInt(5, bean.getCodigoProducto());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "{CALL eliminar_articulo(?)}";
        
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
    public List listarTodos() {
        List<Articulo> lista = new ArrayList<>();
        String sql = "{CALL ListarArticulos()}";
        
        try {
            // Conecta y ejecuta consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Articulo bean = new Articulo();
                bean.setCodigoProducto(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setCategoria(rs.getString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStock(rs.getInt(5));
                // Agrega el objeto formado a la lista
                lista.add(bean);
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // Ordenar la lista alfabeticamente por nombre
        Comparator<Articulo> comparador = (a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre());
        lista.sort(comparador);
        return lista;
    }
    
    public Articulo buscarPorCodigo(int id) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Articulo bean = new Articulo();
        bean.setCodigoProducto(-1);
        String sql = "{CALL BuscarArticuloPorCodigo(?)}";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setCodigoProducto(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setCategoria(rs.getString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStock(rs.getInt(5));
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
