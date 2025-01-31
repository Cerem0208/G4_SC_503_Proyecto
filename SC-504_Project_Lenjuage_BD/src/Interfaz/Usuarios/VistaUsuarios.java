/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz.Usuarios;

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
public class VistaUsuarios extends javax.swing.JPanel {

    Popups popups = new Popups();
    private AgregarUsuarios agregarUsuario;

    /**
     * Creates new form VistaUsuarios2
     */
    public VistaUsuarios() {
        initComponents();
        cargarDatos();
        Ajustar_Tamano_Columnas(TableUsuarios);

        Rol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });

    }

    public void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) TableUsuarios.getModel();
        model.setRowCount(0);

        String textoBusqueda = Busqueda_nombre.getText().trim();
        String salarioBusqueda = Busqueda_salario.getText().trim();
        String rol = Rol.getSelectedItem().toString();

        ConectionDB conexionDB = new ConectionDB();
        try (Connection conn = conexionDB.getConnection(); CallableStatement stmt = conn.prepareCall("{call PROCEDURE_VISTA_USUARIOS(?, ?, ?, ?)}")) {

            if (textoBusqueda.isEmpty()) {
                stmt.setNull(1, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(1, textoBusqueda);
            }

            if (salarioBusqueda.isEmpty()) {
                stmt.setNull(2, java.sql.Types.NUMERIC);
            } else {
                stmt.setDouble(2, Double.parseDouble(salarioBusqueda));
            }

            if (rol.isEmpty() || rol.equals("Selec. Rol")) {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(3, rol);
            }

            stmt.registerOutParameter(4, java.sql.Types.REF_CURSOR);

            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(4)) {
                while (rs.next()) {
                    Object[] fila = new Object[10];
                    fila[0] = rs.getInt("ID_Persona");
                    fila[1] = rs.getString("sCedula");
                    fila[2] = rs.getString("Fullname");
                    fila[3] = rs.getString("Telefono");

                    fila[4] = rs.getInt("nSalario");
                    /*se debe agregar el simbolo de coloners al igual que el bono, el problema es que al cargar el model de edicion el trim para quitar el simbolo no funciona
                    pendiente de solucion, por el momento esta sin simbolo*/
                    String bonoBeneficio = rs.getString("NombreBeneficio");
                    if (bonoBeneficio == null) {
                        bonoBeneficio = "Sin beneficio";
                    }
                    fila[5] = bonoBeneficio + " (" + "₡" + rs.getString("Bono") + ")";
                    fila[6] = "₡" + rs.getString("Salario_Total");
                    fila[7] = rs.getString("Direccion");
                    fila[8] = rs.getDate("Fecha_Contrato");

                    fila[9] = rs.getString("Rol");

                    model.addRow(fila);
                }

                for (int i = 0; i < TableUsuarios.getColumnCount(); i++) {
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    TableUsuarios.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
        TableUsuarios = new javax.swing.JTable();
        Regresar = new javax.swing.JButton();
        Agregar_usuarios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        FilerPanel = new javax.swing.JPanel();
        Busqueda_nombre = new javax.swing.JTextField();
        Busqueda_salario = new javax.swing.JTextField();
        Rol = new javax.swing.JComboBox<>();
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

        jPanel1.setBackground(new java.awt.Color(162, 171, 205));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 2, 36), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        TableUsuarios.setAutoCreateRowSorter(true);
        TableUsuarios.setBackground(new java.awt.Color(102, 102, 102));
        TableUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        TableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CEDULA", "NOMBRE", "TELÉFONO", "SALARIO", "BONO", "NETO", "DIRECCIÓN", "CONTR.", "ROL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableUsuarios.setColumnSelectionAllowed(true);
        TableUsuarios.setComponentPopupMenu(jPopup);
        TableUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TableUsuarios.setSelectionBackground(new java.awt.Color(153, 204, 0));
        TableUsuarios.setSelectionForeground(new java.awt.Color(0, 0, 0));
        TableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableUsuarios);
        TableUsuarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        Regresar.setBackground(new java.awt.Color(162, 171, 205));
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

        Agregar_usuarios.setBackground(new java.awt.Color(162, 171, 205));
        Agregar_usuarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Agregar_usuarios.setForeground(new java.awt.Color(0, 0, 0));
        Agregar_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        Agregar_usuarios.setText("Agregar Usuarios ");
        Agregar_usuarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 153, 0), new java.awt.Color(51, 153, 0), null, null));
        Agregar_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Agregar_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_usuariosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Administración de Usuarios");

        FilerPanel.setBackground(new java.awt.Color(255, 204, 204));
        FilerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        FilerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Busqueda_nombre.setBackground(new java.awt.Color(255, 204, 204));
        Busqueda_nombre.setForeground(new java.awt.Color(0, 0, 0));
        Busqueda_nombre.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Nombre", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        Busqueda_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Busqueda_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Busqueda_nombreActionPerformed(evt);
            }
        });
        FilerPanel.add(Busqueda_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 0, 203, -1));

        Busqueda_salario.setBackground(new java.awt.Color(255, 204, 204));
        Busqueda_salario.setForeground(new java.awt.Color(0, 0, 0));
        Busqueda_salario.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Salario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        Busqueda_salario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Busqueda_salario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Busqueda_salarioActionPerformed(evt);
            }
        });
        FilerPanel.add(Busqueda_salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 0, 149, -1));

        Rol.setBackground(new java.awt.Color(255, 204, 204));
        Rol.setForeground(new java.awt.Color(0, 0, 0));
        Rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selec. Rol", "Chofer", "Gerente", "Mantenimiento" }));
        Rol.setBorder(null);
        Rol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Rol.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        Rol.setMinimumSize(new java.awt.Dimension(11, 42));
        Rol.setOpaque(false);
        Rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RolActionPerformed(evt);
            }
        });
        FilerPanel.add(Rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 6, 167, 30));

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
        FilerPanel.add(Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 48));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FilerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(Agregar_usuarios)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(353, 353, 353))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(391, Short.MAX_VALUE)
                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(391, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Agregar_usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void TableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuariosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int x = evt.getX();
            int y = evt.getY();

            int row = TableUsuarios.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                TableUsuarios.setRowSelectionInterval(row, row);
            }
            jPopup.show(evt.getComponent(), x, y);
        }
    }//GEN-LAST:event_TableUsuariosMouseClicked

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        this.setVisible(false);
        BG_inicio pagina = new BG_inicio();
        pagina.setSize(916, 558);
        pagina.setLocation(0, 0);
        Menu.Contenido.removeAll();
        Menu.Contenido.add(pagina, BorderLayout.CENTER);
        Menu.Contenido.revalidate();
        Menu.Contenido.repaint();
    }//GEN-LAST:event_RegresarActionPerformed

    private void Agregar_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_usuariosActionPerformed
        agregarUsuario.Crud_ = "Creación";
        agregarUsuario.textoBoton_ = "Crear";
        agregarUsuario.isEdit_ = false;
        agregarUsuario = new AgregarUsuarios();
        agregarUsuario.setVisible(true);
        agregarUsuario.setVistaUsuarios2(this);
    }//GEN-LAST:event_Agregar_usuariosActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int filaSeleccionada = TableUsuarios.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idPersona = (int) TableUsuarios.getValueAt(filaSeleccionada, 0);
            String cedula = (String) TableUsuarios.getValueAt(filaSeleccionada, 1);
            String nombreCompleto = (String) TableUsuarios.getValueAt(filaSeleccionada, 2);

            String[] nombreArray = nombreCompleto.split(" ");
            String nombre = nombreArray.length > 0 ? nombreArray[0].trim() : "";
            String apellido1 = nombreArray.length > 1 ? nombreArray[1].trim() : "";
            String apellido2 = nombreArray.length > 2 ? nombreArray[2].trim() : "";

            String telefono = (String) TableUsuarios.getValueAt(filaSeleccionada, 3);

            Integer salarioInteger = (Integer) TableUsuarios.getValueAt(filaSeleccionada, 4);
            int salario = salarioInteger != null ? salarioInteger : 0;
            String bono = (String) TableUsuarios.getValueAt(filaSeleccionada, 5);

            String direccion = (String) TableUsuarios.getValueAt(filaSeleccionada, 7);

            String[] direccionArray = direccion.split(",");
            String provincia = direccionArray.length > 0 ? direccionArray[0].trim() : "";
            String canton = direccionArray.length > 1 ? direccionArray[1].trim() : "";
            String distrito = direccionArray.length > 2 ? direccionArray[2].trim() : "";

            Date contratacion = (Date) TableUsuarios.getValueAt(filaSeleccionada, 8);
            String rol = (String) TableUsuarios.getValueAt(filaSeleccionada, 9);

            //agregarUsuario.setParametros(true, "Actualizar", "Edición"); 
            agregarUsuario.Crud_ = "Edición";
            agregarUsuario.textoBoton_ = "Actualizar";
            agregarUsuario.isEdit_ = true;
            agregarUsuario.ID_aEditar_ = idPersona;
            agregarUsuario = new AgregarUsuarios();
            agregarUsuario.setVistaUsuarios2(this);
            agregarUsuario.setDatosEdicion(nombre, telefono, apellido1, apellido2, cedula, salario, provincia, canton, distrito, rol, bono, contratacion);
            agregarUsuario.setVisible(true);
        } else {
            popups.error("Por favor, seleccione un registro para editar.");
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int filaSeleccionada = TableUsuarios.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idPersona = (int) TableUsuarios.getValueAt(filaSeleccionada, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                Connection conn = null;
                CallableStatement stmt = null;

                try {
                    ConectionDB conexionDB = new ConectionDB();
                    conn = conexionDB.getConnection();

                    conn.setAutoCommit(false);

                    String sqlEliminarPersona = "{call Procedure_EliminarPersona(?)}";
                    stmt = conn.prepareCall(sqlEliminarPersona);
                    stmt.setInt(1, idPersona);

                    stmt.executeUpdate();

                    conn.commit();
                    popups.pulgar_arriba("Registro eliminado correctamente.");
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
                    popups.error("Error al eliminar el registro.");
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
        Busqueda_nombre.setText("");
        Busqueda_salario.setText("");
        Rol.setSelectedItem("Selec. Rol");
        cargarDatos();
    }//GEN-LAST:event_ResetActionPerformed

    private void RolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RolActionPerformed

    private void Busqueda_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda_nombreActionPerformed
        cargarDatos();
    }//GEN-LAST:event_Busqueda_nombreActionPerformed

    private void Busqueda_salarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda_salarioActionPerformed
        cargarDatos();
    }//GEN-LAST:event_Busqueda_salarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar_usuarios;
    private javax.swing.JTextField Busqueda_nombre;
    private javax.swing.JTextField Busqueda_salario;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JPanel FilerPanel;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Reset;
    private javax.swing.JComboBox<String> Rol;
    private javax.swing.JTable TableUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
