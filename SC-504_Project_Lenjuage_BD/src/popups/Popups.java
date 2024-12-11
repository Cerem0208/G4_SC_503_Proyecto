package popups;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;


public class Popups {

  public static void bienvenido() {
    String error_o_mensaje = "¡Bienvenido a nuestra App! \n `•.¸¸.•´´¯`••._.•TransiDataCR•.¸¸.•´´¯`••._.•´   \n \n HECHO POR: \n - RAMÍREZ CORRALES DIEGO \n - CERVANTES RUBÍ EMMANUEL \n - GONZÁLEZ CHAVES MARCO \n - RAMÍREZ ACOSTA LUIS";
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
        Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/popup.png"));

        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);
        dialog.setContentPane(pane);
        dialog.setTitle("Estructura de Datos 2023");
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN BIENVENIDO

  public static void despedida() {
    String error_o_mensaje = "¡Gracias por usar nuestra App! \n `•.¸¸.•´´¯`••._.•TransiDataCR•.¸¸.•´´¯`••._.•´   \n \n HECHO POR: \n - RAMÍREZ CORRALES DIEGO \n - CERVANTES RUBÍ EMMANUEL \n - GONZÁLEZ CHAVES MARCO \n - RAMÍREZ ACOSTA LUIS";
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
        Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/popup.png"));


        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);
        dialog.setContentPane(pane);
        dialog.setTitle("Estructura de Datos 2023");
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN DESPEDIDA
  
  public void ConnectingDB (String mensaje) {
        JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Border border = BorderFactory.createLineBorder(new Color(204, 0, 51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));

        ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/pulgar_arriba.gif"));

        JOptionPane pane = new JOptionPane(
                mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(2000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

  public void DisconectingDB (String mensaje) {
        JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Border border = BorderFactory.createLineBorder(new Color(204, 0, 51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));

        ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/pulgar_arriba.gif"));

        JOptionPane pane = new JOptionPane(
                mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(2000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }
   
  public void espera(String error_o_mensaje) {
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
        Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/loading.gif"));

        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN ESPERA
  
  public void buscando(String error_o_mensaje) {
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
        Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/search.gif"));

        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN ESPERA
  
  public void error(String error_o_mensaje) {
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
       Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/error.gif"));

        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN ERROR
  
  public void pulgar_arriba(String error_o_mensaje) {
    JDialog dialog = new JDialog((Frame) null, true);
        dialog.setUndecorated(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Borde
        Border border = BorderFactory.createLineBorder(new Color(204,0,51), 2);
        dialog.getRootPane().setBorder(border);

        UIManager.put("OptionPane.background", new Color(255, 255, 255));
        UIManager.put("OptionPane.messageForeground", new Color(204, 0, 51));
        UIManager.put("Panel.background", new Color(255, 255, 255));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); 

        ClassLoader classLoader = Popups.class.getClassLoader();
        ImageIcon icono2 = new ImageIcon(classLoader.getResource("img/pulgar_arriba.gif"));

        JOptionPane pane = new JOptionPane(
                error_o_mensaje,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                icono2,
                new Object[]{},
                null);

        dialog.setContentPane(pane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
  }//FIN PULGAR ARRIBA

}//FIN CLASE