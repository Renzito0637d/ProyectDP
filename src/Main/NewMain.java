package Main;
import dao.ClienteDAO;
import modelo.*;
import java.sql.Connection;
import java.util.List;

public class NewMain {

    public static void main(String[] args) {
        //PRUEBAS DE EJECUCION del punto 2 y 3
        
        
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
        
        //PRUEBA 3 CRUD
        // Crear una instancia de ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // Crear un nuevo Cliente ya ingresando los datos
        //Cliente nuevoCliente = new Cliente();
        //nuevoCliente.setNombres("Juan");
        //nuevoCliente.setApellidos("Pérez");
        //nuevoCliente.setEmail("juan.perez@ejemploo.com");
        //nuevoCliente.setTelefono("123456789");
        //nuevoCliente.setUsuario("juanperez");
        //nuevoCliente.setClave("miClaveSegura");
        //nuevoCliente.setDireccion("Calle ejem 123");        
        // Llamar al método agregar para insertar el nuevo cliente
        //int resultado = clienteDAO.agregar(nuevoCliente);
        
        // Verificar el resultado
        //if (resultado == 1) {
//            System.out.println("Cliente agregado exitosamente.");
//        } else if (resultado == 1062) { // Error de duplicación (si la clave única es el usuario)
  //          System.out.println("Error: El usuario ya existe.");
  //      } else {
//            System.out.println("Error al agregar el cliente. Codigo de error: " + resultado);
//        }                                
        

        //UPDATE
        //Cliente clienteActualizado = new Cliente();
        //clienteActualizado.setCodigoCliente(6); // PORQUE ES 6? es por q el codigo del cliente q queremos actu es 6
        //clienteActualizado.setNombres("Juan Actualizado");
        //clienteActualizado.setApellidos("Pérez Actualizado");
        //clienteActualizado.setEmail("juan.perez.actualizado@example.com");
        //clienteActualizado.setTelefono("987654321");
        //clienteActualizado.setUsuario("juanperezactualizado");
        //clienteActualizado.setClave("nuevaClaveSegura");
        //clienteActualizado.setDireccion("Calle Verdadera 456");

        // Llamar al método actualizar
        //int resultado = clienteDAO.actualizar(clienteActualizado);

        // Verificar el resultado
        //if (resultado == 1) {
//            System.out.println("Cliente actualizado exitosamente.");
//        } else {
  //          System.out.println("Error al actualizar el cliente.");
  //      }
  
  
   
        //READ
        // Llamar al método listarTodos para obtener la lista de clientes
        //List<Cliente> listaClientes = clienteDAO.listarTodos();

        // Verificar si la lista no está vacía
        //if (listaClientes != null && !listaClientes.isEmpty()) {
        //    System.out.println("Lista de todos los clientes:");
            // Iterar sobre la lista y mostrar los datos de cada cliente
        //    for (Cliente cliente : listaClientes) {
        //        System.out.println("ID: " + cliente.getCodigoCliente() +
        //                           ", Nombres: " + cliente.getNombres() +
        //                           ", Apellidos: " + cliente.getApellidos() +
        //                           ", Email: " + cliente.getEmail() +
        //                           ", Teléfono: " + cliente.getTelefono() +
        //                           ", Usuario: " + cliente.getUsuario() +
        //                           ", Dirección: " + cliente.getDireccion());
        //    }
        //} else {
        //    System.out.println("No se encontraron clientes en la base de datos.");
        //}

        
        //DELETE
        // ID del cliente que deseas eliminar
        //int idCliente = 6; // Cambia este valor al ID que quieras eliminar

        // Llamar al método eliminar y almacenar el resultado
        //int resultado = clienteDAO.eliminar(idCliente);

        // Verificar el resultado
        //if (resultado == 1) {
//            System.out.println("Cliente eliminado exitosamente.");
//        } else {
//            System.out.println("Error al eliminar el cliente.");
//        }
        
    }    
}
