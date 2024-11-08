package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaClienteCuenta;
import vista.VistaClienteCuentaDetalles;

/**
 *
 * @author Renzo
 */
public class ControladorClienteCuenta implements ActionListener{
    VistaClienteCuenta vista;
    VistaClienteCuentaDetalles deta;
    

    public ControladorClienteCuenta(VistaClienteCuenta vista) {
        this.vista=vista;
        vista.btnDetalles.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        
        deta=new VistaClienteCuentaDetalles();
        CambiarPanel(deta);
        vista.btnDetalles.setSelected(true);
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
        vista.btnDetalles.setSelected(false);
        vista.btnEliminar.setSelected(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnDetalles){
            VistaClienteCuentaDetalles deta=new VistaClienteCuentaDetalles();
            CambiarPanel(deta);
            resetButtons();
        }
    }
    
}
