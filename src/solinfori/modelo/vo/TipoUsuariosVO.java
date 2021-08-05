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
public class TipoUsuariosVO {
    private int id_tipousuarios;
    private char clave_tipousuario;
    private String tipo_usuario;

    public TipoUsuariosVO() {
    }

    public TipoUsuariosVO(int id_tipousuarios, char clave_tipousuario, String tipo_usuario) {
        this.id_tipousuarios = id_tipousuarios;
        this.clave_tipousuario = clave_tipousuario;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_tipousuarios() {
        return id_tipousuarios;
    }

    public void setId_tipousuarios(int id_tipousuarios) {
        this.id_tipousuarios = id_tipousuarios;
    }

    public char getClave_tipousuario() {
        return clave_tipousuario;
    }

    public void setClave_tipousuario(char clave_tipousuario) {
        this.clave_tipousuario = clave_tipousuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    
}
