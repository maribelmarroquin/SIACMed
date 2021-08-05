/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import solinfori.modelo.Conexion;
import solinfori.modelo.ConexionImpl;
import solinfori.modelo.vo.UsuarioPermisosVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class UsuarioPermisosDAO {
    
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    
    private static final String SELECT_Q = "SELECT id_usuario_permisos, fk_tipousuario, fk_seccion, seccion, lectura, escritura, modificacion, eliminacion, acceso \n" +
                                            "FROM siacm_usuario_permisos \n" +
                                            "JOIN scat_secciones\n" +
                                            "ON scat_secciones.id_seccion = siacm_usuario_permisos.fk_seccion\n" +
                                            "WHERE fk_tipousuario=?";
    private static final String INSERT_Q = "";
    private static final String UPDATE_Q = "";
    private static final String DELETE_Q = "";
    
    public UsuarioPermisosDAO(){
        
    }
    
    public UsuarioPermisosDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    public List<UsuarioPermisosVO> readUsuarioPermisos(int tipoUsuario){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<UsuarioPermisosVO> listaUsuarioPermisos = new ArrayList<UsuarioPermisosVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(SELECT_Q);
            ps.setInt(1, tipoUsuario);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                UsuarioPermisosVO usuarioPermisos = new UsuarioPermisosVO();
                usuarioPermisos.setId_usuario_permisos(rs.getInt("id_usuario_permisos"));
                usuarioPermisos.setFk_tipousuario(rs.getInt("fk_tipousuario"));
                usuarioPermisos.setFk_seccion(rs.getInt("fk_seccion"));
                usuarioPermisos.setLectura(rs.getString("lectura").charAt(0));
                usuarioPermisos.setEscritura(rs.getString("escritura").charAt(0));
                usuarioPermisos.setModificacion(rs.getString("modificacion").charAt(0));
                usuarioPermisos.setEliminacion(rs.getString("eliminacion").charAt(0));
                usuarioPermisos.setAcceso(rs.getString("acceso").charAt(0));
                listaUsuarioPermisos.add(usuarioPermisos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        finally{
            conexion.close(rs);
            conexion.close(ps);
            if(conexionTransaccional == null){
                conexion.close(conn);
            }
        }
        return listaUsuarioPermisos;
    }
    
}
