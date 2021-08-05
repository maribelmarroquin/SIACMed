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
public class CatPresionArterialNormalVO {
    private int id;
    private int edad_min;
    private int edad_max;
    private char sexo;
    private float sistolica_max;
    private float sistolica_min;
    private float diastolica_max;
    private float diastolica_min;

    public CatPresionArterialNormalVO() {
    }
    
    public CatPresionArterialNormalVO(int id, int edad_min, int edad_max, char sexo, float sistolica_max, float sistolica_min, float diastolica_max, float diastolica_min) {
        this.id = id;
        this.edad_min = edad_min;
        this.edad_max = edad_max;
        this.sexo = sexo;
        this.sistolica_max = sistolica_max;
        this.sistolica_min = sistolica_min;
        this.diastolica_max = diastolica_max;
        this.diastolica_min = diastolica_min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(int edad_min) {
        this.edad_min = edad_min;
    }

    public int getEdad_max() {
        return edad_max;
    }

    public void setEdad_max(int edad_max) {
        this.edad_max = edad_max;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public float getSistolica_max() {
        return sistolica_max;
    }

    public void setSistolica_max(float sistolica_max) {
        this.sistolica_max = sistolica_max;
    }

    public float getSistolica_min() {
        return sistolica_min;
    }

    public void setSistolica_min(float sistolica_min) {
        this.sistolica_min = sistolica_min;
    }

    public float getDiastolica_max() {
        return diastolica_max;
    }

    public void setDiastolica_max(float diastolica_max) {
        this.diastolica_max = diastolica_max;
    }

    public float getDiastolica_min() {
        return diastolica_min;
    }

    public void setDiastolica_min(float diastolica_min) {
        this.diastolica_min = diastolica_min;
    }
    
    
}
