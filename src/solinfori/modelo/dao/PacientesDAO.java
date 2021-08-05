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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import solinfori.modelo.Conexion;
import solinfori.modelo.ConexionImpl;
import solinfori.modelo.vo.PacientesVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class PacientesDAO {
    private Connection conexionTransaccional;
    private final ConexionImpl conexion = new Conexion();
    
    private static final String Q_SELECT_ALL_SECURE = "SELECT clave_paciente, consecutivo, nombre, apellido_pat, apellido_mat, fecha_nacimiento, email FROM siacm_pacientes ORDER BY clave_paciente ASC, consecutivo ASC";
    private static final String Q_SELECT_ALL = "SELECT * FROM siacm_pacientes";
    private static final String Q_SELECT_EXIST="SELECT * FROM siacm_pacientes WHERE clave_paciente=? AND consecutivo=?";
    private static final String Q_INSERT = "INSERT INTO siacm_pacientes (clave_paciente, consecutivo, nombre, apellido_pat, apellido_mat, fecha_nacimiento, sexo, calle, "
                                                                        + "numero_int, numero_ext, codigo_postal, municipio, localidad, estado, pais, telefono, "
                                                                        + "celular, email, fk_fiscales, fk_expediente) "
                                                                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String Q_UPDATE = "UPDATE siacm_pacientes SET nombre=?, apellido_pat=?, apellido_mat=?, fecha_nacimiento=?, sexo=?, calle=?, "
                                                                        + "numero_int=?, numero_ext=?, codigo_postal=?, municipio=?, localidad=?, estado=?, pais=?, telefono=?, "
                                                                        + "celular=?, email=? WHERE clave_paciente=? AND consecutivo=? AND fk_expediente=0";
    private static final String Q_DELETE = "";
    
    public PacientesDAO(){}
    
    public PacientesDAO(Connection conexionTrasaccional){
        this.conexionTransaccional = conexionTrasaccional;
    }
    
    /**
     * Método que retorna una lista de pacientes, registrada por cualquier usuario, con determinados datos del paciente.
     * @return 
     */
    public List<PacientesVO> readPatientsSecure(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PacientesVO> listaPacientes = new ArrayList<PacientesVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            
            ps = conn.prepareStatement(Q_SELECT_ALL_SECURE);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
             
                PacientesVO pacientes = new PacientesVO();
                
                pacientes.setClave_paciente(rs.getString("clave_paciente").charAt(0));
                pacientes.setConsecutivo(rs.getInt("consecutivo"));
                pacientes.setNombre(rs.getString("nombre"));
                pacientes.setApellido_pat(rs.getString("apellido_pat"));
                pacientes.setApellido_mat(rs.getString("apellido_mat"));
                pacientes.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                pacientes.setEmail(rs.getString("email"));
                
                //Falta retornar fk_fiscales y fk_expediente
                
                listaPacientes.add(pacientes);
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
        return listaPacientes;
    }
    
    /**
     * Método que retorna una lista de pacientes con todos sus datos, registrados por cualquier usuario en el sistema.
     * @return 
     */
    public List<PacientesVO> readPatients(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PacientesVO> listaPacientes = new ArrayList<PacientesVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            
            ps = conn.prepareStatement(Q_SELECT_ALL);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                PacientesVO pacientes = new PacientesVO();
                
                
                pacientes.setClave_paciente(rs.getString("clave_paciente").charAt(0));
                pacientes.setConsecutivo(rs.getInt("consecutivo"));
                pacientes.setNombre(rs.getString("nombre"));
                pacientes.setApellido_pat(rs.getString("apellido_pat"));
                pacientes.setApellido_mat(rs.getString("apellido_mat"));
                pacientes.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                pacientes.setSexo(rs.getString("sexo").charAt(0));
                pacientes.setCalle(rs.getString("calle"));
                pacientes.setNumero_int(rs.getInt("numero_int"));
                pacientes.setNumero_ext(rs.getInt("numero_ext"));
                pacientes.setCodigo_postal(rs.getInt("codigo_postal"));
                pacientes.setMunicipio(rs.getString("municipio"));
                pacientes.setLocalidad(rs.getString("municipio"));
                pacientes.setEstado(rs.getString("municipio"));
                pacientes.setPais(rs.getString("municipio"));
                pacientes.setTelefono(rs.getString("municipio"));
                pacientes.setCelular(rs.getString("municipio"));
                pacientes.setEmail(rs.getString("email"));
                
                listaPacientes.add(pacientes);
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
        return listaPacientes;
    }
    
    /**
     * Método que retorna los datos del paciente con base en la clave única del paciente.
     * @return 
     */
    public List<PacientesVO> readPatient(char clave, int consecutivo){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PacientesVO> listaPacientes = new ArrayList<PacientesVO>();
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            
            ps = conn.prepareStatement(Q_SELECT_EXIST);
            
            ps.setString(1, Character.toString(clave));
            ps.setInt(2, consecutivo);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                PacientesVO pacientes = new PacientesVO();
                
                
                pacientes.setClave_paciente(rs.getString("clave_paciente").charAt(0));
                pacientes.setConsecutivo(rs.getInt("consecutivo"));
                pacientes.setNombre(rs.getString("nombre"));
                pacientes.setApellido_pat(rs.getString("apellido_pat"));
                pacientes.setApellido_mat(rs.getString("apellido_mat"));
                pacientes.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                pacientes.setSexo(rs.getString("sexo").charAt(0));
                pacientes.setCalle(rs.getString("calle"));
                pacientes.setNumero_int(rs.getInt("numero_int"));
                pacientes.setNumero_ext(rs.getInt("numero_ext"));
                pacientes.setCodigo_postal(rs.getInt("codigo_postal"));
                pacientes.setMunicipio(rs.getString("municipio"));
                pacientes.setLocalidad(rs.getString("localidad"));
                pacientes.setEstado(rs.getString("estado"));
                pacientes.setPais(rs.getString("pais"));
                pacientes.setTelefono(rs.getString("telefono"));
                pacientes.setCelular(rs.getString("celular"));
                pacientes.setEmail(rs.getString("email"));
                
                listaPacientes.add(pacientes);
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
        return listaPacientes;
    }
    
    /**
     * Método que valida la existencia de cierto paciente, por medio de la clave y retorna falso o verdadero según su existencia.
     * @param pacientesVO
     * @return 
     */
    public boolean isPatientExistent(PacientesVO pacientesVO){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int countRow=0;
        
        
        try {
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            
            ps = conn.prepareStatement(Q_SELECT_EXIST);
            ps.setString(1, Character.toString(pacientesVO.getClave_paciente()));
            ps.setInt(2, pacientesVO.getConsecutivo());
            
            rs = ps.executeQuery();
            rs.last();
            countRow = rs.getRow();
            
            if(countRow > 0){
                return true;
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
        return false;
        
    }
    
    /**
     * Método que registra paciente nuevo.
     * @param pacientesVO
     * @return 
     */
    public int writePatients(PacientesVO pacientesVO){
        
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_INSERT);
            ps.setString(1, Character.toString(pacientesVO.getClave_paciente()));
            ps.setInt(2, pacientesVO.getConsecutivo());
            ps.setString(3,  pacientesVO.getNombre());
            ps.setString(4,  pacientesVO.getApellido_pat());
            ps.setString(5,  pacientesVO.getApellido_mat());
            //ps.setString(6,  this.getFechaNacimiento(pacientesVO.getFecha_nacimiento()));
            ps.setString(6,  this.getFechaNacimiento(pacientesVO.getFecha_nacimiento()));
            ps.setString(7, Character.toString(pacientesVO.getSexo()));
            ps.setString(8,  pacientesVO.getCalle());
            ps.setInt(9,  pacientesVO.getNumero_int());
            ps.setInt(10,  pacientesVO.getNumero_ext());
            ps.setInt(11,  pacientesVO.getCodigo_postal());
            ps.setString(12,  pacientesVO.getMunicipio());
            ps.setString(13,  pacientesVO.getLocalidad());
            ps.setString(14,  pacientesVO.getEstado());
            ps.setString(15,  pacientesVO.getPais());
            ps.setString(16,  pacientesVO.getTelefono());
            ps.setString(17,  pacientesVO.getCelular());
            ps.setString(18,  pacientesVO.getEmail());
            ps.setInt(19,  pacientesVO.getFk_fiscales());
            ps.setInt(20,  pacientesVO.getFk_expediente());
            rows = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nuevo paciente registrado correctamente.");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null ,ex.toString());
            ex.printStackTrace(System.out);
        }
        finally{
            conexion.close(ps);
            if(this.conexionTransaccional == null){
                conexion.close(conn);
            }
        }
        return rows;
    }
    
    /**
     * Método que actualiza el registro de un paciente
     * @param pacientesVO
     * @return 
     */
    public int updatePatients(PacientesVO pacientesVO){
        int rows = 0;
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            ps = conn.prepareStatement(Q_UPDATE);
            
            ps.setString(1,  pacientesVO.getNombre());
            ps.setString(2,  pacientesVO.getApellido_pat());
            ps.setString(3,  pacientesVO.getApellido_mat());
            ps.setString(4, this.getFechaNacimiento(pacientesVO.getFecha_nacimiento()));
            ps.setString(5, Character.toString(pacientesVO.getSexo()));
            ps.setString(6,  pacientesVO.getCalle());
            ps.setInt(7,  pacientesVO.getNumero_int());
            ps.setInt(8,  pacientesVO.getNumero_ext());
            ps.setInt(9,  pacientesVO.getCodigo_postal());
            ps.setString(10,  pacientesVO.getMunicipio());
            ps.setString(11,  pacientesVO.getLocalidad());
            ps.setString(12,  pacientesVO.getEstado());
            ps.setString(13,  pacientesVO.getPais());
            ps.setString(14,  pacientesVO.getTelefono());
            ps.setString(15,  pacientesVO.getCelular());
            ps.setString(16,  pacientesVO.getEmail());
            ps.setString(17, Character.toString(pacientesVO.getClave_paciente()));
            ps.setInt(18, pacientesVO.getConsecutivo());
            
            rows = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente.");
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null ,ex.toString());
            ex.printStackTrace(System.out);
        }
        finally{
            conexion.close(ps);
            if(this.conexionTransaccional == null){
                conexion.close(conn);
            }
        }
        
        return rows;
    }
    
    
    /**
     * Método que transforma la fecha al formato y tipo requerido.
     * @param fecha
     * @return 
     */
    public String getFechaNacimiento(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }
    
    /**
     * Método que Retorna una busqueda de paciente o pacientes en torno a un filtro.
     * @param filtro
     * @return 
     */
    public List<PacientesVO> filteredSearchOfPatient(String[][] filtro){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PacientesVO> listaPacientes = new ArrayList<PacientesVO>();
        
        String queryComplete = "";
        try {
            
            conn = this.conexionTransaccional!= null ? this.conexionTransaccional : conexion.getConexion();
            String nombre, valor;
            
            for (int i = 0; i < filtro.length; i++) {
                for (int j = 0; j < filtro[i].length; j++) {
                    if(filtro[i][j]!=null){
                        if (filtro[i][j] ==  "clave_paciente" || filtro[i][j] == "consecutivo") {
                            nombre = filtro[i][j]+"=";
                            j++;
                            valor = filtro[i][j];
                            if(valor!=""){
                                queryComplete = queryComplete +" "+ nombre + "'"+valor+"'";
                                if((i+1)!=filtro.length){
                                    queryComplete  = queryComplete + " AND ";
                                }
                            }
                        }else{
                            nombre = filtro[i][j]+" LIKE ";
                            j++;
                            valor = filtro[i][j];
                            if(valor!=""){
                                queryComplete = queryComplete +" "+ nombre + "'%"+valor+"%'";
                                if((i+1)!=filtro.length){
                                    queryComplete  = queryComplete + " AND ";
                                }
                            }
                        }
                        
                        
                    } 
                }
            }
            
            if(queryComplete.substring(queryComplete.length()-5, queryComplete.length()).equals(" AND ")){
                queryComplete = queryComplete.substring(0, queryComplete.length()-5);
            }
            
            String query = "SELECT * FROM siacm_pacientes WHERE "+queryComplete+ " ORDER BY clave_paciente ASC, consecutivo ASC";
            
            ps = conn.prepareStatement(query);
                        
            rs = ps.executeQuery();
            
            while(rs.next()){
             
                PacientesVO pacientes = new PacientesVO();
                
                pacientes.setClave_paciente(rs.getString("clave_paciente").charAt(0));
                pacientes.setConsecutivo(rs.getInt("consecutivo"));
                pacientes.setNombre(rs.getString("nombre"));
                pacientes.setApellido_pat(rs.getString("apellido_pat"));
                pacientes.setApellido_mat(rs.getString("apellido_mat"));
                pacientes.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                pacientes.setEmail(rs.getString("email"));
                
                //Falta retornar fk_fiscales y fk_expediente
                
                listaPacientes.add(pacientes);
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
        return listaPacientes;
    }
}
