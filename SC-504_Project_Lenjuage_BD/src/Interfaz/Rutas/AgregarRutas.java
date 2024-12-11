/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Rutas;

import ConectionDB.ConectionDB;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import popups.Popups;
import java.sql.*;

/**
 * @author ROURY
 */
public class AgregarRutas extends javax.swing.JFrame {

    public static String Crud_;
    public static boolean isEdit_;
    public static String textoBoton_;
    public static int ID_aEditar_;
    private VistaRutas VistaRutas;
    public String pais_ruta;
    String idChofer;
    Popups popups = new Popups();

    public AgregarRutas() {
        initComponents();
        cargarChoferes();
        Pais.setEnabled(false);
        Chofer.setEnabled(false);

        toggleChofer.setSelected(false);
        toggleChofer.setText("NO");
        toggleChofer.setBackground(Color.RED);

        Chofer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choferSeleccionado = Chofer.getSelectedItem().toString();
                System.out.println(choferSeleccionado);
                if (!choferSeleccionado.equals("-")) {
                    idChofer = choferSeleccionado.split(" - ")[0];

                } else {
                }
            }
        });

        toggleChofer.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                toggleChofer.setText("SI");
                toggleChofer.setBackground(new Color(51, 204, 0));
                Chofer.setEnabled(true);
            } else {
                toggleChofer.setText("NO");
                toggleChofer.setBackground(Color.RED);
                Chofer.setEnabled(false);
            }
        });

        TipoRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rutaSeleccionado = TipoRuta.getSelectedItem().toString();

                if (!rutaSeleccionado.equals("Internacional")) {
                    Pais.setEnabled(false);
                    Pais.setSelectedItem("Selec. Pais");
                } else {
                    Pais.setEnabled(true);
                    //Pais.setSelectedItem(pais_ruta);
                    Pais.setSelectedItem("Selec. Pais");
                }
            }
        });
    }

    //esto es un set no quitar el la palabra set antes del nombre del metodo
    public void setVistaRutas(VistaRutas vistaRutas) {
        this.VistaRutas = vistaRutas;
    }

    public void actualizarTabla() {
        if (VistaRutas != null) {
            VistaRutas.cargarDatos();
        }
    }

    public void limpiarCampos() {
        InicioRuta.setText("");
        FinRuta.setText("");
        CostoRuta.setText("");
        TipoRuta.setSelectedIndex(0);
        Duracion.setText("");
    }

    public void setDatosEdicion(String chofer, String costoRuta, String duracion, String finRuta, String inicioRuta, String pais, String tipoRuta, boolean toggleChofer_l) {
        toggleChofer.setSelected(toggleChofer_l);
        Chofer.setSelectedItem(chofer);
        CostoRuta.setText(String.valueOf(costoRuta));
        Duracion.setText(String.valueOf(duracion));
        FinRuta.setText(finRuta);
        InicioRuta.setText(inicioRuta);
        Pais.setSelectedItem(pais);
        pais_ruta = pais;
        TipoRuta.setSelectedItem(tipoRuta);

        System.out.println("depur " + pais);

        for (int i = 0; i < Chofer.getItemCount(); i++) {
            String item = (String) Chofer.getItemAt(i);
            if (item.contains(chofer)) {
                Chofer.setSelectedIndex(i);
                break;
            }
        }
        System.out.println(idChofer);

        for (ActionListener listener : Chofer.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(Chofer, ActionEvent.ACTION_PERFORMED, null));
        }

        for (ActionListener listener : toggleChofer.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(toggleChofer, ActionEvent.ACTION_PERFORMED, null));
        }

        for (ActionListener listener : TipoRuta.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(TipoRuta, ActionEvent.ACTION_PERFORMED, null));
        }

        for (ActionListener listener : Pais.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(Pais, ActionEvent.ACTION_PERFORMED, null));
        }

    }

    //se tuvo que aumentar el cache aqui, FYI
    private void cargarChoferes() {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            ConectionDB conexionDB = new ConectionDB();
            conn = conexionDB.getConnection();

            String sql = "{call Obtener_Lista_Choferes(?)}";
            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);

            Chofer.removeAllItems();

            while (rs.next()) {
                int idChofer = rs.getInt("ID_Chofer");
                String nombreCompleto = rs.getString("sNombre_Completo");
                Chofer.addItem(idChofer + " - " + nombreCompleto);
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
        InicioRuta = new javax.swing.JTextField();
        FinRuta = new javax.swing.JTextField();
        CostoRuta = new javax.swing.JTextField();
        TipoRuta = new javax.swing.JComboBox<>();
        Duracion = new javax.swing.JTextField();
        Chofer = new javax.swing.JComboBox<>();
        Pais = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        toggleChofer = new javax.swing.JToggleButton();
        Error_fecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), Crud_ + " de Rutas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 18), new java.awt.Color(204, 0, 51))); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setAutoscrolls(true);
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        InicioRuta.setBackground(new java.awt.Color(255, 255, 255));
        InicioRuta.setForeground(new java.awt.Color(0, 0, 0));
        InicioRuta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* Donde inicia la Ruta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        InicioRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        InicioRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioRutaActionPerformed(evt);
            }
        });

        FinRuta.setBackground(new java.awt.Color(255, 255, 255));
        FinRuta.setForeground(new java.awt.Color(0, 0, 0));
        FinRuta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* Donde finaliza la ruta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        FinRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinRutaActionPerformed(evt);
            }
        });

        CostoRuta.setBackground(new java.awt.Color(255, 255, 255));
        CostoRuta.setForeground(new java.awt.Color(0, 0, 0));
        CostoRuta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* Costo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        CostoRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CostoRutaActionPerformed(evt);
            }
        });
        CostoRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoRutaKeyTyped(evt);
            }
        });

        TipoRuta.setBackground(new java.awt.Color(255, 255, 255));
        TipoRuta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selec. Ruta", "Interna", "Externa", "Internacional" }));
        TipoRuta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* Tipo de Ruta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        TipoRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoRutaActionPerformed(evt);
            }
        });

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

        Chofer.setBackground(new java.awt.Color(255, 255, 255));
        Chofer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Chofer.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Choferes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        Chofer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Pais.setBackground(new java.awt.Color(255, 255, 255));
        Pais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selec. Pais", "Costa Rica", "Colombia", "Nicaragua", "Panamá", "Honduras", " " }));
        Pais.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "* País", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        Pais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaisActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "¿Necesita Chofer?", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        toggleChofer.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        toggleChofer.setText("SI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(toggleChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(toggleChofer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TipoRuta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(131, 131, 131)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Chofer, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(CostoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                            .addComponent(Duracion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(FinRuta)
                        .addComponent(InicioRuta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Chofer))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TipoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InicioRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FinRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CostoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Duracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Error_fecha.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Error_fecha.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Error_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(458, 458, 458))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Error_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cerrar)
                    .addComponent(Registrar))
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
        if (isEdit_) {
            String IncioRuta_L = InicioRuta.getText();
            String CostoRuta_L = CostoRuta.getText();
            String TipoRuta_L = TipoRuta.getSelectedItem().toString();
            String FinRuta_L = FinRuta.getText();
            String Duracion_L = Duracion.getText();
            String Pais_L = Pais.getSelectedItem().toString();
            Color colorError = new Color(255, 204, 204);

            /*
Debe haber 2 validaciones ya que chofer depende del toogle button, y es necesario validar el pais en caso que sea internacional la ruta
             */
            if (TipoRuta_L.equals("Internacional") && Pais_L.equals("Selec. Pais")) {
                Pais.setBackground(colorError);
                warning.setText("ATENCIÓN: Seleccione un país para rutas internacionales.");
                return;
            } else {
                Pais.setBackground(Color.WHITE);
            }

            if (IncioRuta_L.isEmpty() || Duracion_L.isEmpty() || FinRuta_L.isEmpty()
                    || CostoRuta_L.isEmpty() || TipoRuta_L.equals("Selec. Ruta")) {

                if (IncioRuta_L.isEmpty()) {
                    InicioRuta.setBackground(colorError);
                } else {
                    InicioRuta.setBackground(Color.WHITE);
                }

                if (FinRuta_L.isEmpty()) {
                    FinRuta.setBackground(colorError);
                } else {
                    FinRuta.setBackground(Color.WHITE);
                }

                if (CostoRuta_L.isEmpty()) {
                    CostoRuta.setBackground(colorError);
                } else {
                    CostoRuta.setBackground(Color.WHITE);
                }

                if (Duracion_L.isEmpty()) {
                    Duracion.setBackground(colorError);
                } else {
                    Duracion.setBackground(Color.WHITE);
                }

                if (TipoRuta_L.equals("Selec. Ruta")) {
                    TipoRuta.setBackground(colorError);
                } else {
                    TipoRuta.setBackground(Color.WHITE);
                }

                warning.setText("ATENCIÓN: Por favor, complete todos los campos.");
                return;
            } else {
                Connection conn = null;
                CallableStatement stmt = null;

                try {
                    ConectionDB conexionDB = new ConectionDB();
                    conn = conexionDB.getConnection();

                    stmt = conn.prepareCall("{call Procedure_Editar_Ruta(?, ?, ?, ?, ?, ?, ?,?)}");

                    /*
         p_tipo_ruta IN VARCHAR2,
         p_id_ruta IN INT,
         p_inicio_ruta IN VARCHAR2,
         p_fin_ruta IN VARCHAR2,
         p_costo IN NUMBER,
         p_duracion IN NUMBER,
         p_pais_destino IN VARCHAR2 DEFAULT NULL
                     */
                    stmt.setString(1, TipoRuta_L);
                    stmt.setInt(2, ID_aEditar_); // No es necesario convertir, ojo si se copia este formulario para otro uso
                    stmt.setString(3, IncioRuta_L);
                    stmt.setString(4, FinRuta_L);

                    String costoNumerico = CostoRuta_L.replace("₡", "").trim();
                    double costoRuta = Double.parseDouble(costoNumerico);
                    stmt.setDouble(5, costoRuta);

                    String duracionNumerica = Duracion_L.replace(" Min.", "").trim();
                    double duracionRuta = Double.parseDouble(duracionNumerica);
                    stmt.setDouble(6, duracionRuta);

                    if (Pais_L.equals("Selec. Pais") && TipoRuta_L.equals("Internacional")) {
                        stmt.setNull(7, java.sql.Types.VARCHAR);
                    } else {
                        stmt.setString(7, Pais_L);
                    }

                    if (idChofer == null || toggleChofer.isSelected() == false) {
                        stmt.setNull(8, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(8, Integer.parseInt(idChofer));
                    }

                    stmt.executeUpdate();

                    popups.pulgar_arriba("Ruta actualizada exitosamente.");
                    actualizarTabla();
                    limpiarCampos();

                } catch (SQLException e) {
                    e.printStackTrace();
                    warning.setText("Error al actualizar la ruta: " + e.getMessage());
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
            String IncioRuta_L = InicioRuta.getText();
            String CostoRuta_L = CostoRuta.getText();
            String TipoRuta_L = TipoRuta.getSelectedItem().toString();
            String FinRuta_L = FinRuta.getText();
            String Duracion_L = Duracion.getText();
            String Pais_L = Pais.getSelectedItem().toString();
            Color colorError = new Color(255, 204, 204);
            
            if (TipoRuta_L.equals("Internacional") && Pais_L.equals("Selec. Pais")) {
                Pais.setBackground(colorError);
                warning.setText("ATENCIÓN: Seleccione un país para rutas internacionales.");
                return;
            } else {
                Pais.setBackground(Color.WHITE);
            }
           

            if (IncioRuta_L.isEmpty() || Duracion_L.isEmpty() || FinRuta_L.isEmpty()
                    || CostoRuta_L.isEmpty() || TipoRuta_L.equals("Selec. Ruta")) {

                if (IncioRuta_L.isEmpty()) {
                    InicioRuta.setBackground(colorError);
                } else {
                    InicioRuta.setBackground(Color.WHITE);
                }

                if (FinRuta_L.isEmpty()) {
                    FinRuta.setBackground(colorError);
                } else {
                    FinRuta.setBackground(Color.WHITE);
                }

                if (CostoRuta_L.isEmpty()) {
                    CostoRuta.setBackground(colorError);
                } else {
                    CostoRuta.setBackground(Color.WHITE);
                }

                if (Duracion_L.isEmpty()) {
                    Duracion.setBackground(colorError);
                } else {
                    Duracion.setBackground(Color.WHITE);
                }

                if (TipoRuta_L.equals("Selec. Ruta")) {
                    TipoRuta.setBackground(colorError);
                } else {
                    TipoRuta.setBackground(Color.WHITE);
                }

                warning.setText("ATENCIÓN: Por favor, complete todos los campos.");
                return;
            } else {
                Connection conn = null;
                CallableStatement stmt = null;

                try {
                    ConectionDB conexionDB = new ConectionDB();
                    conn = conexionDB.getConnection();

                    stmt = conn.prepareCall("{call Procedure_Agregar_Ruta(?, ?, ?, ?, ?, ?, ?)}");

//                      p_tipo_ruta IN VARCHAR2, 
//    p_inicio_ruta IN VARCHAR2,
//    p_fin_ruta IN VARCHAR2,
//    p_costo IN NUMBER, 
//    p_duracion IN NUMBER,
//    p_pais_destino IN VARCHAR2 DEFAULT NULL, 
//    p_id_chofer IN INT DEFAULT NULL 
                    stmt.setString(1, TipoRuta_L);
                    stmt.setString(2, IncioRuta_L);
                    stmt.setString(3, FinRuta_L);
                    stmt.setDouble(4, Double.parseDouble(CostoRuta_L));
                    stmt.setDouble(5, Double.parseDouble(Duracion_L));

                    if (Pais_L.equals("Selec. Pais")) {
                        stmt.setNull(6, java.sql.Types.VARCHAR);
                    } else {
                        stmt.setString(6, Pais_L);
                    }

                    if (idChofer == null || toggleChofer.isSelected() == false) {
                        stmt.setNull(7, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(7, Integer.parseInt(idChofer));
                    }
                    stmt.executeUpdate();

                    popups.pulgar_arriba("Ruta registrado exitosamente.");
                    actualizarTabla();
                    limpiarCampos();
                    this.setVisible(false);

                } catch (SQLException e) {
                    e.printStackTrace();
                    warning.setText("Error al registrar la ruta: " + e.getMessage());
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

        }

    }//GEN-LAST:event_RegistrarActionPerformed

    private void TipoRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoRutaActionPerformed

    }//GEN-LAST:event_TipoRutaActionPerformed

    private void DuracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DuracionKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_DuracionKeyTyped

    private void DuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DuracionActionPerformed

    private void CostoRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CostoRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoRutaActionPerformed

    private void InicioRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InicioRutaActionPerformed

    private void FinRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinRutaActionPerformed

    private void PaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaisActionPerformed

    private void CostoRutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoRutaKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_CostoRutaKeyTyped

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
            java.util.logging.Logger.getLogger(AgregarRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarRutas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JComboBox<String> Chofer;
    private javax.swing.JTextField CostoRuta;
    private javax.swing.JTextField Duracion;
    private javax.swing.JLabel Error_fecha;
    private javax.swing.JTextField FinRuta;
    private javax.swing.JTextField InicioRuta;
    private javax.swing.JComboBox<String> Pais;
    private javax.swing.JButton Registrar;
    private javax.swing.JComboBox<String> TipoRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton toggleChofer;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
