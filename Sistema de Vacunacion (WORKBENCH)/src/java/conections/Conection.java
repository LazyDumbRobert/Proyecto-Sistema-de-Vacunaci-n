/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conections;
import java.sql.Connection;
import java.sql.DriverManager;
import clases.Encargado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 50256
 */
public class Conection {
    static PreparedStatement  ps = null;
    ResultSet rs = null;
    
    public static void connect(){
        Connection con = null;
         String driver = "com.mysql.jdbc.Driver";
         
        try{
           Class.forName(driver);
           System.out.println("Driver cargado con exito");
           try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
                if(con!=null){
                System.out.println("Conexión realizada correctamente");
            }
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
           }
           
        }catch(Exception e){
            System.out.println("Ha ocurrido un error al cargar el driver "+e.getMessage());
        }
    }
   
    public boolean insertar(Encargado encargado) throws SQLException{
        Connection con = null;
         String driver = "com.mysql.jdbc.Driver";
         
        try{
           Class.forName(driver);
           
           try{
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
           }catch(Exception e){
               System.out.println("Ha ocurrido un error "+e.getMessage());
               return false;
           }
           
           ps = con.prepareStatement("INSERT INTO `db_proyectoii`.`encargado` (`nombre`,`edad`) VALUES('"+encargado.getNombre_encargado()+"','"+encargado.getEdad_encargado()+"');");
           ps.executeQuery();
           
        }catch(Exception e){
            System.out.println("Ha ocurrido un error "+e.getMessage());
            return false;
        }finally{
            con.close();

        }
        return true;
    
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
