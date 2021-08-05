/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.modelo.vo;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CatFrecuenciaRespiratoriaNormalVO {
    
    private int id_frecuenciarespiratoria;
    private float edad_min;
    private float edad_max;
    private int fr_min;
    private int fr_max;

    public CatFrecuenciaRespiratoriaNormalVO() {
    }
    
    public CatFrecuenciaRespiratoriaNormalVO(int id_frecuenciarespiratoria, float edad_min, int fr_min, int fr_max) {
        this.id_frecuenciarespiratoria = id_frecuenciarespiratoria;
        this.edad_min = edad_min;
        this.fr_min = fr_min;
        this.fr_max = fr_max;
    }

    public int getId_frecuenciarespiratoria() {
        return id_frecuenciarespiratoria;
    }

    public void setId_frecuenciarespiratoria(int id_frecuenciarespiratoria) {
        this.id_frecuenciarespiratoria = id_frecuenciarespiratoria;
    }

    public float getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(float edad_min) {
        this.edad_min = edad_min;
    }

    public float getEdad_max() {
        return edad_max;
    }

    public void setEdad_max(float edad_max) {
        this.edad_max = edad_max;
    }

    public int getFr_min() {
        return fr_min;
    }

    public void setFr_min(int fr_min) {
        this.fr_min = fr_min;
    }

    public int getFr_max() {
        return fr_max;
    }

    public void setFr_max(int fr_max) {
        this.fr_max = fr_max;
    }
    
}
