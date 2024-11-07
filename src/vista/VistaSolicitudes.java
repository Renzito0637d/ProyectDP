/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

/**
 *
 * @author Renzo
 */
public class VistaSolicitudes extends javax.swing.JPanel {

    /**
     * Creates new form VistaSolicitudes
     */
    public VistaSolicitudes() {
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jpSolicitudes = new javax.swing.JPanel();
        jpHead = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        labTitulo = new javax.swing.JLabel();
        pnlFiltroEstado = new javax.swing.JPanel();
        labFiltroEstado = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        cbxFiltrarEstadoSol = new javax.swing.JComboBox<>();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(15, 32767));
        btnActualizarListaSol = new javax.swing.JButton();
        pnlTablaS = new javax.swing.JPanel();
        scrlTablaSolicitudes = new javax.swing.JScrollPane();
        tbSolicitudes = new javax.swing.JTable();
        labInfo1 = new javax.swing.JLabel();
        jpBody = new javax.swing.JPanel();
        labSubTitulo1 = new javax.swing.JLabel();
        pnlDetalle1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDetalleSolicitud = new javax.swing.JTextArea();
        jpBottom = new javax.swing.JPanel();
        labSubTitulo2 = new javax.swing.JLabel();
        pnlSeguimiento = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlTablaE = new javax.swing.JPanel();
        scrlTablaEvaluaciones = new javax.swing.JScrollPane();
        tbEvaluaciones = new javax.swing.JTable();
        labInfo2 = new javax.swing.JLabel();
        pnlInfoEval = new javax.swing.JPanel();
        labSubTitulo3 = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaDetalleEvaluacion = new javax.swing.JTextArea();
        pnlNuevaEval = new javax.swing.JPanel();
        labSubTitulo4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlCambioEstado = new javax.swing.JPanel();
        labCambioEstado = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        rbtnTramite = new javax.swing.JRadioButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        rbtnFinalizado = new javax.swing.JRadioButton();
        pnlEnvioDepartamento = new javax.swing.JPanel();
        labEnvioDepartamento = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        cbxDepartamentoSol = new javax.swing.JComboBox<>();
        pnlAgregarDescipcion = new javax.swing.JPanel();
        labAgregarDescripcion = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDescripcionEvaluacion = new javax.swing.JTextArea();
        pnlBotones = new javax.swing.JPanel();
        btnEvaluarSol = new javax.swing.JButton();
        btnLimpiarSol = new javax.swing.JButton();
        btnSalirSol = new javax.swing.JButton();

        jpSolicitudes.setPreferredSize(new java.awt.Dimension(400, 900));
        jpSolicitudes.setLayout(new java.awt.BorderLayout(5, 5));

        jpHead.setLayout(new java.awt.BorderLayout(0, 10));

        pnlHead.setPreferredSize(new java.awt.Dimension(193, 75));
        pnlHead.setLayout(new java.awt.GridLayout(2, 0, 0, 15));

        labTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitulo.setText("Listado de Solicitudes");
        pnlHead.add(labTitulo);

        pnlFiltroEstado.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlFiltroEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        labFiltroEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labFiltroEstado.setText("Filtrar por Estado: ");
        pnlFiltroEstado.add(labFiltroEstado);
        pnlFiltroEstado.add(filler4);

