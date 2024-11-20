/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import vista.VistaClienteCuentaDetalles;

/**
 *
 * @author Renzo
 */
public class ControladorClienteCuentaDetalles {
    VistaClienteCuentaDetalles vista;
    Cliente cliente;

    public ControladorClienteCuentaDetalles(VistaClienteCuentaDetalles vista,Cliente cliente) {
        this.vista = vista;
        this.cliente=cliente;
        rellenarInformacion();
    }
    private void rellenarInformacion(){
        vista.lbUser.setText(cliente.getUsuario());
        vista.lbNombre.setText(cliente.getNombres()+" "+cliente.getApellidos());        
        vista.lbCorreo.setText(cliente.getEmail());
        vista.lbDireccion.setText(cliente.getDireccion());
        vista.lbTelefono.setText(cliente.getTelefono());
    }
}
