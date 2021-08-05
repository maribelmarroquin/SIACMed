/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador;

import javax.swing.JOptionPane;
import solinfori.vistas.FrameLogin;

/**
 *
 * @author Dell Optiplex 7010
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLogin().setVisible(true);
            }
        });
    }
}
