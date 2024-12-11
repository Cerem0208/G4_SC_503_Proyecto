/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz.Rutas;

import ConectionDB.ConectionDB;
import Interfaz.BG_inicio;
import Interfaz.Menu;
import java.awt.*;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.sql.*;
import popups.Popups;

/**
 *
 * @author RouryR
 */
public class VistaRutas extends javax.swing.JPanel {

    Popups popups = new Popups();
    private AgregarRutas agregarRutas;

    public VistaRutas() {
        initComponents();
        cargarDatos();
        Ajustar_Tamano_Columnas(TableRutas);

        RutaFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });

    }

    public void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) TableRutas.getModel();
        model.setRowCount(0);

        String tipoRuta = RutaFilter.getSelectedItem().toString();
        ConectionDB conexionDB = new ConectionDB();

        try (Connection conn = conexionDB.getConnection(); CallableStatement stmt = conn.prepareCall("{call Procedure_Lista_Rutas(?, ?)}")) {

            if (tipoRuta.isEmpty() || tipoRuta.equals("Todas las rutas")) {
                stmt.setNull(1, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(1, tipoRuta);
            }

            stmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                while (rs.next()) {
                    Object[] fila = new Object[8];

                    fila[0] = rs.getString("Tipo_Ruta");

                    String paisDestino = rs.getString("Pais_Destino");
                    if (paisDestino == null) {
                        paisDestino = "Costa Rica";
                    }
                    fila[1] = paisDestino;

                    fila[2] = rs.getInt("ID_Ruta");
                    fila[3] = rs.getString("sInicio_Ruta");
                    fila[4] = rs.getString("sFin_Ruta");
                    fila[5] = "₡" + rs.getDouble("nCosto");
                    fila[6] = rs.getString("sDuracion") + " Min.";
                    String Chofer_asigando = rs.getString("Nombre_Chofer");
                    if (Chofer_asigando == null) {
                        Chofer_asigando = "Sin chofer";
                    }
                    fila[7] = Chofer_asigando;

                    model.addRow(fila);
                }

                for (int i = 0; i < TableRutas.getColumnCount(); i++) {
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    TableRutas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Ajustar_Tamano_Columnas(javax.swing.JTable tabla) {
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn column = tabla.getColumnModel().getColumn(i);
            int Ancho_Max = 0;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer celdaRenderer = tabla.getCellRenderer(row, i);
                Object value = tabla.getValueAt(row, i);
                Component celda = celdaRenderer.getTableCellRendererComponent(tabla, value, false, false, row, i);
                Ancho_Max = Math.max(Ancho_Max, celda.getPreferredSize().width);
            }
            column.setPreferredWidth(Ancho_Max);
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

        jPopup = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRutas = new javax.swing.JTable();
        Regresar = new javax.swing.JButton();
        Agregar_rutas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        FilerPanel = new javax.swing.JPanel();
        RutaFilter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Reset = new javax.swing.JButton();

        Editar.setBackground(new java.awt.Color(255, 255, 255));
        Editar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Editar.setForeground(new java.awt.Color(0, 102, 204));
        Editar.setText("Editar");
        Editar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 153), new java.awt.Color(0, 102, 153), null, null));
        Editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPopup.add(Editar);

        Eliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Eliminar.setForeground(new java.awt.Color(204, 0, 51));
        Eliminar.setText("Eliminar");
        Eliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 51), new java.awt.Color(204, 0, 51), null, null));
        Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopup.add(Eliminar);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Rutas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 2, 36), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TableRutas.setAutoCreateRowSorter(true);
        TableRutas.setBackground(new java.awt.Color(102, 102, 102));
        TableRutas.setForeground(new java.awt.Color(255, 255, 255));
        TableRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo Ruta", "Pais", "ID", "SALIDA", "LLEGADA", "COSTO", "DURACIÓN", "CHOFER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableRutas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableRutas.setColumnSelectionAllowed(true);
        TableRutas.setComponentPopupMenu(jPopup);
        TableRutas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TableRutas.setSelectionBackground(new java.awt.Color(153, 204, 0));
        TableRutas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TableRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRutasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableRutas);
        TableRutas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        Regresar.setBackground(new java.awt.Color(204, 255, 204));
        Regresar.setForeground(new java.awt.Color(0, 0, 0));
        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/atras.png"))); // NOI18N
        Regresar.setText("REGRESAR");
        Regresar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 51, 51), new java.awt.Color(255, 51, 51), null, null));
        Regresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Agregar_rutas.setBackground(new java.awt.Color(204, 255, 204));
        Agregar_rutas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Agregar_rutas.setForeground(new java.awt.Color(0, 0, 0));
        Agregar_rutas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-ruta-32.png"))); // NOI18N
        Agregar_rutas.setText("Agregar Ruta ");
        Agregar_rutas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 153, 0), new java.awt.Color(51, 153, 0), null, null));
        Agregar_rutas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Agregar_rutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_rutasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Administración de Rutas");

        FilerPanel.setBackground(new java.awt.Color(255, 204, 204));
        FilerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        FilerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RutaFilter.setBackground(new java.awt.Color(255, 204, 204));
        RutaFilter.setForeground(new java.awt.Color(0, 0, 0));
        RutaFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las rutas", "Interna", "Externa", "Internacional" }));
        RutaFilter.setBorder(null);
        RutaFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RutaFilter.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        RutaFilter.setMinimumSize(new java.awt.Dimension(11, 42));
        RutaFilter.setOpaque(false);
        RutaFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RutaFilterActionPerformed(evt);
            }
        });
        FilerPanel.add(RutaFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 167, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-filter-35.png"))); // NOI18N
        FilerPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, 48));

        Reset.setBackground(new java.awt.Color(204, 204, 204));
        Reset.setForeground(new java.awt.Color(0, 0, 0));
        Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-reset-35.png"))); // NOI18N
        Reset.setBorder(null);
        Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Reset.setOpaque(false);
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        FilerPanel.add(Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, 48));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FilerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Agregar_rutas)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(370, Short.MAX_VALUE)
                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(369, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Agregar_rutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FilerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(459, Short.MAX_VALUE)
                    .addComponent(Regresar)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TableRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRutasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int x = evt.getX();
            int y = evt.getY();

            int row = TableRutas.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                TableRutas.setRowSelectionInterval(row, row);
            }
            jPopup.show(evt.getComponent(), x, y);
        }
    }//GEN-LAST:event_TableRutasMouseClicked

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        this.setVisible(false);
        BG_inicio pagina = new BG_inicio();
        pagina.setSize(916, 5581);
        pagina.setLocation(0, 0);
        Menu.Contenido.removeAll();
        Menu.Contenido.add(pagina, BorderLayout.CENTER);
        Menu.Contenido.revalidate();
        Menu.Contenido.repaint();
    }//GEN-LAST:event_RegresarActionPerformed

    private void Agregar_rutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_rutasActionPerformed
        agregarRutas.Crud_ = "Creación";
        agregarRutas.textoBoton_ = "Crear";
        agregarRutas.isEdit_ = false;
        agregarRutas = new AgregarRutas();
        agregarRutas.setVisible(true);
        agregarRutas.setVistaRutas(this);
    }//GEN-LAST:event_Agregar_rutasActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int filaSeleccionada = TableRutas.getSelectedRow();

        if (filaSeleccionada != -1) {
            String Tipo_Ruta = (String) TableRutas.getValueAt(filaSeleccionada, 0);
            String Pais = (String) TableRutas.getValueAt(filaSeleccionada, 1);
            int ID_Ruta = (int) TableRutas.getValueAt(filaSeleccionada, 2); //OJO si se copia este codigo se debe modificar el valor de la columna 2 ya que esta como intenger y causa error si no se cambia

            String Salida = (String) TableRutas.getValueAt(filaSeleccionada, 3);
            String Llegada = (String) TableRutas.getValueAt(filaSeleccionada, 4);

            String Costo = String.valueOf(TableRutas.getValueAt(filaSeleccionada, 5));
            String Duracion = String.valueOf(TableRutas.getValueAt(filaSeleccionada, 6));

            String Chofer_asignado = (String) TableRutas.getValueAt(filaSeleccionada, 7);
            System.out.println(Chofer_asignado);

///verificacion necesaaria, se tuvo que aumentar el cache aqui, FYI
            boolean tieneChofer = !("Sin chofer".equalsIgnoreCase(Chofer_asignado));

            agregarRutas.Crud_ = "Edición";
            agregarRutas.textoBoton_ = "Actualizar";
            agregarRutas.isEdit_ = true;
            agregarRutas.ID_aEditar_ = ID_Ruta;

           
            agregarRutas = new AgregarRutas();
            

            agregarRutas.setVistaRutas(this);
            agregarRutas.setDatosEdicion(Chofer_asignado, Costo, Duracion, Llegada, Salida, Pais, Tipo_Ruta, tieneChofer);
            agregarRutas.setVisible(true);
        } else {
            popups.error("Por favor, seleccione un registro para editar.");
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int filaSeleccionada = TableRutas.getSelectedRow();

        if (filaSeleccionada != -1) {
            String Tipo_Ruta = (String) TableRutas.getValueAt(filaSeleccionada, 0);
            int ID_Ruta = (int) TableRutas.getValueAt(filaSeleccionada, 2);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar la ruta?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                Connection conn = null;
                CallableStatement stmt = null;

                try {
                    ConectionDB conexionDB = new ConectionDB();
                    conn = conexionDB.getConnection();

                    conn.setAutoCommit(false);

                    String sqlEliminarPersona = "{call Procedure_Eliminar_Ruta(?,?)}";
                    stmt = conn.prepareCall(sqlEliminarPersona);
                    stmt.setString(1, Tipo_Ruta);
                    stmt.setInt(2, ID_Ruta);

                    stmt.executeUpdate();

                    conn.commit();
                    popups.pulgar_arriba("Ruta eliminado correctamente.");
                    cargarDatos();

                } catch (SQLException e) {
                    try {
                        if (conn != null) {
                            conn.rollback();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                    popups.error("Error al eliminar la ruta.");
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
        } else {
            popups.error("Por favor, seleccione un registro para eliminar.");
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        RutaFilter.setSelectedItem("Todas las rutas");
        cargarDatos();
    }//GEN-LAST:event_ResetActionPerformed

    private void RutaFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RutaFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RutaFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar_rutas;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JPanel FilerPanel;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Reset;
    private javax.swing.JComboBox<String> RutaFilter;
    private javax.swing.JTable TableRutas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
