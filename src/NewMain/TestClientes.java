package NewMain;

import java.util.List;
import java.util.Scanner;
import modelo.Cliente;
import modelo.ClienteDAO;

public class TestClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear una instancia del DAO
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // Usar Scanner para leer los datos del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Crear un objeto Cliente
        Cliente nuevoCliente = new Cliente();
        
        // Solicitar y asignar los datos
        System.out.print("Ingrese los nombres del cliente: ");
        nuevoCliente.setNombres(scanner.nextLine());
        
        System.out.print("Ingrese los apellidos del cliente: ");
        nuevoCliente.setApellidos(scanner.nextLine());
        
        System.out.print("Ingrese el email del cliente: ");
        nuevoCliente.setEmail(scanner.nextLine());
        
        System.out.print("Ingrese el teléfono del cliente: ");
        nuevoCliente.setTelefono(scanner.nextLine());
        
        System.out.print("Ingrese el nombre de usuario del cliente: ");
        nuevoCliente.setUsuario(scanner.nextLine());
        
        System.out.print("Ingrese la clave del cliente: ");
        nuevoCliente.setClave(scanner.nextLine());
        
        System.out.print("Ingrese la dirección del cliente: ");
        nuevoCliente.setDireccion(scanner.nextLine());
        
        // Registrar el cliente a través del DAO
        int resultado = clienteDAO.agregar(nuevoCliente);
        
        // Verificar si el registro fue exitoso
        if (resultado == 1) {
            System.out.println("Cliente registrado exitosamente.");
        } else if (resultado == 1062) {
            System.out.println("Error: El usuario ya existe.");
        } else {
            System.out.println("Error al registrar el cliente.");
        }
        
        // Cerrar el scanner
        scanner.close();
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el cpdigo a eliminar del cliente:");
        int cd =sc.nextInt();
        int idCliente = cd; // Cambia este valor al ID que quieras eliminar

        //Llamar al método eliminar y almacenar el resultado
        int resultado = clienteDAO.eliminar(idCliente);

        //Verificar el resultado
        if (resultado == 1) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el cliente.");
        }
    }
    
    
    
}
