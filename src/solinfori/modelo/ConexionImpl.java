/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell Optiplex 7010
 */
public interface ConexionImpl {
    
    public Connection getConexion() throws SQLException;   
    public void close(ResultSet rs); 
    public void close(PreparedStatement stmt);
    public void close(Connection conn);
    
}
