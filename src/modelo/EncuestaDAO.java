/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renzo
 */
public class EncuestaDAO {
    
    MiConexion conectar = MiConexion.getInstance();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    DateTimeFormatter fmtSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");    
    
    public int agregar(Encuesta bean, int idSolicitud) {
        String sql = "{ CALL agregarEncuesta(?, ?, ?, ?, ?, ?) }";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Manejar la fecha
            LocalDateTime fecha = bean.getFechaHoraLlenado();
            // Convierte el objeto en una sentencia SQL de insert
            ps = con.prepareStatement(sql);
            ps.setString(1, (fecha == null) ? null :fecha.format(fmtSQL));
            ps.setInt(2, bean.getEstado());
            ps.setInt(3, bean.getRespuesta1());
            ps.setInt(4, bean.getRespuesta2());
            ps.setInt(5, bean.getRespuesta3());
            ps.setInt(6, idSolicitud);
            
            // Ejecuta el insert y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            // El codigo de error 1062 indica duplicidad de llave única (id_solicitud)
            return e.getErrorCode();
        }        
    }

    // Update
    public int actualizar(Encuesta bean) {
        String sql = "{ CALL actualizarEncuesta(?, ?, ?, ?, ?, ?) }";
        
        try {
            // Conectar
            con = conectar.obtenerConexion();
            // Manejar la fecha
            LocalDateTime fecha = bean.getFechaHoraLlenado();
            // Convierte el objeto en una sentencia SQL de update
            ps = con.prepareStatement(sql);            
            ps.setString(1, (fecha == null) ? null :fecha.format(fmtSQL));
            ps.setInt(2, bean.getEstado());
            ps.setInt(3, bean.getRespuesta1());
            ps.setInt(4, bean.getRespuesta2());
            ps.setInt(5, bean.getRespuesta3());       
            ps.setInt(6, bean.getIdEncuesta());

            // Ejecuta el update y devuelve el número de filas alteradas (debería ser 1) 
            return ps.executeUpdate(); // 1
        }
        catch (SQLException e) {
            return -1;
        }
    }
    
    // Delete
    public void eliminar(int id) {
        String sql = "{ CALL eliminarEncuesta(?) }";
        
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
    //Reads
     public List listarTodos() {
        List<Encuesta> lista = new ArrayList<>();
        String sql = "{ CALL listarEncuestas() }";
        
        try {
            // Conecta y ejecuta consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Encuesta bean = new Encuesta();
                bean.setIdEncuesta(rs.getInt(1));
                bean.setEstado(rs.getInt(3));
                bean.setRespuesta1(rs.getInt(4));
                bean.setRespuesta2(rs.getInt(5));
                bean.setRespuesta3(rs.getInt(6));
                // Maneja la fecha
                if (rs.getString(2) == null) {
                    bean.setFechaHoraLlenado(null);
                } else {
                    bean.setFechaHoraLlenado(LocalDateTime.parse(rs.getString(2), fmtSQL));
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
    
    public List listarPorEstado(int estado) {
        List<Encuesta> lista = new ArrayList<>();
        String sql = "{ CALL listarEncuestasPorEstado(?) }";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Encuesta bean = new Encuesta();
                bean.setIdEncuesta(rs.getInt(1));
                bean.setEstado(rs.getInt(3));
                bean.setRespuesta1(rs.getInt(4));
                bean.setRespuesta2(rs.getInt(5));
                bean.setRespuesta3(rs.getInt(6));
                // Maneja la fecha
                if (rs.getString(2) == null) {
                    bean.setFechaHoraLlenado(null);
                } else {
                    bean.setFechaHoraLlenado(LocalDateTime.parse(rs.getString(2), fmtSQL));
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
    
    public List listarPorFechayEstado(int estado, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Encuesta> lista = new ArrayList<>();
        String sql = "{ CALL listarEncuestasPorFechayEstado(?, ?, ?) }";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setString(2, fechaInicio.format(fmtSQL));
            ps.setString(3, fechaFin.format(fmtSQL));
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                // Convierte el resultado de la consulta en un objeto
                Encuesta bean = new Encuesta();
                bean.setIdEncuesta(rs.getInt(1));
                bean.setEstado(rs.getInt(3));
                bean.setRespuesta1(rs.getInt(4));
                bean.setRespuesta2(rs.getInt(5));
                bean.setRespuesta3(rs.getInt(6));
                // Maneja la fecha
                if (rs.getString(2) == null) {
                    bean.setFechaHoraLlenado(null);
                } else {
                    bean.setFechaHoraLlenado(LocalDateTime.parse(rs.getString(2), fmtSQL));
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
        
    public Encuesta buscarPorSolicitud(int idSolicitud) {
        // Si no se encuentra ninguna coincidencia, devuelve un objeto de id = -1
        Encuesta bean = new Encuesta();
        bean.setIdEncuesta(-1);
        String sql = "{ CALL buscarEncuestaPorSolicitud(?) }";
        
        try {
            // Conecta y prepara la consulta
            con = conectar.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud);
            // Ejecuta la consulta
            rs = ps.executeQuery();
            while (rs.next()) { // Solo debería contener una fila
                // Convierte el resultado de la consulta en un objeto
                bean.setIdEncuesta(rs.getInt(1));
                bean.setEstado(rs.getInt(3));
                bean.setRespuesta1(rs.getInt(4));
                bean.setRespuesta2(rs.getInt(5));
                bean.setRespuesta3(rs.getInt(6));
                // Maneja la fecha
                if (rs.getString(2) == null) {
                    bean.setFechaHoraLlenado(null);
                } else {
                    bean.setFechaHoraLlenado(LocalDateTime.parse(rs.getString(2), fmtSQL));
                }
            }
            con.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }        
        return bean;
    }
}
