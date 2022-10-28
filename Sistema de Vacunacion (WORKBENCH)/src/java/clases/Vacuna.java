/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import conections.VacunaBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 * @author 50256
 */
public class Vacuna {
    public String id_vacuna, nombre_vacuna, via_aplicacion_vacuna, edad_aplicacion_meses, numero_dosis, cantidad_dosis;
    public LocalDate fecha_n;
     static PreparedStatement  ps = null;
     ResultSet rs = null;
     int diferencia_meses;
    //CONSTRUCTOR

    public Vacuna() {
    }

    public Vacuna(String id_vacuna, String nombre_vacuna, String via_aplicacion_vacuna, String edad_aplicacion_meses, String numero_dosis, String cantidad_dosis) {
        this.id_vacuna = id_vacuna;
        this.nombre_vacuna = nombre_vacuna;
        this.via_aplicacion_vacuna = via_aplicacion_vacuna;
        this.edad_aplicacion_meses = edad_aplicacion_meses;
        this.numero_dosis = numero_dosis;
        this.cantidad_dosis = cantidad_dosis;
    }
    
    //GETTERS

    public LocalDate getFecha_n() {
        return fecha_n;
    }

    public String getId_vacuna() {
        return id_vacuna;
    }

    public String getNombre_vacuna() {
        return nombre_vacuna;
    }

    public String getVia_aplicacion_vacuna() {
        return via_aplicacion_vacuna;
    }

    public String getEdad_aplicacion_meses() {
        return edad_aplicacion_meses;
    }

    public String getNumero_dosis() {
        return numero_dosis;
    }

    public String getCantidad_dosis() {
        return cantidad_dosis;
    }
    
    //SETTERS

    public void setId_vacuna(String id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public void setNombre_vacuna(String nombre_vacuna) {
        this.nombre_vacuna = nombre_vacuna;
    }

    public void setVia_aplicacion_vacuna(String via_aplicacion_vacuna) {
        this.via_aplicacion_vacuna = via_aplicacion_vacuna;
    }

    public void setEdad_aplicacion_meses(String edad_aplicacion_meses) {
        this.edad_aplicacion_meses = edad_aplicacion_meses;
    }

    public void setNumero_dosis(String numero_dosis) {
        this.numero_dosis = numero_dosis;
    }

    public void setCantidad_dosis(String cantidad_dosis) {
        this.cantidad_dosis = cantidad_dosis;
    }

    public void setFecha_n(LocalDate fecha_n) {
        this.fecha_n = fecha_n;
    }
    
    public int diferencia_fechas(String fecha_nacimiento){
        LocalDate fecha_ahora = LocalDate.now();
        LocalDate fecha_n = LocalDate.parse(fecha_nacimiento);

        
        LocalDate f1 = LocalDate.of(fecha_n.getYear(), fecha_n.getMonth(), fecha_n.getDayOfMonth());
        LocalDate f2 = LocalDate.now(); 
        Period period = Period.between(f1, f2);
        
        return period.getMonths()+(period.getYears()*12);
        
    }
    
    
    
     public List<Vacuna> buscar_por_idpaciente(String idpaciente){
            String fecha_nacimiento_paciente = null;
                try{
                    Connection con=null;
                    Statement st=null;
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT fecha_nacimiento FROM paciente WHERE idpaciente='"+idpaciente+"';");
                    while(rs.next()){
                        fecha_nacimiento_paciente = rs.getString(1);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                
                
                VacunaBD v1 = new VacunaBD();
                
                List<Vacuna> lstVacuna = v1.buscar(fecha_nacimiento_paciente);
                return lstVacuna;                 
            }
    
}
