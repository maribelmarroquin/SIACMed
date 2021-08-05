/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.coordinadores;

import java.awt.Font;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import solinfori.controlador.Excepciones.FieldValidationException;
import solinfori.modelo.dao.PacientesDAO;
import solinfori.modelo.logica.Sesion;
import solinfori.modelo.vo.PacientesVO;
import solinfori.vistas.Paciente.PrimeraVez;

/**
 *
 * @author Maribel Marroquín - SOLINFORI
 */
public class CoordinaPrimeraVez {
    
    private PrimeraVez primeraVez;
    private PacientesDAO pacientesDAO;
    private static Sesion sesion;
    private static CoordinaValidaciones validaciones = new CoordinaValidaciones();
    private boolean visible;
    
    public CoordinaPrimeraVez(Sesion sesion){
        this.sesion = sesion;
    }
    
    /*
    public CoordinaPrimeraVez(){
        this.primeraVez = new PrimeraVez(sesion);
    }
    */
    
    public CoordinaPrimeraVez(JInternalFrame primeraVez, Sesion sesion){
        this.primeraVez = (PrimeraVez) primeraVez;
        this.sesion = sesion;
    }
    
    
    public PrimeraVez getInstancia(){
                
        if(this.primeraVez==null){
            this.primeraVez.getlPrefijoClave().setText(sesion.usuarioLogueado().get(3)+"_");
        }
        return primeraVez;
    }
    
