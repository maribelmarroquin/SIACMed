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
import solinfori.modelo.vo.CatFrecuenciaRespiratoriaNormalVO;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CatFrecuenciaRespiratoriaNormalDAO {
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    private static final String Q_SELECT = "SELECT * FROM scat_frecuenciarespiratorianormal WHERE edad_min<=? AND edad_max>=?";
    
    public CatFrecuenciaRespiratoriaNormalDAO(){}
    
    public CatFrecuenciaRespiratoriaNormalDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    public List<CatFrecuenciaRespiratoriaNormalVO> filteredSearchOfFRN(int edad){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CatFrecuenciaRespiratoriaNormalVO> listarRangoFR = new ArrayList<CatFrecuenciaRespiratoriaNormalVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_SELECT);
            ps.setInt(1, edad);
            ps.setInt(2, edad);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                CatFrecuenciaRespiratoriaNormalVO fr = new CatFrecuenciaRespiratoriaNormalVO(); 
                fr.setId_frecuenciarespiratoria(rs.getInt("id_frecuenciarespiratoria"));
                fr.setEdad_min(rs.getFloat("edad_min"));
                fr.setEdad_max(rs.getFloat("edad_max"));
                fr.setFr_min(rs.getInt("fr_min"));
                fr.setFr_max(rs.getInt("fr_max"));
                listarRangoFR.add(fr);
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
        return listarRangoFR;
    }
}
