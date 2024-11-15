package NewMain;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import controlador.ControladorCliente;
import controlador.ControladorLogin;
import modelo.MiConexion;
import vista.VistaLogin;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Solicitud;
import modelo.SolicitudDAO;

public class Main {

    public static void main(String[] args) {
        Map<String, String> extraDefaults = new HashMap<>();
        extraDefaults.put("@accentColor", "#fffc33");         
            
        FlatLaf.setGlobalExtraDefaults(extraDefaults);
        FlatLightLaf.setup();
        VistaLogin vista = new VistaLogin();
        ControladorLogin ctrl = new ControladorLogin(vista);
        ctrl.iniciar();
        
        //PUNTO 2 probamos la conectividada a la base de datos NOTA: se uliso workbench, puente XAMPP
        MiConexion miConexion = MiConexion.getInstance();

        // Obtener la conexión a la base de datos
        Connection conexion = miConexion.obtenerConexion();

        // Verificar si la conexión fue exitosa
        if (conexion != null) {
            System.out.println("Conexion exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexion a la base de datos.");
        }   
    }
}

