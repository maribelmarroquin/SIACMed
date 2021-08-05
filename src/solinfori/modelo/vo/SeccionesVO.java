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
public class SeccionesVO {
    
    private int id_seccion;
    private char clave_seccion;
    private String seccion;

    public SeccionesVO() {
    }
    
    public SeccionesVO(int id_seccion, char clave_seccion, String seccion) {
        this.id_seccion = id_seccion;
        this.clave_seccion = clave_seccion;
        this.seccion = seccion;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public char getClave_seccion() {
        return clave_seccion;
    }

    public void setClave_seccion(char clave_seccion) {
        this.clave_seccion = clave_seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
    
    
}
