package clases;

import static clases.Encargado.ps;
import conections.Conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 50256
 */
public class Paciente {
    String nombre_paciente, fecha_nacimiento, id_encargado, id_paciente,genero,enfermedad;
    static PreparedStatement  ps = null;
    ResultSet rs = null;
    public Paciente() {
    }

    public Paciente(String nombre_paciente, String fecha_nacimiento,String genero, String enfermedad) {
        this.nombre_paciente = nombre_paciente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.enfermedad = enfermedad;
    }
    
    //Getters

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getId_encargado() {
        return id_encargado;
    }
    
      public String getId_paciente() {
        return id_paciente;
    }

    public String getGenero() {
        return genero;
    }

    public String getEnfermedad() {
        return enfermedad;
    }
     
      
    //Setters

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setId_encargado(String id_encargado) {
        this.id_encargado = id_encargado;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
     
    public List<Paciente> buscar(){
        
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        List<Paciente> lstPaciente=null; 
        Paciente paciente = null;
        try{
           Class.forName(driver);
           
           try{
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
           }
           
           ps = con.prepareStatement("select * from paciente;");
           rs = ps.executeQuery();
           lstPaciente = new ArrayList<Paciente>();
           while(rs.next()){
               paciente = new Paciente();
               
               paciente.setId_paciente(rs.getString("idpaciente"));
               paciente.setNombre_paciente(rs.getString("nombre_paciente"));
               paciente.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
               paciente.setId_encargado(rs.getString("id_encargado"));
               paciente.setGenero(rs.getString("genero"));
               paciente.setEnfermedad(rs.getString("enfermedad"));
               
             
       
                lstPaciente.add(paciente);
           }
           
        }catch(Exception e){
            System.out.println("Ha ocurrido un error "+e.getMessage());
           
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return lstPaciente;
    
    }
  
    public List<Paciente> buscar_codigo(String id_paciente){
        
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        List<Paciente> lstPaciente=null; 
        Paciente paciente = null;
        try{
           Class.forName(driver);
           
           try{
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
           }
           
           ps = con.prepareStatement("select * from paciente where idpaciente = '"+id_paciente+"';");
           rs = ps.executeQuery();
           lstPaciente = new ArrayList<Paciente>();
           while(rs.next()){
               paciente = new Paciente();
               
               paciente.setId_paciente(rs.getString("idpaciente"));
               paciente.setNombre_paciente(rs.getString("nombre_paciente"));
               paciente.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
               paciente.setId_encargado(rs.getString("id_encargado"));
               paciente.setGenero(rs.getString("genero"));
               paciente.setEnfermedad(rs.getString("enfermedad"));
                     
                lstPaciente.add(paciente);
           }
           
        }catch(Exception e){
            System.out.println("Ha ocurrido un error "+e.getMessage());
           
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return lstPaciente;
    
    }
}
