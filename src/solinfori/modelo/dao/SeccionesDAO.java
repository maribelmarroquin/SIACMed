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
import solinfori.modelo.vo.SeccionesVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class SeccionesDAO {
    
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    
    private static final String Q_SELECT = "SELECT id_seccion, clave_seccion, seccion FROM scat_secciones";
    private static final String Q_INSERT = "";
    private static final String Q_UPDATE = "";
    private static final String Q_DELETE = "";
    
    public SeccionesDAO(){}
    
    public SeccionesDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    public List<SeccionesVO> readSections(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<SeccionesVO> listaSecciones = new ArrayList<SeccionesVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_SELECT);
            rs = ps.executeQuery();
            
            while(rs.next()){
                SeccionesVO secciones = new SeccionesVO();
                secciones.setId_seccion(rs.getInt("id_seccion"));
                secciones.setClave_seccion(rs.getString("clave_seccion").charAt(0));
                secciones.setSeccion(rs.getString("seccion"));
                listaSecciones.add(secciones);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null ,ex.toString());
        }
        finally{
            conexion.close(rs);
            conexion.close(ps);
            if(this.conexionTransaccional == null){
                conexion.close(conn);
            }
        }
        return listaSecciones;
    }
}
