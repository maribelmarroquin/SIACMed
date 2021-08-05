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
import solinfori.modelo.vo.CatPresionArterialNormalVO;


/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CatPresionArterialNormalDAO {
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    private static final String Q_SELECT = "SELECT * FROM scat_presionarterialnormal WHERE edad_min<=? AND edad_max>=? AND sexo=?";
    
    public CatPresionArterialNormalDAO(){}
    
    public CatPresionArterialNormalDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    public List<CatPresionArterialNormalVO> filteredSearchOfPAN(int edad, char sexo){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CatPresionArterialNormalVO> listarRangoPresion = new ArrayList<CatPresionArterialNormalVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_SELECT);
            ps.setInt(1, edad);
            ps.setInt(2, edad);
            ps.setString(3, Character.toString(sexo));
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                CatPresionArterialNormalVO presionArterial = new CatPresionArterialNormalVO(); 
                presionArterial.setId(rs.getInt("id_presionarterial"));
                presionArterial.setEdad_min(rs.getInt("edad_min"));
                presionArterial.setEdad_max(rs.getInt("edad_max"));
                presionArterial.setSexo(rs.getString("sexo").charAt(0));
                presionArterial.setSistolica_max(rs.getInt("sistolica_max"));
                presionArterial.setSistolica_min(rs.getInt("sistolica_min"));
                presionArterial.setDiastolica_max(rs.getInt("diastolica_max"));
                presionArterial.setDiastolica_min(rs.getInt("diastolica_min"));
                listarRangoPresion.add(presionArterial);
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
        return listarRangoPresion;
    }
}
