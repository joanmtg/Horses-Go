/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsesGo;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Joan
 */
public class Interfaz extends javax.swing.JFrame implements MouseListener{

    private Programa programa;
    private Font font = new Font("Lato", Font.PLAIN, 14);
    private boolean fichaSeleccionada;
    private Posicion posBoton1;
    private Posicion posBoton2;
    private Posicion posBotonSeleccionado;
    private boolean finJuego;  

    public Interfaz(int tamano) {
        super("Horses Go!");
        initComponents();
        setLocationRelativeTo(null);

        programa = new Programa(tamano);
        fichaSeleccionada = false;

        dibujarTablero();
        removeAllMouseListeners();

        movimientoMaquina();        
    } 
    

    public void dibujarTablero() {

        int size = programa.getSize();
        int tablero[][] = programa.getTablero();

        //Se dibuja el tablero dependiendo de la dificultad escogida
        lTablero.setIcon(new ImageIcon("src/images/" + size + "x" + size + ".png"));

        lTablero.setLayout(new GridLayout(size, size));

        JButton boton;
        int valor = 0;

        //Se agregan los botones al label del tablero
        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                boton = new JButton();
                valor = tablero[i][j];

                switch (valor) {
                    case 3:
                    case 4:
                        boton.setIcon(new ImageIcon("src/images/" + valor + ".png"));
                        //boton.setPressedIcon(new ImageIcon("src/images/" + valor + "-S.png"));
                        break;
                    default:
                        boton.setIcon(new ImageIcon("src/images/" + valor + "-" + size + ".png"));
                        //boton.setPressedIcon(new ImageIcon("src/images/" + tablero[i][j] + "-" + size + "S.png"));
                        break;
                }

                //Para que no tenga fondo el botón
                boton.setOpaque(false);
                boton.setContentAreaFilled(false);
                boton.setBorderPainted(false);
                boton.addMouseListener(this);
                lTablero.add(boton);
            }
        }
    }

    public void actualizarTablero() {

        int size = programa.getSize();
        int tablero[][] = programa.getTablero();

        JButton boton;
        int valor = 0;
        int indiceBoton = 0;

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                boton = (JButton) lTablero.getComponent(indiceBoton);
                valor = tablero[i][j];

                switch (valor) {
                    case 3:
                    case 4:
                        boton.setIcon(new ImageIcon("src/images/" + valor + ".png"));
                        //boton.setPressedIcon(new ImageIcon("src/images/" + valor + "-S.png"));
                        break;
                    default:
                        boton.setIcon(new ImageIcon("src/images/" + valor + "-" + size + ".png"));
                        //boton.setPressedIcon(new ImageIcon("src/images/" + tablero[i][j] + "-" + size + "S.png"));
                        break;
                }

                indiceBoton++;
            }
        }
    }

    //Pequeños cambios a los siguientes dos métodos son la clave para que se pueda jugar de a 2 jugadores
    //El manejo del turno es la clave  programa.getTurno() == tablero[filaSeleccionada][colSeleccionada]
    @Override
    public void mousePressed(MouseEvent evt) {

        int[][] tablero = programa.getTablero();
        int size = programa.getSize();
        int indiceBoton = 0;

        //Se revisa qué botón fue seleccionado
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                JButton boton = (JButton) lTablero.getComponent(indiceBoton);

                if (evt.getSource() == boton) {
                    posBotonSeleccionado = new Posicion(i, j);
                }
                indiceBoton++;
            }
        }

        //Se revisa si ya había una ficha seleccionada o no para determinar la operación
        if (!fichaSeleccionada) {

            if (tablero[posBotonSeleccionado.getFila()][posBotonSeleccionado.getColumna()] == 2) { //Si el turno es de las negras   
                posBoton1 = posBotonSeleccionado;
                seleccionarBoton("Selección", evt);
            }

        } else {
            posBoton2 = posBotonSeleccionado;
            seleccionarBoton("Movimiento", evt);
        }
    }

    //operacion := "Selección" | "Movimiento"
    public void seleccionarBoton(String operacion, MouseEvent evt) {

        int[][] tablero = programa.getTablero();
        int size = programa.getSize();
        int i = posBotonSeleccionado.getFila();
        int j = posBotonSeleccionado.getColumna();

        //Si se va a seleccionar la ficha a mover
        if (operacion.equals("Selección")) {

            JButton boton1 = (JButton) lTablero.getComponent(posBoton1.getFila() * programa.getSize() + posBoton1.getColumna());

            programa.marcarMovimientosValidosJugador(); //Mostrar movimientos disponibles (poner 4 en las posiciones válidas de la matriz)         
            actualizarTablero(); //Se actualizan los iconos de los botones

            boton1.setIcon(new ImageIcon("src/images/" + tablero[i][j] + "-" + size + "S.png"));
            boton1.removeMouseListener(this);
            fichaSeleccionada = true;

            programa.mostrarTablero();

        } //Si se va a mover la ficha seleccionada
        else if (operacion.equals("Movimiento")) {

            JButton boton1 = (JButton) lTablero.getComponent(posBoton1.getFila() * programa.getSize() + posBoton1.getColumna());

            if (validarMovimientoJugador()) { //Si el movimiento es válido

                tablero[posBoton2.getFila()][posBoton2.getColumna()] = programa.getTurno(); //Se mueve la ficha seleccionada
                tablero[posBoton1.getFila()][posBoton1.getColumna()] = 3;                   //Se pone un 3 en la casilla pisada
                programa.limpiarMovimientosValidos();     //Se quitan los 4's de la matriz           
                actualizarTablero();                      //Se actualizan los iconos de los botones

                boton1.addMouseListener(this);
                fichaSeleccionada = false;
                programa.cambiarTurno();
                removeAllMouseListeners();
                programa.mostrarTablero();                                     
                
                movimientoMaquina();

            }
        }
    }
    
    public void movimientoMaquina(){
        
        new Thread() {

            @Override
            public void run() {                
                validarFinJuego();

                if (!finJuego) {                    
                    programa.movimientoMaquina();
                    programa.cambiarTurno();
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    actualizarTablero();
                    addAllMouseListeners();
                    validarFinJuego();
                }
            }

        }.start();
    }

    public boolean validarFinJuego() {

        finJuego = false;

        if (programa.finJuego()) {                       
                        
            String mensaje = "ganaste";
            if(programa.getTurno() == 2){
                mensaje = "game-over";
            }            
            
            JOptionPane.showMessageDialog(null, "", "Fin del juego", 0, new ImageIcon("src/images/" + mensaje + ".gif"));
            removeAllMouseListeners();
            finJuego = true;

        }

        return finJuego;

    }

    public boolean validarMovimientoJugador() {

        return (programa.getTablero()[posBoton2.getFila()][posBoton2.getColumna()] == 4);
    }

    public void removeAllMouseListeners() {

        int cantidadBotones = lTablero.getComponentCount();

        JButton boton;

        for (int i = 0; i < cantidadBotones; i++) {
            boton = (JButton) lTablero.getComponent(i);
            boton.removeMouseListener(this);
        }
    }

    //Se llama cuando la máquina haya jugado
    public void addAllMouseListeners() {

        int cantidadBotones = lTablero.getComponentCount();

        JButton boton;

        for (int i = 0; i < cantidadBotones; i++) {
            boton = (JButton) lTablero.getComponent(i);
            boton.addMouseListener(this);
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

        panelTablero = new javax.swing.JPanel();
        lTablero = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuJuego = new javax.swing.JMenu();
        bNuevoJuego = new javax.swing.JMenuItem();
        bSalir = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        bInstrucciones = new javax.swing.JMenuItem();
        bAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelTablero.setPreferredSize(new java.awt.Dimension(480, 480));

        lTablero.setPreferredSize(new java.awt.Dimension(480, 480));

        javax.swing.GroupLayout panelTableroLayout = new javax.swing.GroupLayout(panelTablero);
        panelTablero.setLayout(panelTableroLayout);
        panelTableroLayout.setHorizontalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lTablero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTableroLayout.setVerticalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        barraMenu.setBackground(new java.awt.Color(255, 251, 251));
        barraMenu.setBorder(null);
        barraMenu.setPreferredSize(new java.awt.Dimension(94, 30));

        menuJuego.setText("Juego");
        menuJuego.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N

        bNuevoJuego.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        bNuevoJuego.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bNuevoJuego.setText("Nuevo Juego");
        bNuevoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuevoJuegoActionPerformed(evt);
            }
        });
        menuJuego.add(bNuevoJuego);

        bSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        bSalir.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        menuJuego.add(bSalir);

        barraMenu.add(menuJuego);

        menuAyuda.setText("Ayuda");
        menuAyuda.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N

        bInstrucciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        bInstrucciones.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bInstrucciones.setText("Instrucciones");
        bInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInstruccionesActionPerformed(evt);
            }
        });
        menuAyuda.add(bInstrucciones);

        bAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        bAcerca.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bAcerca.setText("Acerca de");
        bAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAcercaActionPerformed(evt);
            }
        });
        menuAyuda.add(bAcerca);

        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNuevoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuevoJuegoActionPerformed

        JLabel lInfo = new JLabel("<html>¿Está seguro de que quiere configurar un nuevo juego?<br/><div align=\"center\">Se perderá la partida actual</div></html>");
        lInfo.setFont(font);

        if(!finJuego){
            
            int opcion = JOptionPane.showConfirmDialog(null, lInfo);

            if (opcion == JOptionPane.YES_OPTION) {

                VentanaConfiguracion vPrincipal = new VentanaConfiguracion();
                this.dispose();
                vPrincipal.setVisible(true);
            }            
        }else{
            VentanaConfiguracion vPrincipal = new VentanaConfiguracion();
            this.dispose();
            vPrincipal.setVisible(true);
        }
        
        
    }//GEN-LAST:event_bNuevoJuegoActionPerformed

    private void bInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInstruccionesActionPerformed

        JLabel lInfo = new JLabel("Instrucciones:");
        lInfo.setFont(font);

        JOptionPane.showMessageDialog(null, lInfo);

    }//GEN-LAST:event_bInstruccionesActionPerformed

    private void bAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAcercaActionPerformed

        JLabel lInfo = new JLabel("Acerca de:");
        lInfo.setFont(font);

        JOptionPane.showMessageDialog(null, lInfo);

    }//GEN-LAST:event_bAcercaActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed

        JLabel lInfo = new JLabel("<html>¿Está seguro de que quiere salir?<br/><div align=\"center\">Se perderá la partida actual</div></html>");
        lInfo.setFont(font);

        int opcion = JOptionPane.showConfirmDialog(null, lInfo);

        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
        }

    }//GEN-LAST:event_bSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        bSalir.doClick();

    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz(6).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bAcerca;
    private javax.swing.JMenuItem bInstrucciones;
    private javax.swing.JMenuItem bNuevoJuego;
    private javax.swing.JMenuItem bSalir;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JLabel lTablero;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuJuego;
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }    

}