        cbxFiltrarEstadoSol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "En Espera", "En Trámite", "Finalizado" }));
        cbxFiltrarEstadoSol.setMaximumSize(new java.awt.Dimension(100, 80));
        pnlFiltroEstado.add(cbxFiltrarEstadoSol);
        pnlFiltroEstado.add(filler9);

        btnActualizarListaSol.setText("Actualizar Lista de Solicitudes");
        pnlFiltroEstado.add(btnActualizarListaSol);

        pnlHead.add(pnlFiltroEstado);

        jpHead.add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlTablaS.setMaximumSize(new java.awt.Dimension(2147483647, 180));
        pnlTablaS.setPreferredSize(new java.awt.Dimension(579, 170));
        pnlTablaS.setRequestFocusEnabled(false);
        pnlTablaS.setLayout(new java.awt.BorderLayout());

        scrlTablaSolicitudes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlTablaSolicitudes.setMaximumSize(new java.awt.Dimension(32767, 200));
        scrlTablaSolicitudes.setPreferredSize(new java.awt.Dimension(452, 150));

        tbSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"123", "QUEJA", "Luis Gonzales", "12-05-2020", "FINALIZADO", "Gerencia"}
            },
            new String [] {
                "ID", "Tipo de Solicitud", "Cliente", "Fecha Ingreso", "Estado actual", "Departamento Evaluador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSolicitudes.setMaximumSize(new java.awt.Dimension(450, 200));
        tbSolicitudes.setPreferredSize(new java.awt.Dimension(450, 200));
        tbSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSolicitudesMouseClicked(evt);
            }
        });
        scrlTablaSolicitudes.setViewportView(tbSolicitudes);

        pnlTablaS.add(scrlTablaSolicitudes, java.awt.BorderLayout.PAGE_START);

        labInfo1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        labInfo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labInfo1.setText("Seleccione una fila para mostrar la información de la Solicitud correspondiente.");
        labInfo1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labInfo1.setMaximumSize(new java.awt.Dimension(411, 20));
        labInfo1.setPreferredSize(new java.awt.Dimension(411, 20));
        pnlTablaS.add(labInfo1, java.awt.BorderLayout.PAGE_END);

        jpHead.add(pnlTablaS, java.awt.BorderLayout.CENTER);

        jpSolicitudes.add(jpHead, java.awt.BorderLayout.PAGE_START);

        jpBody.setMaximumSize(new java.awt.Dimension(800, 100));
        jpBody.setPreferredSize(new java.awt.Dimension(579, 100));
        jpBody.setLayout(new java.awt.BorderLayout());

        labSubTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labSubTitulo1.setText("Detalles de la Solicitud seleccionada");
        labSubTitulo1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        labSubTitulo1.setMaximumSize(new java.awt.Dimension(198, 20));
        labSubTitulo1.setMinimumSize(new java.awt.Dimension(198, 20));
        labSubTitulo1.setPreferredSize(new java.awt.Dimension(198, 20));
        jpBody.add(labSubTitulo1, java.awt.BorderLayout.PAGE_START);

        pnlDetalle1.setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        txaDetalleSolicitud.setColumns(20);
        txaDetalleSolicitud.setRows(5);
        txaDetalleSolicitud.setFocusable(false);
        jScrollPane1.setViewportView(txaDetalleSolicitud);

        jPanel1.add(jScrollPane1);

        pnlDetalle1.add(jPanel1, "card3");

        jpBody.add(pnlDetalle1, java.awt.BorderLayout.CENTER);

        jpSolicitudes.add(jpBody, java.awt.BorderLayout.CENTER);

        jpBottom.setPreferredSize(new java.awt.Dimension(452, 520));
        jpBottom.setLayout(new java.awt.BorderLayout());

        labSubTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labSubTitulo2.setText("Seguimiento de la Solicitud seleccionada");
        jpBottom.add(labSubTitulo2, java.awt.BorderLayout.PAGE_START);

        pnlSeguimiento.setPreferredSize(new java.awt.Dimension(411, 500));
        pnlSeguimiento.setLayout(new java.awt.CardLayout(3, 5));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(405, 400));
        jPanel2.setLayout(new java.awt.BorderLayout());

        pnlTablaE.setMaximumSize(new java.awt.Dimension(2147483647, 130));
        pnlTablaE.setPreferredSize(new java.awt.Dimension(579, 120));
        pnlTablaE.setRequestFocusEnabled(false);
        pnlTablaE.setLayout(new java.awt.BorderLayout());

        scrlTablaEvaluaciones.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlTablaEvaluaciones.setMaximumSize(new java.awt.Dimension(32767, 100));
        scrlTablaEvaluaciones.setPreferredSize(new java.awt.Dimension(452, 100));

        tbEvaluaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número Evaluación", "Fecha", "Hora", "Estado", "Evaluador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEvaluaciones.setMaximumSize(new java.awt.Dimension(450, 200));
        tbEvaluaciones.setPreferredSize(new java.awt.Dimension(450, 140));
        tbEvaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEvaluacionesMouseClicked(evt);
            }
        });
        scrlTablaEvaluaciones.setViewportView(tbEvaluaciones);

        pnlTablaE.add(scrlTablaEvaluaciones, java.awt.BorderLayout.PAGE_START);

        labInfo2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        labInfo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labInfo2.setText("Seleccione una fila para mostrar los detalles y comentarios de la Evaluación correspondiente.");
        labInfo2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labInfo2.setMaximumSize(new java.awt.Dimension(411, 20));
        labInfo2.setPreferredSize(new java.awt.Dimension(411, 20));
        pnlTablaE.add(labInfo2, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(pnlTablaE, java.awt.BorderLayout.PAGE_START);

        pnlInfoEval.setPreferredSize(new java.awt.Dimension(411, 80));
        pnlInfoEval.setLayout(new java.awt.BorderLayout());

        labSubTitulo3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labSubTitulo3.setText("Información");
        pnlInfoEval.add(labSubTitulo3, java.awt.BorderLayout.PAGE_START);

        pnlInfo.setLayout(new java.awt.CardLayout());

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        txaDetalleEvaluacion.setColumns(20);
        txaDetalleEvaluacion.setRows(5);
        txaDetalleEvaluacion.setFocusable(false);
        jScrollPane2.setViewportView(txaDetalleEvaluacion);

        jPanel3.add(jScrollPane2);

        pnlInfo.add(jPanel3, "card3");

        pnlInfoEval.add(pnlInfo, java.awt.BorderLayout.CENTER);

        jPanel2.add(pnlInfoEval, java.awt.BorderLayout.CENTER);

        pnlNuevaEval.setPreferredSize(new java.awt.Dimension(411, 220));
        pnlNuevaEval.setLayout(new java.awt.BorderLayout());

        labSubTitulo4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labSubTitulo4.setText("Nueva Evaluación");
        pnlNuevaEval.add(labSubTitulo4, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        pnlCambioEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 3));

        labCambioEstado.setText("Cambiar Estado a: ");
        pnlCambioEstado.add(labCambioEstado);
        pnlCambioEstado.add(filler1);

        rbtnTramite.setSelected(true);
        rbtnTramite.setText("EN TRÁMITE");
        pnlCambioEstado.add(rbtnTramite);
        pnlCambioEstado.add(filler2);

        rbtnFinalizado.setText("FINALIZADO");
        pnlCambioEstado.add(rbtnFinalizado);

        jPanel4.add(pnlCambioEstado, java.awt.BorderLayout.PAGE_START);

        pnlEnvioDepartamento.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 3));

        labEnvioDepartamento.setText("Enviar a Departamento: ");
        pnlEnvioDepartamento.add(labEnvioDepartamento);
        pnlEnvioDepartamento.add(filler3);

        cbxDepartamentoSol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Atención al Cliente", "Calidad", "Ventas", "Gerencia", "Contabilidad y Finanzas" }));
        pnlEnvioDepartamento.add(cbxDepartamentoSol);

        jPanel4.add(pnlEnvioDepartamento, java.awt.BorderLayout.CENTER);

        pnlAgregarDescipcion.setLayout(new java.awt.BorderLayout());

        labAgregarDescripcion.setText("Agregar Descripción:");
        pnlAgregarDescipcion.add(labAgregarDescripcion, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        txaDescripcionEvaluacion.setColumns(20);
        txaDescripcionEvaluacion.setRows(5);
        jScrollPane3.setViewportView(txaDescripcionEvaluacion);

        jPanel5.add(jScrollPane3);

        pnlAgregarDescipcion.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.add(pnlAgregarDescipcion, java.awt.BorderLayout.PAGE_END);

        pnlNuevaEval.add(jPanel4, java.awt.BorderLayout.CENTER);

        btnEvaluarSol.setText("Realizar Evaluacion");
        pnlBotones.add(btnEvaluarSol);

        btnLimpiarSol.setText("Limpiar");
        pnlBotones.add(btnLimpiarSol);

        btnSalirSol.setText("Salir");
        pnlBotones.add(btnSalirSol);

        pnlNuevaEval.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(pnlNuevaEval, java.awt.BorderLayout.PAGE_END);

        pnlSeguimiento.add(jPanel2, "card2");

        jpBottom.add(pnlSeguimiento, java.awt.BorderLayout.PAGE_END);

        jpSolicitudes.add(jpBottom, java.awt.BorderLayout.PAGE_END);

        jScrollPane4.setViewportView(jpSolicitudes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSolicitudesMouseClicked

    }//GEN-LAST:event_tbSolicitudesMouseClicked

    private void tbEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEvaluacionesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEvaluacionesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarListaSol;
    public javax.swing.JButton btnEvaluarSol;
    public javax.swing.JButton btnLimpiarSol;
    public javax.swing.JButton btnSalirSol;
    public javax.swing.JComboBox<String> cbxDepartamentoSol;
    public javax.swing.JComboBox<String> cbxFiltrarEstadoSol;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpBody;
    private javax.swing.JPanel jpBottom;
    private javax.swing.JPanel jpHead;
    private javax.swing.JPanel jpSolicitudes;
    private javax.swing.JLabel labAgregarDescripcion;
    private javax.swing.JLabel labCambioEstado;
    private javax.swing.JLabel labEnvioDepartamento;
    private javax.swing.JLabel labFiltroEstado;
    private javax.swing.JLabel labInfo1;
    private javax.swing.JLabel labInfo2;
    private javax.swing.JLabel labSubTitulo1;
    private javax.swing.JLabel labSubTitulo2;
    private javax.swing.JLabel labSubTitulo3;
    private javax.swing.JLabel labSubTitulo4;
    private javax.swing.JLabel labTitulo;
    private javax.swing.JPanel pnlAgregarDescipcion;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCambioEstado;
    private javax.swing.JPanel pnlDetalle1;
    private javax.swing.JPanel pnlEnvioDepartamento;
    private javax.swing.JPanel pnlFiltroEstado;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlInfoEval;
    private javax.swing.JPanel pnlNuevaEval;
    private javax.swing.JPanel pnlSeguimiento;
    private javax.swing.JPanel pnlTablaE;
    private javax.swing.JPanel pnlTablaS;
    public javax.swing.JRadioButton rbtnFinalizado;
    public javax.swing.JRadioButton rbtnTramite;
    private javax.swing.JScrollPane scrlTablaEvaluaciones;
    private javax.swing.JScrollPane scrlTablaSolicitudes;
    public javax.swing.JTable tbEvaluaciones;
    public javax.swing.JTable tbSolicitudes;
    public javax.swing.JTextArea txaDescripcionEvaluacion;
    public javax.swing.JTextArea txaDetalleEvaluacion;
    public javax.swing.JTextArea txaDetalleSolicitud;
    // End of variables declaration//GEN-END:variables
}
