/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

/**
 *
 * @author Renzo
 */
public class VistaClienteEncuestas extends javax.swing.JPanel {

    /**
     * Creates new form VistaClienteCuenta
     */
    public VistaClienteEncuestas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelCambio = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnLista = new javax.swing.JButton();
        btnCompletar = new javax.swing.JButton();
        btnOtros = new javax.swing.JButton();

        PanelCambio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelCambioLayout = new javax.swing.GroupLayout(PanelCambio);
        PanelCambio.setLayout(PanelCambioLayout);
        PanelCambioLayout.setHorizontalGroup(
            PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        PanelCambioLayout.setVerticalGroup(
            PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(153, 0, 153));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        btnLista.setBackground(new java.awt.Color(153, 0, 153));
        btnLista.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLista.setForeground(new java.awt.Color(255, 255, 255));
        btnLista.setText("Lista de encuestas");
        btnLista.setBorder(null);
        btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnCompletar.setBackground(new java.awt.Color(153, 0, 153));
        btnCompletar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCompletar.setForeground(new java.awt.Color(255, 255, 255));
        btnCompletar.setText("Completar encuestas");
        btnCompletar.setBorder(null);
        btnCompletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnOtros.setBackground(new java.awt.Color(153, 0, 153));
        btnOtros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOtros.setForeground(new java.awt.Color(255, 255, 255));
        btnOtros.setText("Otras encuestas");
        btnOtros.setBorder(null);
        btnOtros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCompletar, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(btnOtros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelCambio;
    public javax.swing.JButton btnCompletar;
    public javax.swing.JButton btnLista;
    public javax.swing.JButton btnOtros;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}