/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsesGo;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Joan
 */
public class VentanaConfiguracion extends javax.swing.JFrame {

    /**
     * Creates new form VentanaConfiguracion
     */
    
    Font font;
    
    public VentanaConfiguracion() {
        super("Horses Go!");
        initComponents();
        setLocationRelativeTo(null);
        
        registrarFuente();                
        lDificultad.setFont(font);             
    }
    
    //Método registrarFuente()
    //Registra la fuente de texto usada y la guarda en un atributo
    
    public void registrarFuente(){
        
        try {
            GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/joystix monospace.ttf"));
            ge.registerFont(font);
            font = new Font(font.getName(), Font.PLAIN, 13);
            
        } catch (IOException|FontFormatException e) {
            System.out.println("Error al cargar fuente");
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

        panelPrnicipal = new javax.swing.JPanel();
        lBanner = new javax.swing.JLabel();
        lDificultad = new javax.swing.JLabel();
        cbDificultad = new javax.swing.JComboBox<>();
        bComenzarJuego = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelPrnicipal.setBackground(new java.awt.Color(255, 251, 251));

        lBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Banner.png"))); // NOI18N

        lDificultad.setForeground(new java.awt.Color(205, 133, 63));
        lDificultad.setText("Seleccione una dificultad:");

        cbDificultad.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        cbDificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Principiante", "Intemedio", "Avanzado" }));
        cbDificultad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbDificultadKeyPressed(evt);
            }
        });

        bComenzarJuego.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bComenzarJuego.setText("Comenzar");
        bComenzarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComenzarJuegoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HorseLilBanner.jpg"))); // NOI18N

        javax.swing.GroupLayout panelPrnicipalLayout = new javax.swing.GroupLayout(panelPrnicipal);
        panelPrnicipal.setLayout(panelPrnicipalLayout);
        panelPrnicipalLayout.setHorizontalGroup(
            panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrnicipalLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPrnicipalLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lBanner))
                    .addGroup(panelPrnicipalLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPrnicipalLayout.createSequentialGroup()
                                .addComponent(cbDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bComenzarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelPrnicipalLayout.setVerticalGroup(
            panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrnicipalLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrnicipalLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPrnicipalLayout.createSequentialGroup()
                        .addComponent(lBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(panelPrnicipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bComenzarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDificultad))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrnicipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrnicipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bComenzarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComenzarJuegoActionPerformed
        
        int size = 0;
        int dificultad = cbDificultad.getSelectedIndex();
        
        //Se determina el tamaño de tablero escogido
        //4x4: Principiante
        //6x6: Intermedio
        //8x8: Avanzado
        
        switch(dificultad){
            case 0:
                size = 4;
                break;
            case 1:
                size = 6;
                break;
            case 2:
                size = 8;
                break;
        }        
        
        //Se crea y se abre la interfaz del juego
        
        Interfaz interfazJuego = new Interfaz(size);
        this.dispose();
        interfazJuego.setVisible(true);
        
    }//GEN-LAST:event_bComenzarJuegoActionPerformed

    private void cbDificultadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbDificultadKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){            
            bComenzarJuego.doClick();
        }
        
    }//GEN-LAST:event_cbDificultadKeyPressed

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
            java.util.logging.Logger.getLogger(VentanaConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaConfiguracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bComenzarJuego;
    private javax.swing.JComboBox<String> cbDificultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lBanner;
    private javax.swing.JLabel lDificultad;
    private javax.swing.JPanel panelPrnicipal;
    // End of variables declaration//GEN-END:variables
}
