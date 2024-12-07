package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MiConexion {
    // Instancia única de clase - SINGLETON
    private static final MiConexion INSTANCE = new MiConexion();
    
    // Constructor oculto
    private MiConexion() {};
    
    // Método estático que retorna la clase única
    public static MiConexion getInstance() {
        return INSTANCE;
    }
    
    // Conexión a base de datos
    Connection con;   
    
    public Connection obtenerConexion(){
        String bd = "proyectotamboaaa";
        String url = "jdbc:mysql://localhost/"+bd;
        String user = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            // JOptionPane.showMessageDialog(null, "Conexión Exitosa");
            return con;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, No hay Conexión\n" + e);
            return con=null;
        }
    }
    
    
}
