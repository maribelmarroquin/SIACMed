/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solinfori.controlador.coordinadores;

import com.toedter.calendar.JDateChooser;
import java.util.List;
import javax.swing.JOptionPane;
import solinfori.modelo.dao.PacientesDAO;
import solinfori.modelo.vo.PacientesVO;
import solinfori.vistas.Paciente.primeraVez.RegistrosPacientes;

/**
 *
 * @author Dell Optiplex 7010
 */
public class CoordinaRegistrosPacientes {
    
    private PacientesDAO pacientesDAO;
    private static boolean security;
    private static CoordinaValidaciones validaciones = new CoordinaValidaciones();
    
    
    
    public CoordinaRegistrosPacientes(){
    }
    
    /**
     * Método que retorna el listado total de pacientes registrados en el sistema
     * 
     * @param registrosPacientes 
     */
    private void listarPacientes(RegistrosPacientes registrosPacientes){
        pacientesDAO = new PacientesDAO();
        List<PacientesVO> listaPacientes= pacientesDAO.readPatientsSecure();
        
        String matris[][] = new String [listaPacientes.size()][4];
        //for()
        for (int i = 0; i < listaPacientes.size(); i++) {
            matris[i][0]=listaPacientes.get(i).getClave_paciente()+"_"+listaPacientes.get(i).getConsecutivo();
            matris[i][1]=listaPacientes.get(i).getApellido_pat()+" "+listaPacientes.get(i).getApellido_mat()+" "+listaPacientes.get(i).getNombre();
            matris[i][2]=listaPacientes.get(i).getFecha_nacimiento().toString();
            matris[i][3]=listaPacientes.get(i).getEmail();
        }
        
        registrosPacientes.getjTablePacientes().setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "Clave", "Nombre", "Fecha de nacimiento", "Correo electrónico"
            }
        ));
    }
    
    private void listarPacientesFiltrados(RegistrosPacientes registrosPacientes){
        String columnas[]={"clave_paciente", "consecutivo", "nombre", "apellido_pat", "apellido_mat", "fecha_nacimiento", "email"};
        String filtros[][] = new String[7][2];
        
        String claveConsecutivo = registrosPacientes.getTxtFiltrarPorClave().getText();
        String clave, consecutivo;
        
        if(claveConsecutivo==null || claveConsecutivo.equals("")){
            clave = "";
            consecutivo = "";
        }else{
            if(claveConsecutivo.length()<=2){
                clave = "";
                consecutivo = "";
                JOptionPane.showMessageDialog(registrosPacientes, "La clave del paciente que desea buscar, está incompleta.", "Aviso:", 0);
            }else{
                clave = String.valueOf(claveConsecutivo.charAt(0));
                consecutivo = claveConsecutivo.substring(2, claveConsecutivo.length());
            }
        }
        pacientesDAO = new PacientesDAO();
        
        JDateChooser fecha = registrosPacientes.getDchFiltrarPorFechaNac();
                
        String nombre = registrosPacientes.getTxtFiltrarPorNombre().getText();
        String ape_pat = registrosPacientes.getTxtFiltrarPorApePat().getText();
        String ape_mat = registrosPacientes.getTxtFiltrarPorApeMat().getText();
        
        String fecha_nacString;
        
        if(fecha.getDate() == null){
            fecha_nacString = "";
        }else{
            fecha_nacString = pacientesDAO.getFechaNacimiento(fecha.getDate());
        }
        
        String email = registrosPacientes.getTxtFiltrarPorEmail().getText();
               
        String valores[]={clave, consecutivo, nombre, ape_pat, ape_mat, fecha_nacString, email};

        for (int i = 0; i < columnas.length; i++) {
            filtros[i][0]=columnas[i];
            if (valores[i]==null || valores[i].equals("")) {
                filtros[i][1]="";
            }else{
                filtros[i][1]=valores[i];
            } 
        }
        List<PacientesVO> listaPacientes;
        if (valores[0].equals("") && valores[1].equals("") && valores[2].equals("") && valores[3].equals("") && valores[4].equals("") && valores[5].equals("") && valores[6].equals("")){
            listaPacientes= pacientesDAO.readPatientsSecure();
        }else{
            listaPacientes= pacientesDAO.filteredSearchOfPatient(filtros);
        }
        

        String matris[][] = new String [listaPacientes.size()][4];
       
        for (int i = 0; i < listaPacientes.size(); i++) {
            matris[i][0]=listaPacientes.get(i).getClave_paciente()+"_"+listaPacientes.get(i).getConsecutivo();
            matris[i][1]=listaPacientes.get(i).getApellido_pat()+" "+listaPacientes.get(i).getApellido_mat()+" "+listaPacientes.get(i).getNombre();
            matris[i][2]=listaPacientes.get(i).getFecha_nacimiento().toString();
            matris[i][3]=listaPacientes.get(i).getEmail();
        }

        registrosPacientes.getjTablePacientes().setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "Clave", "Nombre", "Fecha de nacimiento", "Correo electrónico"
            }
        ));       
                
    }
    
    public void alternadorBusqueda(RegistrosPacientes registrosPacientes){
        
        String claveConsecutivo = registrosPacientes.getTxtFiltrarPorClave().getText();
        String nombre = registrosPacientes.getTxtFiltrarPorNombre().getText();
        String ape_pat = registrosPacientes.getTxtFiltrarPorApePat().getText();
        String ape_mat = registrosPacientes.getTxtFiltrarPorApeMat().getText();
        JDateChooser fecha = registrosPacientes.getDchFiltrarPorFechaNac();
        String email = registrosPacientes.getTxtFiltrarPorEmail().getText();
        
        if (claveConsecutivo.equals("") && nombre.equals("") && ape_pat.equals("") && ape_mat.equals("") && fecha.getDate()==null && email.equals("")) {
            this.listarPacientes(registrosPacientes);
        }else{
            this.listarPacientesFiltrados(registrosPacientes);
        }
    }
    
    public void limpiarBusqueda(RegistrosPacientes registrosPacientes){
        registrosPacientes.getTxtFiltrarPorClave().setText("");
        registrosPacientes.getTxtFiltrarPorNombre().setText("");
        registrosPacientes.getTxtFiltrarPorApePat().setText("");
        registrosPacientes.getTxtFiltrarPorApeMat().setText("");
        registrosPacientes.getDchFiltrarPorFechaNac().setDate(null);
        registrosPacientes.getTxtFiltrarPorEmail().setText("");
        
        listarPacientes(registrosPacientes);
    }
}
