/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.coordinadores;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import solinfori.controlador.Excepciones.FieldValidationException;

/**
 *
 * @author Dell Optiplex 7010
 * 
 * Clase que permite la validación de campor de formulario.
 */
public class CoordinaValidaciones {
    
    /**
     * Método que valida campos de texto obligatorios.
     * @param txtField
     * @return
     * @throws FieldValidationException 
     */
    public String validarCampoTxt(JTextField txtField) throws FieldValidationException{
        String valor = null;
        if(txtField.getText().equals("")){
            String texto = "<html>El valor del campo \"<b>"+txtField.getName()+"</b>\" es obligatorio.</html>";
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
    
    /**
     * Método que valida campos de fecha obligatorios
     * @param dateChooser
     * @return
     * @throws FieldValidationException 
     */
    public Date validarCampoDC(JDateChooser dateChooser) throws FieldValidationException{
        Date valor = null;
        if(dateChooser.getDate() == null){
            String texto = "<html>El valor del campo \"<b>"+dateChooser.getName()+"</b>\" es obligatorio.</html>";
            JLabel label = new JLabel(texto);
            label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(null, label);
            throw new FieldValidationException("No se completó el registro del paciente.");
        }
        else{
            valor = dateChooser.getDate();
        }
        return valor;
    }
    
    /**
     * Método que valida campor de Texto opcionales.
     * @param txtField
     * @return 
     */
    public String validarCampoTxtOpc(JTextField txtField){
        String valor = null;
        if(txtField.getText().equals(""))
            valor = null;
        else
            valor = txtField.getText();
        return valor;
    }
    
    /**
     * Método que valida campos numéricos obligatorios
     * @param txtField
     * @return
     * @throws FieldValidationException 
     */
    public int validarCampoInt(JTextField txtField)throws FieldValidationException{          
        int valor = 0;
        if(txtField.getText().equals("")){
            String texto = "<html>El valor del campo \"<b>"+txtField.getName()+"</b>\" es obligatorio.</html>";
            JLabel label = new JLabel(texto);
            label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(null, label);
            throw new FieldValidationException("No se completó el registro del paciente.");
        }
        else{
            valor = Integer.parseInt(txtField.getText());
        }
        return valor;
    }
    
    /**
     * Método que valida los campos numéricos opcionales.
     * @param txtField
     * @return 
     */
    public int validarCampoIntOpc(JTextField txtField){          
        int valor = 0;
        if(txtField.getText().equals(""))
            valor=0;
        else
            valor = Integer.parseInt(txtField.getText());
        return valor;
    }
    
    /**
     * Método que valida el campo jComboBox Obligatorio.
     * @param comboBox
     * @return
     * @throws FieldValidationException 
     */
    public String validarCampoComboBox(JComboBox comboBox) throws FieldValidationException{
        String valor = null;
        if(comboBox.getSelectedItem().toString().equals("Seleccionar")){
          String texto = "<html>El valor del campo \"<b>"+comboBox.getName()+"</b>\" es obligatorio.</html>";
            JLabel label = new JLabel(texto);
            label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            JOptionPane.showMessageDialog(null, label);
            throw new FieldValidationException("No se completó el registro del paciente.");
        }
        else{
            valor = comboBox.getSelectedItem().toString();
        }
        return valor;
    } 
}
