/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.vo;

import java.util.Date;

/**
 *
 * @author Dell Optiplex 7010
 */
public class PacientesVO {
   
    private int id_paciente;
    private char clave_paciente;
    private int consecutivo;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private Date fecha_nacimiento;
    private char sexo;
    private String calle;
    private int numero_int;
    private int numero_ext;
    private int codigo_postal;
    private String municipio;
    private String localidad;
    private String estado;
    private String pais;
    private String telefono;
    private String celular;
    private String email;
    private int fk_fiscales;
    private int fk_expediente;

    public PacientesVO() {
    }
    
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public char getClave_paciente() {
        return clave_paciente;
    }

    public void setClave_paciente(char clave_paciente) {
        this.clave_paciente = clave_paciente;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero_int() {
        return numero_int;
    }

    public void setNumero_int(int numero_int) {
        this.numero_int = numero_int;
    }

    public int getNumero_ext() {
        return numero_ext;
    }

    public void setNumero_ext(int numero_ext) {
        this.numero_ext = numero_ext;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFk_fiscales() {
        return fk_fiscales;
    }

    public void setFk_fiscales(int fk_fiscales) {
        this.fk_fiscales = fk_fiscales;
    }

    public int getFk_expediente() {
        return fk_expediente;
    }

    public void setFk_expediente(int fk_expediente) {
        this.fk_expediente = fk_expediente;
    }
    
    
}
