/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.vo;

/**
 *
 * @author Dell Optiplex 7010
 */
public class UsuariosVO {
    private int id_usuario;
    private String usuario;
    private String password;
    private int fk_usuario_tipo;
    private char status;
    private int fk_usuario_pregsecreta;
    private char prefijo_pacientes;

    public UsuariosVO() {
    }
    
    public UsuariosVO(int id_usuario, String usuario, String password, int fk_usuario_tipo, char status, int fk_usuario_pregsecreta, char prefijo_pacientes) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.password = password;
        this.fk_usuario_tipo = fk_usuario_tipo;
        this.status = status;
        this.fk_usuario_pregsecreta = fk_usuario_pregsecreta;
        this.prefijo_pacientes = prefijo_pacientes;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFk_usuario_tipo() {
        return fk_usuario_tipo;
    }

    public void setFk_usuario_tipo(int fk_usuario_tipo) {
        this.fk_usuario_tipo = fk_usuario_tipo;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getFk_usuario_pregsecreta() {
        return fk_usuario_pregsecreta;
    }

    public void setFk_usuario_pregsecreta(int fk_usuario_pregsecreta) {
        this.fk_usuario_pregsecreta = fk_usuario_pregsecreta;
    }

    public char getPrefijo_pacientes() {
        return prefijo_pacientes;
    }

    public void setPrefijo_pacientes(char prefijo_pacientes) {
        this.prefijo_pacientes = prefijo_pacientes;
    }
    
    
    @Override
    public String toString() {
        return "UsuariosVO{" + "id_usuario=" + id_usuario + ", usuario=" + usuario + ", password=" + password + ", fk_usuario_tipo=" + fk_usuario_tipo + ", status=" + status + ", fk_usuario_pregsecreta=" + fk_usuario_pregsecreta + '}';
    }
        
}
