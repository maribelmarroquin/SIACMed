/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import solinfori.modelo.dao.UsuariosDAO;
import solinfori.modelo.vo.UsuariosVO;

/**
 *
 * @author Dell Optiplex 7010
 */
public class Sesion {
    
    private static final UsuariosDAO usuariosDAO = new UsuariosDAO();
    private static UsuariosVO usuariosVO;
    
    public Sesion(){
        usuariosVO = new UsuariosVO();
    }
        
    public boolean login(String usuario, String password){
        String pass_encriptada = sha1(password);
        List<UsuariosVO> listaUsuarios = usuariosDAO.readUsers();
        for (UsuariosVO usuarios : listaUsuarios) {
            usuariosVO.setUsuario(usuarios.getUsuario()); 
            usuariosVO.setPassword(usuarios.getPassword());
            usuariosVO.setFk_usuario_tipo(usuarios.getFk_usuario_tipo());
            usuariosVO.setStatus(usuarios.getStatus());
            usuariosVO.setFk_usuario_pregsecreta(usuarios.getFk_usuario_pregsecreta());
            usuariosVO.setPrefijo_pacientes(usuarios.getPrefijo_pacientes());
            if(usuariosVO.getUsuario().equals(usuario) && usuarios.getPassword().equals(pass_encriptada)){
                return true;
            }
        }
        return false;
    }
    
    public List<String> usuarioLogueado(){
        List <String> usuario = new ArrayList<>();
        usuario.add(usuariosVO.getUsuario());
        usuario.add(Integer.toString(usuariosVO.getFk_usuario_tipo()));
        usuario.add(Character.toString(usuariosVO.getStatus()));
        usuario.add(Character.toString(usuariosVO.getPrefijo_pacientes()));
        return usuario;
    }
    
    private String encriptado(String password, String tipoHash){
        
        try {
            MessageDigest newPassword = MessageDigest.getInstance(tipoHash);
            byte[] array = newPassword.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    /* Retorna un hash MD5 a partir de un texto */
    public String md5(String txt) {
            return encriptado(txt, "MD5");
    }

    /* Retorna un hash SHA1 a partir de un texto */
    public String sha1(String txt) {
            return encriptado(txt, "SHA1");
    }
}
