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
import solinfori.modelo.vo.TipoUsuariosVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class TipoUsuariosDAO {
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    
    private static final String SELECT_Q = "SELECT id_usuario_permisos, fk_tipousuario, fk_seccion, lectura, escritura, modificacion, eliminacion, acceso FROM siacm_usuario_permisos";
    private static final String INSERT_Q = "";
    private static final String UPDATE_Q = "";
    private static final String DELETE_Q = "";
    
    public TipoUsuariosDAO(){
        
    }
    
    public TipoUsuariosDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    public List<TipoUsuariosVO> readUsuarioPermisos(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<TipoUsuariosVO> listaTipoUsuarios = new ArrayList<TipoUsuariosVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(SELECT_Q);
            rs = ps.executeQuery();
            
            while(rs.next()){
                TipoUsuariosVO tipoUsuarios = new TipoUsuariosVO();
                tipoUsuarios.setId_tipousuarios(rs.getInt("id_tipousuarios"));
                tipoUsuarios.setTipo_usuario(rs.getString("tipo_usuario"));
                listaTipoUsuarios.add(tipoUsuarios);
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
        return listaTipoUsuarios;
    }
}
