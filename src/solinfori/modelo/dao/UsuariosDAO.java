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
import javax.swing.JOptionPane;
import solinfori.modelo.Conexion;
import solinfori.modelo.ConexionImpl;
import solinfori.modelo.vo.UsuariosVO;


/**
 *
 * @author Dell Optiplex 7010
 */
public class UsuariosDAO{
    
    private Connection conexionTrasaccional;
    private final ConexionImpl conexion = new Conexion();
    
    private static final String Q_SELECT = "SELECT usuario, password, fk_usuario_tipo, status, fk_usuario_pregsecreta, prefijo_pacientes FROM siacm_usuarios";
    private static final String Q_SELECT_SUPPORT = "SELECT usuario FROM siacm_usuarios";
    private static final String Q_INSERT = "INSERT INTO usuarios VALUES(?, SHA(?), ?, ?, ?)";
        
    public UsuariosDAO(){}
    
    public UsuariosDAO(Connection conexionTrasaccional){
        this.conexionTrasaccional = conexionTrasaccional;
    }
   
    public List<UsuariosVO> readUsers(){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UsuariosVO> usuarios = new ArrayList<UsuariosVO>();

        try {
            conn = this.conexionTrasaccional!= null ? this.conexionTrasaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_SELECT);
            rs = ps.executeQuery();
            
            while(rs.next()){
                UsuariosVO usuario = new UsuariosVO();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFk_usuario_tipo(rs.getInt("fk_usuario_tipo"));
                usuario.setStatus(rs.getString("status").charAt(0));
                usuario.setFk_usuario_pregsecreta(rs.getInt("fk_usuario_pregsecreta"));
                usuario.setPrefijo_pacientes(rs.getString("prefijo_pacientes").charAt(0));
                usuarios.add(usuario);
            }
        return usuarios;    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            ex.printStackTrace(System.out);
        }
        finally{
            conexion.close(rs);
            conexion.close(ps);
            if(this.conexionTrasaccional == null ){
                conexion.close(conn);
            }
        }
        return null;
    }
    
    
}
