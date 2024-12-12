/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Encuesta;
import modelo.EncuestaDAO;
import vista.VistaEncuesta;

/**
 *
 * @author Renzo
 */
public class ControladorEncuestas implements ActionListener{
     // La vista correspondiente a este controlador
    VistaEncuesta vista;
    Encuesta encuesta;
    
    // DAO
    EncuestaDAO encuestaDAO;

    public ControladorEncuestas(VistaEncuesta vista, Encuesta encuesta) {
        this.vista = vista;
        this.encuesta = encuesta;
        // Eventos
        this.vista.btnCompletarEncuesta.addActionListener(this);
        this.vista.btnVolverEncuesta.addActionListener(this);
    }
    
    // Eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCompletarEncuesta) {
            actualizarEncuesta();
        }        
    }
    
    // MÉTODOS PROPIOS
    
    // OPERACIONES DE INSERCION-ACTUALIZACION-DELECION
    public void actualizarEncuesta() {
        // Verificar que todas las preguntas estén marcadas
        int respuesta1 = leerPregunta1();
        boolean check1 = respuesta1 != -1;
        int respuesta2 = leerPregunta2();
        boolean check2 = respuesta2 != -1;
        int respuesta3 = leerPregunta3();
        boolean check3 = respuesta3 != -1;
        
        // Evaluar
        if (check1 == false) {
            JOptionPane.showMessageDialog(vista, "Falta responder la Pregunta 1");
        }
        else if (check2 == false) {
            JOptionPane.showMessageDialog(vista, "Falta responder la Pregunta 2");
        }
        else if (check3 == false) {
            JOptionPane.showMessageDialog(vista, "Falta responder la Pregunta 3");
        }
        else {
            // Actualizar atributos
            encuesta.setRespuesta1(respuesta1);
            encuesta.setRespuesta2(respuesta2);
            encuesta.setRespuesta3(respuesta3);
            encuesta.completarEncuesta();
            // Actualiza en la BD
            encuestaDAO = new EncuestaDAO();
            int res = encuestaDAO.actualizar(encuesta);
            switch (res) {
                case 1 ->
                    JOptionPane.showMessageDialog(vista, "Encuesta completada!");
                default ->
                    JOptionPane.showMessageDialog(vista, "Error: No se pudo completar la encuesta. ERROR: "+res);
            }
            
            // Volver
            ControladorClienteEncuestasCompletar.limpiarPanel(vista);
            
        }
    }
    
    // LECTURA DE DATOS Y VALIDACIONES    
    public int leerPregunta1() {
        if (vista.rbtnP1_respuesta1.isSelected()) {
            return 1;
        }
        else if (vista.rbtnP1_respuesta2.isSelected()) {
            return 2;
        }
        else if (vista.rbtnP1_respuesta3.isSelected()) {
            return 3;
        }
        else if (vista.rbtnP1_respuesta4.isSelected()) {
            return 4;
        }
        else if (vista.rbtnP1_respuesta5.isSelected()) {
            return 5;
        }
        else {
            return -1;
        }        
    }
    
    public int leerPregunta2() {
        if (vista.rbtnP2_respuesta1.isSelected()) {
            return 1;
        }
        else if (vista.rbtnP2_respuesta2.isSelected()) {
            return 2;
        }
        else if (vista.rbtnP2_respuesta3.isSelected()) {
            return 3;
        }
        else if (vista.rbtnP2_respuesta4.isSelected()) {
            return 4;
        }
        else if (vista.rbtnP2_respuesta5.isSelected()) {
            return 5;
        }
        else {
            return -1;
        }        
    }
    
    public int leerPregunta3() {
        if (vista.rbtnP3_respuesta1.isSelected()) {
            return 1;
        }
        else if (vista.rbtnP3_respuesta2.isSelected()) {
            return 2;
        }
        else if (vista.rbtnP3_respuesta3.isSelected()) {
            return 3;
        }
        else if (vista.rbtnP3_respuesta4.isSelected()) {
            return 4;
        }
        else if (vista.rbtnP3_respuesta5.isSelected()) {
            return 5;
        }
        else {
            return -1;
        }        
    }

}
