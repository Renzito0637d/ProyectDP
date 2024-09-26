
package vista;


public class VistaLogin extends javax.swing.JFrame {

    
    public VistaLogin() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpRol = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        chkClaveLogin = new javax.swing.JCheckBox();
        btnLimpiarLogin = new javax.swing.JButton();
        btnIngresarLogin = new javax.swing.JButton();
        btnRegistrarLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rbtnEmpleado = new javax.swing.JRadioButton();
        rbtnCliente = new javax.swing.JRadioButton();
        txtClaveLogin = new javax.swing.JPasswordField();
        txtUsuarioLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel2.setText("Inicio de Sesión");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 151, -1));

        chkClaveLogin.setBackground(new java.awt.Color(255, 255, 255));
        chkClaveLogin.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        chkClaveLogin.setText("Ver");
        chkClaveLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkClaveLoginActionPerformed(evt);
            }
        });
        bg.add(chkClaveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        btnLimpiarLogin.setText("LIMPIAR");
        btnLimpiarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLoginActionPerformed(evt);
            }
        });
        bg.add(btnLimpiarLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        btnIngresarLogin.setText("INGRESAR");
        btnIngresarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarLoginActionPerformed(evt);
            }
        });
        bg.add(btnIngresarLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        btnRegistrarLogin.setText("Registrate ahora ->");
        bg.add(btnRegistrarLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 157, -1));

        jLabel5.setText("¿Eres cliente y no tienes una cuenta?");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 231, -1));

        rbtnEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        btngrpRol.add(rbtnEmpleado);
        rbtnEmpleado.setText("Personal de Tambo");
        bg.add(rbtnEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 135, -1));

        rbtnCliente.setBackground(new java.awt.Color(255, 255, 255));
        btngrpRol.add(rbtnCliente);
        rbtnCliente.setText("Cliente");
        bg.add(rbtnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 98, -1));

        txtClaveLogin.setBorder(null);
        bg.add(txtClaveLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 252, -1));

        txtUsuarioLogin.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuarioLogin.setBorder(null);
        txtUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioLoginActionPerformed(evt);
            }
        });
        bg.add(txtUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 299, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel4.setText("Contraseña");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 93, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel3.setText("Usuario");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 75, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo de Tambo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/TRABAJODRES TAMBO.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 330, 350));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 143, 310, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 20));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 300, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarLoginActionPerformed

    private void btnIngresarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarLoginActionPerformed

    private void chkClaveLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkClaveLoginActionPerformed
        if (chkClaveLogin.isSelected()) {
            txtClaveLogin.setEchoChar((char) 0);
        } else {
            txtClaveLogin.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_chkClaveLoginActionPerformed

    private void txtUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioLoginActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    public javax.swing.JButton btnIngresarLogin;
    public javax.swing.JButton btnLimpiarLogin;
    public javax.swing.JButton btnRegistrarLogin;
    private javax.swing.ButtonGroup btngrpRol;
    public javax.swing.JCheckBox chkClaveLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JRadioButton rbtnCliente;
    public javax.swing.JRadioButton rbtnEmpleado;
    public javax.swing.JPasswordField txtClaveLogin;
    public javax.swing.JTextField txtUsuarioLogin;
    // End of variables declaration//GEN-END:variables
}
