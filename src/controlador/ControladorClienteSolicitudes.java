package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaClienteSolicitudes;
import vista.VistaClienteSolicitudesCrear;
import vista.VistaClienteSolicitudesHistorial;

/**
 *
 * @author Renzo
 */
public class ControladorClienteSolicitudes implements ActionListener{
    VistaClienteSolicitudes vista;

    public ControladorClienteSolicitudes(VistaClienteSolicitudes vista) {
        this.vista=vista;
        vista.btnCrear.addActionListener(this);
        vista.btnHistorial.addActionListener(this);
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
        vista.btnCrear.setSelected(false);
        vista.btnHistorial.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnCrear){
            VistaClienteSolicitudesCrear crea=new VistaClienteSolicitudesCrear();
            CambiarPanel(crea);
            resetButtons();            
        }
        if(e.getSource()==vista.btnHistorial){
            VistaClienteSolicitudesHistorial histo=new VistaClienteSolicitudesHistorial();
            CambiarPanel(histo);
            resetButtons();
        }
    }
    
}
