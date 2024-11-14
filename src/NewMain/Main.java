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
        
        
        
         int opcion =0;
        Scanner sc = new Scanner(System.in);
        TestClientes pr = new TestClientes();
        
        do{
        
                
        System.out.println("OPCION [1]: mostrar todos los clientes");
        System.out.println("OPCION [2]: ELIMINAR UN CLIENTE"); 
        System.out.println("");
        System.out.print("OPRCION ESCOQUIDA:");
        opcion=sc.nextInt();
        
            switch (opcion) {
            case 1:
                pr.verTodosClientes();
                break;
            case 2:
                pr.borrarCliente();
                break;    
            default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
        }while(opcion!=4);
        
        
    }    
    
    
    
    
    
    public void verTodosClientes(){
        ClienteDAO clienteDAO = new ClienteDAO();
        // Llamar al método listarTodos para obtener la lista de clientes
        List<Cliente> listaClientes = clienteDAO.listarTodos();

        //Verificar si la lista no está vacía
        if (listaClientes != null && !listaClientes.isEmpty()) {
            System.out.println("Lista de todos los clientes:");
            // Iterar sobre la lista y mostrar los datos de cada cliente
            for (Cliente cliente : listaClientes) {
                System.out.println("ID: " + cliente.getCodigoCliente() +
                                   ", Nombres: " + cliente.getNombres() +
                                   ", Apellidos: " + cliente.getApellidos() +
                                   ", Email: " + cliente.getEmail() +
                                   ", Teléfono: " + cliente.getTelefono() +
                                   ", Usuario: " + cliente.getUsuario() +
                                   ", Dirección: " + cliente.getDireccion());
            }
        }else {
            System.out.println("No se encontraron clientes en la base de datos.");
            }
    }
    
    public void borrarCliente(){
        ClienteDAO clienteDAO = new ClienteDAO();
        //ID del cliente que deseas eliminar
        int idCliente = 6; // Cambia este valor al ID que quieras eliminar

        //Llamar al método eliminar y almacenar el resultado
        int resultado = clienteDAO.eliminar(idCliente);

        //Verificar el resultado
        if (resultado == 1) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el cliente.");
        }
    }
    
    public void actualizarUnCliente(){
        // Crear instancia de Scanner
        Scanner scanner = new Scanner(System.in);
        
        // Crear una instancia de ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // Pedir al usuario el código del cliente que desea actualizar
        System.out.print("Ingrese el código del cliente que desea actualizar: ");
        int codigoCliente = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        // Crear un objeto Cliente para actualizar
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCodigoCliente(codigoCliente);
        
        // Pedir los nuevos datos del cliente
        System.out.print("Ingrese el nuevo nombre: ");
        clienteActualizado.setNombres(scanner.nextLine());
        
        System.out.print("Ingrese el nuevo apellido: ");
        clienteActualizado.setApellidos(scanner.nextLine());
        
        System.out.print("Ingrese el nuevo email: ");
        clienteActualizado.setEmail(scanner.nextLine());
        
        System.out.print("Ingrese el nuevo teléfono: ");
        clienteActualizado.setTelefono(scanner.nextLine());
        
        System.out.print("Ingrese el nuevo usuario: ");
        clienteActualizado.setUsuario(scanner.nextLine());
        
        System.out.print("Ingrese la nueva clave: ");
        clienteActualizado.setClave(scanner.nextLine());
        
        System.out.print("Ingrese la nueva dirección: ");
        clienteActualizado.setDireccion(scanner.nextLine());
        
        // Llamar al método actualizar
        int resultado = clienteDAO.actualizar(clienteActualizado);
        
        // Verificar el resultado
        if (resultado == 1) {
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el cliente.");
        }
        
        // Cerrar el scanner
        scanner.close();
    }
    
}

