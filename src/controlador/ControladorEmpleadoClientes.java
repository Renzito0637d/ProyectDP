package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaEmpleadoClientes;
import vista.VistaEmpleadoClientesAgregar;
import vista.VistaEmpleadoClientesEliminar;
import vista.VistaEmpleadoClientesLista;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoClientes implements ActionListener{
    VistaEmpleadoClientes vista;

    public ControladorEmpleadoClientes(VistaEmpleadoClientes vista) {
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
            VistaEmpleadoClientesLista lis=new VistaEmpleadoClientesLista();
            CambiarPanel(lis);
            resetButtons();
        }
        if(e.getSource()==vista.btnAgregar){
            VistaEmpleadoClientesAgregar agre=new VistaEmpleadoClientesAgregar();
            CambiarPanel(agre);
            resetButtons();
        }
        if(e.getSource()==vista.btnEliminar){
            VistaEmpleadoClientesEliminar eli=new VistaEmpleadoClientesEliminar();
            CambiarPanel(eli);
            resetButtons();
        }
    }
    
}
