package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import modelo.Cliente;
import vista.VistaClienteSolicitudes;
import vista.VistaClienteSolicitudesCrear;
import vista.VistaClienteSolicitudesHistorial;

/**
 *
 * @author Renzo
 */
public class ControladorClienteSolicitudes implements ActionListener{
    VistaClienteSolicitudesCrear crea;
    VistaClienteSolicitudes vista;
    Cliente cliente;

    public ControladorClienteSolicitudes(VistaClienteSolicitudes vista,Cliente cliente) {
        this.vista=vista;
        this.cliente=cliente;
        vista.btnCrear.addActionListener(this);
        vista.btnHistorial.addActionListener(this);
        
        crea= new VistaClienteSolicitudesCrear();
        CambiarPanel(crea);
        new ControladorClienteSolicitudesCrear(crea, cliente);
        vista.btnCrear.setSelected(true);
    }
    public void CambiarPanel(JPanel box) {
        box.setPreferredSize(new Dimension(1000, 500)); // Tamaño inicial

        vista.PanelCambio.removeAll();
        vista.PanelCambio.setLayout(new BorderLayout());
        vista.PanelCambio.add(box, BorderLayout.CENTER);
        vista.PanelCambio.revalidate();
        vista.PanelCambio.repaint();
    }
    private void setSelectedButton(javax.swing.JButton selectedButton) {
        javax.swing.JButton[] botones = {
            vista.btnCrear, 
            vista.btnHistorial
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnCrear){
            VistaClienteSolicitudesCrear crea=new VistaClienteSolicitudesCrear();
            CambiarPanel(crea);
            new ControladorClienteSolicitudesCrear(crea, cliente);
            setSelectedButton(vista.btnCrear);
        }
        if(e.getSource()==vista.btnHistorial){
            VistaClienteSolicitudesHistorial histo=new VistaClienteSolicitudesHistorial();
            CambiarPanel(histo);
            new ControladorClienteSolicitudesVer(histo, cliente);
            setSelectedButton(vista.btnHistorial);
        }
    }
    
}
