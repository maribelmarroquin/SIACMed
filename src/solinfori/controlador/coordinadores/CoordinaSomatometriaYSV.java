/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.coordinadores;

import java.util.Date;
import java.util.List;
import javax.swing.JInternalFrame;
import solinfori.modelo.dao.CatFrecuenciaRespiratoriaNormalDAO;
import solinfori.modelo.dao.CatPresionArterialNormalDAO;
import solinfori.modelo.dao.PacientesDAO;
import solinfori.modelo.vo.CatFrecuenciaRespiratoriaNormalVO;
import solinfori.modelo.vo.CatPresionArterialNormalVO;
import solinfori.modelo.vo.PacientesVO;
import solinfori.vistas.Paciente.SomatometriaYSV;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CoordinaSomatometriaYSV {
    
    private PacientesDAO pacientesDAO;
    private SomatometriaYSV somatometriaYSV;
    private CatPresionArterialNormalDAO catPresionArterialNormalDAO;
    private CatFrecuenciaRespiratoriaNormalDAO catFrecuenciaRespiratoriaNormalDAO;

    public CoordinaSomatometriaYSV(JInternalFrame somatometriaYSV) {
        this.somatometriaYSV = (SomatometriaYSV) somatometriaYSV;
    }
       
    public void retornarPaciente(String clavePaciente){
               
        pacientesDAO = new PacientesDAO();
        
        char clave = clavePaciente.charAt(0);
        int consecutivo = Integer.parseInt(clavePaciente.substring(2, clavePaciente.length()));
                
        List<PacientesVO> datosPaciente = pacientesDAO.readPatient(clave, consecutivo);
        
        for (PacientesVO pacientesVO : datosPaciente) {
            
            somatometriaYSV.getlClave().setText(String.valueOf(pacientesVO.getClave_paciente())+"_"+String.valueOf(pacientesVO.getConsecutivo()));
            somatometriaYSV.getlNombre().setText(String.valueOf(pacientesVO.getNombre())+" "+String.valueOf(pacientesVO.getApellido_pat())+" "+String.valueOf(pacientesVO.getApellido_mat()));
            somatometriaYSV.getlEdad().setText(calculaEdad(pacientesVO.getFecha_nacimiento()));
            somatometriaYSV.getlSexo().setText(getSexoString(pacientesVO.getSexo()));
            somatometriaYSV.getlDomicilio().setText(valueNull(String.valueOf(pacientesVO.getCalle()))+" no. "+
                                                    valueNull(String.valueOf(pacientesVO.getNumero_int()))+" "+
                                                    valueNull(String.valueOf(pacientesVO.getNumero_ext()))+", "+
                                                    valueNull(String.valueOf(pacientesVO.getMunicipio()))+", "+
                                                    valueNull(String.valueOf(pacientesVO.getPais())));
            somatometriaYSV.getlTelefono().setText(valueNull(String.valueOf(pacientesVO.getTelefono())));
            
            retornaRangos();
        }        
    }
    
    /**
     * Método PRIVADO que gestiona el retorno de rangos normales
     */
    private void retornaRangos(){
        
         somatometriaYSV.getSsv_lPresionArterial_rn().setText(getRangoPresion());
         somatometriaYSV.getSsv_lFrecResp_rn().setText(getRangoFR());
    }
    
    /**
     * Método PRIVADO que retorna el rango normal de presion arterial de acuerdo a la edad y el sexo.
     * @return 
     */
    private String getRangoPresion(){
        String edadConPalabraAnios = somatometriaYSV.getlEdad().getText();
        String edad = edadConPalabraAnios.substring(0, edadConPalabraAnios.length()-5);
        int sistolica_min = 0, sistolica_max = 0, diastolica_min = 0, diastolica_max = 0;
        catPresionArterialNormalDAO = new CatPresionArterialNormalDAO();
        
        List<CatPresionArterialNormalVO> presionArterialNormal = catPresionArterialNormalDAO.filteredSearchOfPAN(Integer.parseInt(edad), somatometriaYSV.getlSexo().getText().charAt(0));
        
        for (CatPresionArterialNormalVO rangoNormal : presionArterialNormal) {
            sistolica_min = (int) rangoNormal.getSistolica_min();
            sistolica_max = (int) rangoNormal.getSistolica_max();
            diastolica_min = (int) rangoNormal.getDiastolica_min();
            diastolica_max = (int) rangoNormal.getDiastolica_max();
        }
        return sistolica_min+" / "+diastolica_min+" mm Hg   -   "+sistolica_max+" / "+diastolica_max+" mm Hg";
    }
    
    private String getRangoFR(){
        String edadConPalabraAnios = somatometriaYSV.getlEdad().getText();
        String edad = edadConPalabraAnios.substring(0, edadConPalabraAnios.length()-5);
        int fr_min = 0, fr_max = 0;
        catFrecuenciaRespiratoriaNormalDAO = new CatFrecuenciaRespiratoriaNormalDAO();
        
        List<CatFrecuenciaRespiratoriaNormalVO> frecuenciaRespiratoriaNormal = catFrecuenciaRespiratoriaNormalDAO.filteredSearchOfFRN(Integer.parseInt(edad));
        
        for (CatFrecuenciaRespiratoriaNormalVO rangoNormal : frecuenciaRespiratoriaNormal) {
            fr_min = (int) rangoNormal.getFr_min();
            fr_max = (int) rangoNormal.getFr_max();
        }
        return fr_min+" resp/min   -   "+fr_max+" resp/min";
                
    }
    
    /**
     * MÉTODOS DE UTILERIAS DE  LA CLASE
     */
    
    /**
     * Metodo que retorna sexo para los "lable" de la vista.
     * @param sexo
     * @return 
     */
    private String getSexoString(char sexo){
        if(sexo == 'M'){
            return "Hombre";
        }else if(sexo == 'F'){
            return "Mujer";
        }
        return "";
    }
    
    /**
     * Método que calcula EDAD respecto a la fecha de nacimiento.
     * @param fecha
     * @return 
     */
    private String calculaEdad(Date fecha){
        long milseg_por_dia = 24 * 60 * 60 * 1000;
        Date hoy = new Date();        
        long milisegundos = hoy.getTime()-fecha.getTime();
        int dias = (int) (milisegundos/milseg_por_dia);
        long anios = dias/365;
        return String.valueOf(anios) + " años";
    }
    
    /**
     * Método que retorna CADENAS VACÍAS, para prevenir valores "null"
     * @param value
     * @return 
     */
    private String valueNull(String value){
        if(value.equals("null")){
            return "";
        }
        if(value.equals("0")){
            return "";
        }
        return value;
    }
}
