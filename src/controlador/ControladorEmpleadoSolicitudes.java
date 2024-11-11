package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import vista.VistaEmpleadoSolicitudes;
import vista.VistaEmpleadoSolicitudesEvaluar;
import vista.VistaEmpleadoSolicitudesLista;
import vista.VistaEmpleadoSolicitudesSeguimiento;

/**
 *
 * @author Renzo
 */
public class ControladorEmpleadoSolicitudes implements ActionListener{
    VistaEmpleadoSolicitudes vista;

    public ControladorEmpleadoSolicitudes(VistaEmpleadoSolicitudes vista) {
        this.vista=vista;
        vista.btnLista.addActionListener(this);
        vista.btnSeguimiento.addActionListener(this);
        vista.btnEvaluar.addActionListener(this);        
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
        vista.btnEvaluar.setSelected(false);
        vista.btnLista.setSelected(false);
        vista.btnSeguimiento.setSelected(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnLista){
            VistaEmpleadoSolicitudesLista lista=new VistaEmpleadoSolicitudesLista();
            CambiarPanel(lista);
        }
        if(e.getSource()==vista.btnSeguimiento){
            VistaEmpleadoSolicitudesSeguimiento soli=new VistaEmpleadoSolicitudesSeguimiento();
            CambiarPanel(soli);
        }
        if(e.getSource()==vista.btnEvaluar){
            VistaEmpleadoSolicitudesEvaluar eva=new VistaEmpleadoSolicitudesEvaluar();
            CambiarPanel(eva);
        }
        
    }
    
}