    public void alternaGuardarActualizar(PrimeraVez primeraVez){
        try {
            pacientesDAO = new PacientesDAO();
            PacientesVO pacientesVO = new PacientesVO();
            pacientesVO.setClave_paciente(sesion.usuarioLogueado().get(3).charAt(0));
            pacientesVO.setConsecutivo(validaciones.validarCampoInt(primeraVez.getTxtClave()));
            
            if(pacientesDAO.isPatientExistent(pacientesVO)==true){
                actualizarPaciente(primeraVez);
            }else{
                setPacienteNuevo(primeraVez);
            }
        } catch (FieldValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al arternar funcionamiento de Guardar/Actualizar.", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void setPacienteNuevo(PrimeraVez primeraVez){
        
        pacientesDAO = new PacientesDAO();
        PacientesVO paciente = new PacientesVO();
        
        try {
            paciente.setClave_paciente(sesion.usuarioLogueado().get(3).charAt(0));
            paciente.setConsecutivo(Integer.parseInt(validarPacienteExistente(pacientesDAO, primeraVez.getTxtClave())));
            paciente.setNombre(validaciones.validarCampoTxt(primeraVez.getTxtNombre()));
            paciente.setApellido_pat(validaciones.validarCampoTxt(primeraVez.getTxtApellidoPaterno()));
            paciente.setApellido_mat(validaciones.validarCampoTxt(primeraVez.getTxtApellidoMaterno()));
            paciente.setFecha_nacimiento(validaciones.validarCampoDC(primeraVez.getDcFechaNacimiento()));
            paciente.setSexo(getSexoChar(validaciones.validarCampoComboBox(primeraVez.getCbSexo())));
            paciente.setCalle(validaciones.validarCampoTxtOpc(primeraVez.getTxtCalle()));
            paciente.setNumero_int(validaciones.validarCampoIntOpc(primeraVez.getTxtNumeroInt()));
            paciente.setNumero_ext(validaciones.validarCampoIntOpc(primeraVez.getTxtNumeroExt()));
            paciente.setCodigo_postal(validaciones.validarCampoIntOpc(primeraVez.getTxtCP()));
            paciente.setMunicipio(validaciones.validarCampoTxtOpc(primeraVez.getTxtMunicipio()));
            paciente.setLocalidad(validaciones.validarCampoTxtOpc(primeraVez.getTxtLocalidad()));
            paciente.setEstado(validaciones.validarCampoTxtOpc(primeraVez.getTxtEstado()));
            paciente.setPais(validaciones.validarCampoTxtOpc(primeraVez.getTxtPais()));
            paciente.setTelefono(validaciones.validarCampoTxtOpc(primeraVez.getTxtTelefono()));
            paciente.setCelular(validaciones.validarCampoTxtOpc(primeraVez.getTxtCelular()));
            paciente.setEmail(validaciones.validarCampoTxtOpc(primeraVez.getTxtEmail()));

            pacientesDAO.writePatients(paciente);
            limpiarFormulario(primeraVez);
            
        } catch (FieldValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de llenado", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void retornarPaciente(String clavePaciente){
               
        pacientesDAO = new PacientesDAO();
        
        char clave = clavePaciente.charAt(0);
        int consecutivo = Integer.parseInt(clavePaciente.substring(2, clavePaciente.length()));
                
        List<PacientesVO> datosPaciente = pacientesDAO.readPatient(clave, consecutivo);
        
        for (PacientesVO pacientesVO : datosPaciente) {
            primeraVez.getlPrefijoClave().setText(sesion.usuarioLogueado().get(3)+"_");
            primeraVez.getTxtClave().setText(String.valueOf(pacientesVO.getConsecutivo()));
            primeraVez.getTxtNombre().setText(String.valueOf(pacientesVO.getNombre()));
            primeraVez.getTxtApellidoPaterno().setText(String.valueOf(pacientesVO.getApellido_pat()));
            primeraVez.getTxtApellidoMaterno().setText(String.valueOf(pacientesVO.getApellido_mat()));
            primeraVez.getDcFechaNacimiento().setDate(pacientesVO.getFecha_nacimiento());
            primeraVez.getCbSexo().setSelectedIndex(getSexoInt(pacientesVO.getSexo()));
            primeraVez.getTxtCalle().setText(valueNull(String.valueOf(pacientesVO.getCalle())));
            primeraVez.getTxtNumeroInt().setText(valueNull(String.valueOf(pacientesVO.getNumero_int())));
            primeraVez.getTxtNumeroExt().setText(valueNull(String.valueOf(pacientesVO.getNumero_ext())));
            primeraVez.getTxtCP().setText(valueNull(String.valueOf(pacientesVO.getCodigo_postal())));
            primeraVez.getTxtMunicipio().setText(valueNull(String.valueOf(pacientesVO.getMunicipio())));
            primeraVez.getTxtLocalidad().setText(valueNull(String.valueOf(pacientesVO.getLocalidad())));
            primeraVez.getTxtEstado().setText(valueNull(String.valueOf(pacientesVO.getEstado())));
            primeraVez.getTxtPais().setText(valueNull(String.valueOf(pacientesVO.getPais())));
            primeraVez.getTxtTelefono().setText(valueNull(String.valueOf(pacientesVO.getTelefono())));
            primeraVez.getTxtCelular().setText(valueNull(String.valueOf(pacientesVO.getCelular())));
            primeraVez.getTxtEmail().setText(valueNull(String.valueOf(pacientesVO.getEmail())));
        }        
    }
    
    public void actualizarPaciente(PrimeraVez primeraVez){
         pacientesDAO = new PacientesDAO();
        PacientesVO paciente = new PacientesVO();
        
        try {
            paciente.setClave_paciente(sesion.usuarioLogueado().get(3).charAt(0));
            paciente.setConsecutivo(validaciones.validarCampoInt(primeraVez.getTxtClave()));
            paciente.setNombre(validaciones.validarCampoTxt(primeraVez.getTxtNombre()));
            paciente.setApellido_pat(validaciones.validarCampoTxt(primeraVez.getTxtApellidoPaterno()));
            paciente.setApellido_mat(validaciones.validarCampoTxt(primeraVez.getTxtApellidoMaterno()));
            paciente.setFecha_nacimiento(validaciones.validarCampoDC(primeraVez.getDcFechaNacimiento()));
            paciente.setSexo(getSexoChar(validaciones.validarCampoComboBox(primeraVez.getCbSexo())));
            paciente.setCalle(validaciones.validarCampoTxtOpc(primeraVez.getTxtCalle()));
            paciente.setNumero_int(validaciones.validarCampoIntOpc(primeraVez.getTxtNumeroInt()));
            paciente.setNumero_ext(validaciones.validarCampoIntOpc(primeraVez.getTxtNumeroExt()));
            paciente.setCodigo_postal(validaciones.validarCampoIntOpc(primeraVez.getTxtCP()));
            paciente.setMunicipio(validaciones.validarCampoTxtOpc(primeraVez.getTxtMunicipio()));
            paciente.setLocalidad(validaciones.validarCampoTxtOpc(primeraVez.getTxtLocalidad()));
            paciente.setEstado(validaciones.validarCampoTxtOpc(primeraVez.getTxtEstado()));
            paciente.setPais(validaciones.validarCampoTxtOpc(primeraVez.getTxtPais()));
            paciente.setTelefono(validaciones.validarCampoTxtOpc(primeraVez.getTxtTelefono()));
            paciente.setCelular(validaciones.validarCampoTxtOpc(primeraVez.getTxtCelular()));
            paciente.setEmail(validaciones.validarCampoTxtOpc(primeraVez.getTxtEmail()));

            pacientesDAO.updatePatients(paciente);
            limpiarFormulario(primeraVez);
            
        } catch (FieldValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de llenado", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void limpiarFormulario(PrimeraVez primeraVez){
        primeraVez.getTxtClave().setText("");
        primeraVez.getTxtNombre().setText("");
        primeraVez.getTxtApellidoPaterno().setText("");
        primeraVez.getTxtApellidoMaterno().setText("");
        primeraVez.getDcFechaNacimiento().setDate(null);
        primeraVez.getCbSexo().setSelectedIndex(0);
        primeraVez.getTxtCalle().setText("");
        primeraVez.getTxtNumeroInt().setText("");
        primeraVez.getTxtNumeroExt().setText("");
        primeraVez.getTxtCP().setText("");
        primeraVez.getTxtMunicipio().setText("");
        primeraVez.getTxtLocalidad().setText("");
        primeraVez.getTxtEstado().setText("");
        primeraVez.getTxtPais().setText("");
        primeraVez.getTxtTelefono().setText("");
        primeraVez.getTxtCelular().setText("");
        primeraVez.getTxtEmail().setText("");
    }
    
    public void cerrarVentana(PrimeraVez primeraVez){
        limpiarFormulario(primeraVez);
        primeraVez.dispose();
    }
    
    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/
    
    private char getSexoChar(String sexo){
        if(sexo.equals("Hombre")){
            return 'M';
        }else if(sexo.equals("Mujer")){
            return 'F';
        }
        return 0;
    }
    
    private int getSexoInt(char sexo){
        if(sexo == 'M'){
            return 2;
        }else if(sexo == 'F'){
            return 1;
        }
        return 0;
    }
    
    private String validarPacienteExistente(PacientesDAO pacientesDAO, JTextField txtField) throws FieldValidationException{
        String valor = null;
        PacientesVO pacientesVO = new PacientesVO();
        pacientesVO.setClave_paciente(sesion.usuarioLogueado().get(3).charAt(0));
        pacientesVO.setConsecutivo(validaciones.validarCampoInt(txtField));
        if(pacientesDAO.isPatientExistent(pacientesVO)==true){
            String texto = "<html>El registro del paciente con clave \"<b>"+sesion.usuarioLogueado().get(3).charAt(0)+"_"+txtField.getText()+"</b>\" ya existe. Intente con otra clave.</html>";
            JLabel label = new JLabel(texto);
            label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(null, label);
            throw new FieldValidationException("No se completó el registro del paciente.");
        }
        else{
            valor = txtField.getText();
        }
        return valor;
    }
    
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
