/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell Optiplex 7010
 */
public class Conexion implements ConexionImpl{
    
    private static final String URL_BD = "jdbc:mariadb://localhost:3308/solinforisiacmed";
    private static final String USER_BD = "root";
    private static final String PASS_BD = "50LiN7o12I@SI@cM";
    
    @Override
    public Connection getConexion() throws SQLException{
        return DriverManager.getConnection(URL_BD, USER_BD, PASS_BD);
    }
    
    @Override
    public void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    @Override
    public void close(PreparedStatement stmt){   
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
