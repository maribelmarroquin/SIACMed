/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.logica;

import java.util.ArrayList;
import java.util.List;
import solinfori.modelo.dao.UsuarioPermisosDAO;
import solinfori.modelo.vo.UsuarioPermisosVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class Permisos {
    
    private UsuarioPermisosDAO usuarioPermisos;
    
    /**
     * 
     * @param sesion
     * @return 
     */
    public List<UsuarioPermisosVO> getPermisos(Sesion sesion){
        List<UsuarioPermisosVO> listaPermisos= new ArrayList<UsuarioPermisosVO>();
        
        List<String> sesionUsuario = sesion.usuarioLogueado();
        String usuario_tipo = sesionUsuario.get(1);
       
        usuarioPermisos = new UsuarioPermisosDAO();

        List<UsuarioPermisosVO> listaUsuarioPermisos = usuarioPermisos.readUsuarioPermisos(Integer.parseInt(usuario_tipo));
        
        for(UsuarioPermisosVO usuarioPermisosVO : listaUsuarioPermisos){
            UsuarioPermisosVO usuarioPermiso = new UsuarioPermisosVO();
            usuarioPermiso.setId_usuario_permisos(usuarioPermisosVO.getId_usuario_permisos());
            usuarioPermiso.setFk_tipousuario(usuarioPermisosVO.getFk_tipousuario());
            usuarioPermiso.setFk_seccion(usuarioPermisosVO.getFk_seccion());
            usuarioPermiso.setSeccion(usuarioPermisosVO.getSeccion());
            usuarioPermiso.setLectura(usuarioPermisosVO.getLectura());
            usuarioPermiso.setEscritura(usuarioPermisosVO.getEscritura());
            usuarioPermiso.setModificacion(usuarioPermisosVO.getModificacion());
            usuarioPermiso.setEliminacion(usuarioPermisosVO.getEliminacion());
            usuarioPermiso.setAcceso(usuarioPermisosVO.getAcceso());
            listaPermisos.add(usuarioPermiso);
        }  
        
        return listaPermisos;
    }
    
    public boolean getBooleano(char resultado){
        if(resultado == '1')
            return true;
        else
            return false;
    }
    
    public void lectura(){
    
    }
    
    public void escritura(){
    
    }
    
    public void modificacion(){
    
    }
    
    public void eliminacion(){
    
    }
    
    public void acceso(){
    
    }
}
