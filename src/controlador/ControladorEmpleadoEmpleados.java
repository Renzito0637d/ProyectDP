package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaEmpleadoEmpleados;
import vista.VistaEmpleadoEmpleadosAgregar;
import vista.VistaEmpleadoEmpleadosEliminar;
import vista.VistaEmpleadoEmpleadosLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoEmpleados implements ActionListener{

    VistaEmpleadoEmpleados vista;

    public ControladorEmpleadoEmpleados(VistaEmpleadoEmpleados vista) {
        this.vista=vista;
        vista.btnLista.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
    }
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    private void resetButtons() {
        vista.btnAgregar.setSelected(false);
        vista.btnLista.setSelected(false);
        vista.btnEliminar.setSelected(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaEmpleadoEmpleadosLista lis=new VistaEmpleadoEmpleadosLista();
            CambiarPanel(lis);
            resetButtons();
        }
        if(e.getSource()==vista.btnAgregar){
            VistaEmpleadoEmpleadosAgregar agre=new VistaEmpleadoEmpleadosAgregar();
            CambiarPanel(agre);
            resetButtons();
        }
        if(e.getSource()==vista.btnEliminar){
            VistaEmpleadoEmpleadosEliminar eli=new VistaEmpleadoEmpleadosEliminar();
            CambiarPanel(eli);
            resetButtons();
        }
    }
    
}
