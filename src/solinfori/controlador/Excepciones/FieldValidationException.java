/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.Excepciones;

/**
 *
 * @author Dell Optiplex 7010
 */
public class FieldValidationException extends Exception{
   
    public FieldValidationException(String mensaje) {
        super(mensaje);
    }
}
