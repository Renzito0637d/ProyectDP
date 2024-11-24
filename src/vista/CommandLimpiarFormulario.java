/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorRegistroCliente;

/**
 *
 * @author Renzo
 */
//PATRON COMMAND
public class CommandLimpiarFormulario implements Command {
    private final ControladorRegistroCliente controlador;    

    public CommandLimpiarFormulario(ControladorRegistroCliente controlador) {
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        controlador.limpiar();
    }
}

