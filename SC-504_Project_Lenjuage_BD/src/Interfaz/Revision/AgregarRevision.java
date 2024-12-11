/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Revision;

import ConectionDB.ConectionDB;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import popups.Popups;
import java.sql.*;
import javax.swing.JComponent;

/**
 * @author ROURY
 */
public class AgregarRevision extends javax.swing.JFrame {

    public static String Crud_;
    public static boolean isEdit_;
    public static String textoBoton_;
    public static String ID_aEditar_;
    private VistaRevision agregarRevision;

    Popups popups = new Popups();
    String idMecanico;
    String idBus;

    public AgregarRevision() {
        initComponents();
        jDateChooser_Fin.getDateEditor().getUiComponent().setEnabled(false);
        for (Component c : jDateChooser_Fin.getComponents()) {
            ((JComponent) c).setBackground(Color.white);
        }

        jDateChooser_inicio.getDateEditor().getUiComponent().setEnabled(false);
        for (Component c : jDateChooser_inicio.getComponents()) {
            ((JComponent) c).setBackground(Color.white);
        }

        cargarMecanicos();
        cargarBuses();

        BusesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Bus_Seleccionado = BusesList.getSelectedItem().toString();

                if (!Bus_Seleccionado.equals("-")) {
                    idBus = Bus_Seleccionado.split(" - ")[0];
                } else {
                }
            }
        });

        MecanicosList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Mecanico_Seleccionado = MecanicosList.getSelectedItem().toString();

                if (!Mecanico_Seleccionado.equals("-")) {
                    idMecanico = Mecanico_Seleccionado.split(" - ")[0];
                } else {
                }
            }
        });
    }

    //esto es un set no quitar el la palabra set antes del nombre del metodo
    public void setVistaMantenimiento(VistaRevision agregarRevision) {
        this.agregarRevision = agregarRevision;
    }

    public void actualizarTabla() {
        if (agregarRevision != null) {
            agregarRevision.cargarDatos();
        }
    }

    public void setDatosEdicion(Date inicio, Date fin, String duracion, String mecanico, String placa) {
        jDateChooser_inicio.setDate(inicio);
        jDateChooser_Fin.setDate(fin);
        Duracion.setText(duracion);

        for (int i = 0; i < MecanicosList.getItemCount(); i++) {
            String item = (String) MecanicosList.getItemAt(i);
            if (item.contains(mecanico)) {
                MecanicosList.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < BusesList.getItemCount(); i++) {
            String item = (String) BusesList.getItemAt(i);
            if (item.contains(placa)) {
                BusesList.setSelectedIndex(i);
                break;
            }
        }

        jDateChooser_inicio.getDateEditor().getUiComponent().setEnabled(true);
        jDateChooser_inicio.setEnabled(false);
        jDateChooser_Fin.getDateEditor().getUiComponent().setEnabled(true);
        jDateChooser_Fin.setEnabled(false);
        Duracion.setEditable(false);
        MecanicosList.setEnabled(false);
        BusesList.setEnabled(false);
    }

    private void cargarBuses() {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            ConectionDB conexionDB = new ConectionDB();
            conn = conexionDB.getConnection();

            String sql = "{call Obtener_Lista_Buses(?)}";
            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);

            BusesList.removeAllItems();

            while (rs.next()) {
                int idBus = rs.getInt("nPlaca");
                BusesList.addItem(idBus + " - Activo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarMecanicos() {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            ConectionDB conexionDB = new ConectionDB();
            conn = conexionDB.getConnection();

            String sql = "{call Obtener_Lista_Mantenimientos(?)}";
            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);

            MecanicosList.removeAllItems();

            /*
            Ojo con los trim, son muy necesarios, no tocar ni modificar
             */
            while (rs.next()) {
                int idChofer = rs.getInt("ID_Mantenimiento");
                String nombreCompleto = rs.getString("sNombre_Completo");
                MecanicosList.addItem(idChofer + " - " + nombreCompleto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Cerrar = new javax.swing.JButton();
        Registrar = new javax.swing.JButton();
        warning = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jDateChooser_inicio = new com.toedter.calendar.JDateChooser();
        jDateChooser_Fin = new com.toedter.calendar.JDateChooser();
        Duracion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        MecanicosList = new javax.swing.JComboBox<>();
        BusesList = new javax.swing.JComboBox<>();
        Error_fecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), Crud_ + " de Revisión", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 18), new java.awt.Color(204, 0, 51))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Cerrar.setBackground(new java.awt.Color(153, 0, 0));
        Cerrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Cerrar.setForeground(new java.awt.Color(255, 255, 255));
        Cerrar.setText("Cerrar");
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });

        Registrar.setBackground(new java.awt.Color(0, 102, 255));
        Registrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Registrar.setForeground(new java.awt.Color(255, 255, 255));
        Registrar.setText(textoBoton_);
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });

        warning.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        warning.setForeground(new java.awt.Color(255, 51, 51));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("* Campos Obligatorios");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Por favor llene toda la información necesaria");

        jPanel2.setBackground(new java.awt.Color(162, 171, 205));

        jDateChooser_inicio.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser_inicio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Fecha Inicio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jDateChooser_inicio.setForeground(new java.awt.Color(0, 0, 0));

        jDateChooser_Fin.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser_Fin.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Fecha Fin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jDateChooser_Fin.setForeground(new java.awt.Color(0, 0, 0));

        Duracion.setBackground(new java.awt.Color(255, 255, 255));
        Duracion.setForeground(new java.awt.Color(0, 0, 0));
        Duracion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* Duración", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        Duracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DuracionActionPerformed(evt);
            }
        });
        Duracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DuracionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser_Fin, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Duracion, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser_Fin, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Duracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(162, 171, 205));

        MecanicosList.setBackground(new java.awt.Color(255, 255, 255));
        MecanicosList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        MecanicosList.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Mecánicos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        MecanicosList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BusesList.setBackground(new java.awt.Color(255, 255, 255));
        BusesList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        BusesList.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Buses", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        BusesList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MecanicosList, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BusesList, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MecanicosList, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(BusesList, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Error_fecha.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Error_fecha.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(warning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Error_fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(170, 170, 170))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Error_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Registrar)
                    .addComponent(Cerrar))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
        if (isEdit_) {
            Connection conn = null;
            CallableStatement stmt = null;

            try {
                ConectionDB conexionDB = new ConectionDB();
                conn = conexionDB.getConnection();

                String sql = "{CALL Procedure_cambiar_estado_bus(?, ?)}";
                stmt = conn.prepareCall(sql);

                stmt.setInt(1, Integer.parseInt(ID_aEditar_));
                stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

                stmt.execute();

                String message = stmt.getString(2);
                if (message.equals("El bus ya está activo")) {
                    popups.error(message);
                } else {
                    popups.pulgar_arriba(message);
                }

                actualizarTabla();
                this.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
                popups.error("Error al completar la revisión.");
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            String duracion = Duracion.getText();

            if (jDateChooser_inicio.getDate() == null) {
                Error_fecha.setText("ATENCIÓN: Por favor, seleccione la fecha de contratación.");
                return;
            }
            java.sql.Date fecha_Inicio = new java.sql.Date(jDateChooser_inicio.getDate().getTime());

            if (jDateChooser_Fin.getDate() == null) {
                Error_fecha.setText("ATENCIÓN: Por favor, seleccione la fecha de contratación.");
                return;
            }
            java.sql.Date fecha_Fin = new java.sql.Date(jDateChooser_Fin.getDate().getTime());

            Color colorError = new Color(255, 204, 204);

            if (duracion.isEmpty()) {

                if (duracion.isEmpty()) {
                    Duracion.setBackground(colorError);
                } else {
                    Duracion.setBackground(Color.WHITE);
                }
                warning.setText("ATENCIÓN: Por favor, complete todos los campos.");
                return;
            }

            Connection conn = null;
            CallableStatement stmt = null;

            try {
                ConectionDB conexionDB = new ConectionDB();
                conn = conexionDB.getConnection();

                String sql = "{CALL Procedure_crear_revision(?, ?, ?, ?, ?)}";
                stmt = conn.prepareCall(sql);

                stmt.setInt(1, Integer.parseInt(idBus));
                stmt.setInt(2, Integer.parseInt(idMecanico));
                stmt.setDate(3, fecha_Inicio);
                stmt.setDate(4, fecha_Fin);
                stmt.setInt(5, Integer.parseInt(duracion));

                stmt.executeUpdate();
                popups.pulgar_arriba("Revisión registrada exitosamente.");
                actualizarTabla();
                this.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
                popups.error("Error al registrar Revisión.");
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_RegistrarActionPerformed

    private void DuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DuracionActionPerformed

    private void DuracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DuracionKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_DuracionKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AgregarRevision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarRevision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarRevision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarRevision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarRevision().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BusesList;
    private javax.swing.JButton Cerrar;
    private javax.swing.JTextField Duracion;
    private javax.swing.JLabel Error_fecha;
    private javax.swing.JComboBox<String> MecanicosList;
    private javax.swing.JButton Registrar;
    private com.toedter.calendar.JDateChooser jDateChooser_Fin;
    private com.toedter.calendar.JDateChooser jDateChooser_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
