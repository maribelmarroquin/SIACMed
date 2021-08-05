/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.vistas;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class ConectorVistas {
    
    private static JDesktopPane escritorio;
    
    public ConectorVistas(JDesktopPane escritorio){
        ConectorVistas.escritorio = escritorio;
    }

    public ConectorVistas() {
    }
    
    /**
     *
     * @param escritorio
     */
    public void jInExpedientes(JInternalFrame expedientes){
        this.viewjInternalFrame(expedientes);
    }
    
    public void jInPrimeraVez(JInternalFrame primeraVez){
        this.viewjInternalFrame(primeraVez);
    }
    
    public void jInAgenda(JInternalFrame agenda){
        this.viewjInternalFrame(agenda);
    }
    
    public void jInSomatoSV(JInternalFrame somatoSV){
        this.viewjInternalFrame(somatoSV);
    }
    
    public void viewjInternalFrame(JInternalFrame jInternalFrame){
        if(jInternalFrame.isVisible()){
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
            int x = (escritorio.getWidth() - jInternalFrame.getWidth())/2;
            int y = (escritorio.getHeight() - jInternalFrame.getHeight())/2;
            jInternalFrame.setLocation(x, y);
        }else{
            int x = (escritorio.getWidth() - jInternalFrame.getWidth())/2;
            int y = (escritorio.getHeight() - jInternalFrame.getHeight())/2;
            jInternalFrame.setLocation(x, y);
            escritorio.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        }
    }
    
}
