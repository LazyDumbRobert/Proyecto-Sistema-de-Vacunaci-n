/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

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

/**
 *
 * @author 50256
 */
public class Encargado {
    public String nombre_encargado, edad_encargado, id_encargado,numero_dpi,direccion,telefono;
    static PreparedStatement  ps = null;
    ResultSet rs = null;
    public Encargado() {
    }

    public Encargado(String nombre_encargado, String edad_encargado, String numero_dpi, String direccion, String telefono) {
        this.nombre_encargado = nombre_encargado;
        this.edad_encargado = edad_encargado;
        this.numero_dpi = numero_dpi;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    //Getters

    public String getNombre_encargado() {
        return nombre_encargado;
    }

    public String getEdad_encargado() {
        return edad_encargado;
    }

    public String getId_encargado() {
        return id_encargado;
    }

    public String getNumero_dpi() {
        return numero_dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    
    //Setters

    public void setId_encargado(String id_encargado) {
        this.id_encargado = id_encargado;
    }

    public void setNombre_encargado(String nombre_encargado) {
        this.nombre_encargado = nombre_encargado;
    }

    public void setEdad_encargado(String edad_encargado) {
        this.edad_encargado = edad_encargado;
    }

    public void setNumero_dpi(String numero_dpi) {
        this.numero_dpi = numero_dpi;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public List<Encargado> buscar(){
        
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        List<Encargado> lstEncargado=null; 
        Encargado encargado = null;
        try{
           Class.forName(driver);
           
           try{
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
           }
           
           ps = con.prepareStatement("select * from encargado;");
           rs = ps.executeQuery();
           lstEncargado = new ArrayList<Encargado>();
           while(rs.next()){
               encargado = new Encargado();
               
               encargado.setId_encargado(rs.getString("id_encargado"));
                encargado.setNombre_encargado(rs.getString("nombre"));
                encargado.setEdad_encargado(rs.getString("edad"));
                encargado.setNumero_dpi(rs.getString("numero_dpi"));
                encargado.setDireccion(rs.getString("direccion"));
                encargado.setTelefono(rs.getString("numero_telefono"));
       
                lstEncargado.add(encargado);
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
        return lstEncargado;
    
    }
    
}
