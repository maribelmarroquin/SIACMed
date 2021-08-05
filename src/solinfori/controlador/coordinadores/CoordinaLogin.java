/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.coordinadores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import solinfori.modelo.logica.Permisos;
import solinfori.modelo.logica.Sesion;
import solinfori.modelo.vo.UsuarioPermisosVO;
import solinfori.vistas.Plataforma;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CoordinaLogin {
    
    private Sesion sesion;
    private final Plataforma plataforma;
    private Permisos permisos;
    
    public CoordinaLogin(){
        plataforma = new Plataforma();
        permisos = new Permisos();
    }
    
    public boolean loginUsuario(String usuario, String password){
        sesion = new Sesion();
        if(sesion.login(usuario, password) == true){
            getPlataforma(sesion);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Acceso denegado. Valide sus credenciales e intente nuevamente.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void getPlataforma(Sesion sesion){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CoordinaLogin cl = new CoordinaLogin();
                cl.plataforma.setSesion(sesion);
                cl.plataforma.setVisible(true);
                cl.plataforma.setlUsuario(sesion.usuarioLogueado().get(0));
                cl.permisos(
                        sesion, 
                        cl.plataforma.getmCertificado(), 
                        cl.plataforma.getmSomato(),
                        cl.plataforma.getmExpedientes(),
                        cl.plataforma.getmAgenda(),
                        cl.plataforma.getmCorreo(),
                        cl.plataforma.getmNotasRemision(),
                        cl.plataforma.getmFacturas(),
                        cl.plataforma.getmCxc(),
                        cl.plataforma.getmReportes(),
                        cl.plataforma.getmDatosFiscales(),
                        cl.plataforma.getmDisenosPlantillas(),
                        cl.plataforma.getmRespaldos(),
                        cl.plataforma.getmBitacoraAcceso(),
                        cl.plataforma.getmErrores(),
                        cl.plataforma.getmConfiguraUsuarios(),
                        cl.plataforma.getmConfiguraCorreo()
                );
                
            }
        });
    }
    
    public void permisos(Sesion sesion, JMenuItem... jMenuItem){
        List<UsuarioPermisosVO> listaPermisos = permisos.getPermisos(sesion);
        int i = 0;
        for (UsuarioPermisosVO usuarioPermisos : listaPermisos) {   
            permisoActivo(jMenuItem[i], usuarioPermisos.getAcceso());
            i++;
        }
    }
    
    public void permisoLectura(){
    }
    
    public void permisoEscritura(){
        
    }
    
    public void permisoModificacion(){
    }
    
    public void permisoEliminacion(){
    }
        
    public void permisoActivo(JMenuItem jMenuItem, char permiso){
        if(permisos.getBooleano(permiso) == true)
            jMenuItem.setEnabled(true);
        else
            jMenuItem.setEnabled(false);
    }
}
