package controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Cliente;
import vista.*;

public class ControladorCliente implements ActionListener {

    private GestorDePaneles gestorDePaneles;  // Usamos de patron BRIGDE para cambiar paneles
    private Cliente cliente;
    private VistaCliente vista;
    private ControladorLogin controladorPrevio;

    public ControladorCliente(VistaCliente vista, ControladorLogin controladorPrevio, Cliente cliente) {
        this.vista = vista;
        this.controladorPrevio = controladorPrevio;
        this.cliente = cliente;
        vista.btnCuenta.addActionListener(this);
        vista.btnSolicitudes.addActionListener(this);
        vista.btnEncuestas.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        vista.btnLimparPanel.addActionListener(this);

        // Inicializar el gestor de paneles con el primer panel (por ejemplo, PanelSolicitudes)
        PanelInterface panelInicial = new PanelSolicitudes(cliente);  // Panel por defecto
        gestorDePaneles = new GestorDePaneles(this, panelInicial);
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
            vista.btnCuenta, 
            vista.btnEncuestas, 
            vista.btnLimparPanel, 
            vista.btnSolicitudes
        };
    
        for (javax.swing.JButton boton : botones) {
            boton.setSelected(boton == selectedButton);
        }
    }
    private void ponerTitulo(Cliente cliente,JLabel label){
        cliente.mostrarBienv(label);
    }
    public void iniciar() {
        vista.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        vista.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(153,0,153));
        vista.setSize(800, 600);  // Establece un tamaño inicial
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.setTitle("Tiendas Tambo - Quejas y Reclamos");
        vista.setLocationRelativeTo(null);          
        vista.setVisible(true);
        ponerTitulo(cliente,vista.lbTitle);

        // Iniciar con el panel de solicitudes
        gestorDePaneles.cambiarPanel();
    }

    public void salir() {
        controladorPrevio.iniciar();
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCuenta) {
            // Cambiar a PanelCuenta
            PanelInterface nuevoPanel = new PanelCuenta(cliente,this);
            gestorDePaneles.setPanel(nuevoPanel);
            gestorDePaneles.cambiarPanel();
            setSelectedButton(vista.btnCuenta);
        }
        if (e.getSource() == vista.btnSolicitudes) {
            // Cambiar a PanelSolicitudes
            PanelInterface nuevoPanel = new PanelSolicitudes(cliente);
            gestorDePaneles.setPanel(nuevoPanel);
            gestorDePaneles.cambiarPanel();
            setSelectedButton(vista.btnSolicitudes);
        }
        if (e.getSource() == vista.btnEncuestas) {
            // Cambiar a PanelEncuestas
            PanelInterface nuevoPanel = new PanelEncuestas(cliente);
            gestorDePaneles.setPanel(nuevoPanel);
            gestorDePaneles.cambiarPanel();
            setSelectedButton(vista.btnEncuestas);
        }
        if (e.getSource() == vista.btnSalir) {
            salir();
        }
        if(e.getSource()==vista.btnLimparPanel){
            VistaLimpia limp=new VistaLimpia();
            CambiarPanel(limp);
        }
    }
}
