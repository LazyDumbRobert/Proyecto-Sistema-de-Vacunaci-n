/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conections;
import java.sql.Connection;
import java.sql.DriverManager;
import clases.Encargado;
import static conections.Conection.ps;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.text.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import clases.Vacuna;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author 50256
 */
public class VacunaBD {
    
     static PreparedStatement  ps = null;
     ResultSet rs = null;
     int diferencia_meses;
     
      public List<Vacuna> buscar(String fecha_nacimiento_paciente){
        Vacuna v1 = new Vacuna();
        diferencia_meses = v1.diferencia_fechas(fecha_nacimiento_paciente);
        LocalDate fecha_n = null;
        Connection con;
        con = null;
        String driver = "com.mysql.jdbc.Driver";
        List<Vacuna> lstVacuna=null; 
        Vacuna vacuna = null;
        try{
           Class.forName(driver);
           
           try{
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
           }
           
           ps = con.prepareStatement("select * from vacuna;");
           rs = ps.executeQuery();
           lstVacuna = new ArrayList<Vacuna>();
           while(rs.next()){
               fecha_n = LocalDate.parse(fecha_nacimiento_paciente);
               int edad_meses,años,r_meses;
               vacuna = new Vacuna();
               
               if(Integer.parseInt(rs.getString("edad_aplicacion_meses"))>diferencia_meses){
               vacuna.setId_vacuna(rs.getString("id_vacuna"));
               vacuna.setNombre_vacuna(rs.getString("nombre_vacuna"));
               vacuna.setVia_aplicacion_vacuna(rs.getString("via_aplicacion"));
               vacuna.setEdad_aplicacion_meses(rs.getString("edad_aplicacion_meses"));
               
               edad_meses = Integer.parseInt(rs.getString("edad_aplicacion_meses"));
               do{
                   años = edad_meses/12;
                   r_meses = edad_meses%12;
                   edad_meses = 0;
               }while(edad_meses>0);
               
               fecha_n = fecha_n.plusYears(años).plusMonths(r_meses);
               vacuna.setFecha_n(fecha_n);
               
               vacuna.setNumero_dosis(rs.getString("numero_dosis"));
               vacuna.setCantidad_dosis(rs.getString("cantidad_dosis"));
               vacuna.setFecha_n(fecha_n);
                lstVacuna.add(vacuna);
               }   
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
        return lstVacuna;
    
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
