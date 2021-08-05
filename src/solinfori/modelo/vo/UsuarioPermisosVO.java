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
public class UsuarioPermisosVO {
    
    private int id_usuario_permisos;
    private int fk_tipousuario;
    private int fk_seccion;
    private char lectura;
    private char escritura;
    private char modificacion;
    private char eliminacion;
    private char acceso;
    
    private String seccion;

    public UsuarioPermisosVO() {
    }

    public UsuarioPermisosVO(int id_usuario_permisos, int fk_tipousuario, int fk_seccion, char lectura, char escritura, char modificacion, char eliminacion, char acceso) {
        this.id_usuario_permisos = id_usuario_permisos;
        this.fk_tipousuario = fk_tipousuario;
        this.fk_seccion = fk_seccion;
        this.lectura = lectura;
        this.escritura = escritura;
        this.modificacion = modificacion;
        this.eliminacion = eliminacion;
        this.acceso = acceso;
    }    

    public int getId_usuario_permisos() {
        return id_usuario_permisos;
    }

    public void setId_usuario_permisos(int id_usuario_permisos) {
        this.id_usuario_permisos = id_usuario_permisos;
    }

    public int getFk_tipousuario() {
        return fk_tipousuario;
    }

    public void setFk_tipousuario(int fk_tipousuario) {
        this.fk_tipousuario = fk_tipousuario;
    }

    public int getFk_seccion() {
        return fk_seccion;
    }

    public void setFk_seccion(int fk_seccion) {
        this.fk_seccion = fk_seccion;
    }

    public char getLectura() {
        return lectura;
    }

    public void setLectura(char lectura) {
        this.lectura = lectura;
    }

    public char getEscritura() {
        return escritura;
    }

    public void setEscritura(char escritura) {
        this.escritura = escritura;
    }

    public char getModificacion() {
        return modificacion;
    }

    public void setModificacion(char modificacion) {
        this.modificacion = modificacion;
    }

    public char getEliminacion() {
        return eliminacion;
    }

    public void setEliminacion(char eliminacion) {
        this.eliminacion = eliminacion;
    }

    public char getAcceso() {
        return acceso;
    }

    public void setAcceso(char acceso) {
        this.acceso = acceso;
    }
    
    /*--------------------------------------------------------*/
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
}
