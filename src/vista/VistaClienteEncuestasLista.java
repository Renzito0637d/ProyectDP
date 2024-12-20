/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Renzo
 */
public class VistaClienteEncuestasLista extends javax.swing.JPanel {

    /**
     * Creates new form VistaClienteCuentaDetalles
     */
    public VistaClienteEncuestasLista() {
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

        pnlInfo1 = new javax.swing.JPanel();
        jLabel = new javax.swing.JLabel();
        txtNumeroEncuestas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        labEncuestas = new javax.swing.JLabel();
        btnActualizarListaEncuestas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEncuestas = new javax.swing.JTable();

        pnlInfo1.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlInfo1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel.setText("Tienes ");
        pnlInfo1.add(jLabel);

        txtNumeroEncuestas.setText("0");
        txtNumeroEncuestas.setFocusable(false);
        txtNumeroEncuestas.setMaximumSize(new java.awt.Dimension(40, 2147483647));
        txtNumeroEncuestas.setPreferredSize(new java.awt.Dimension(40, 22));
        pnlInfo1.add(txtNumeroEncuestas);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" encuestas pendientes");
        pnlInfo1.add(jLabel1);

        labEncuestas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labEncuestas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labEncuestas.setText("Lista de mis encuestas");
        labEncuestas.setPreferredSize(new java.awt.Dimension(168, 30));

        btnActualizarListaEncuestas.setText("Actualizar Lista de Encuestas");
        btnActualizarListaEncuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarListaEncuestasActionPerformed(evt);
            }
        });

        tbEncuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo de solicitud", "Cliente", "Fecha ingreso", "Estado actual", "Departamento evaluador"
            }
        ));
        jScrollPane1.setViewportView(tbEncuestas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labEncuestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizarListaEncuestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labEncuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarListaEncuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarListaEncuestasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarListaEncuestasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarListaEncuestasActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarListaEncuestas;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labEncuestas;
    private javax.swing.JPanel pnlInfo1;
    public javax.swing.JTable tbEncuestas;
    public javax.swing.JTextField txtNumeroEncuestas;
    // End of variables declaration//GEN-END:variables
}
