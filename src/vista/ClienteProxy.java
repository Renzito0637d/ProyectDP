/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JLabel;

/**
 *
 * @author Renzo
 */
//patron PROXY para dar la bienvida al usuario
public class ClienteProxy implements InterfazCliente{
    private String bienvenida;
    private InterfazCliente cliente;
    public ClienteProxy(String bienvenida) {
        this.bienvenida = bienvenida;
    }

    @Override
    public void mostrarBienv(JLabel label) {
        if(cliente==null){
           cliente =new ClienteProxy(bienvenida);
        }
        cliente.mostrarBienv(label);
    }    
    
}
