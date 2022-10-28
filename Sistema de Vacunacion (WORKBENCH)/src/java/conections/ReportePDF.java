/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conections;
import java.sql.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static conections.VacunaBD.ps;
import java.io.FileOutputStream;
import clases.Vacuna;

/**
 *
 * @author 50256
 */
public class ReportePDF {
    static String ruta = System.getProperty("user.home");
    Connection con = null;
    static PreparedStatement  ps = null;
    ResultSet rs = null;
    Statement st=null;

    int diferencia_meses;
    static String driver = "com.mysql.jdbc.Driver";
    
    public void imprimir_reporte(String fecha_nacimiento){
        Document documento = new Document();
        Vacuna v1 = new Vacuna();
        diferencia_meses = v1.diferencia_fechas(fecha_nacimiento);
        int i = 0;
        try{
            i +=1;
            PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Documents/NetBeansProjects/Sistema de Vacunacion (WORKBENCH)/web/reportes/Reporte Vacunas.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Nombre de la vacuna");
            tabla.addCell("Via de aplicación");
            tabla.addCell("Edad de aplicacion");
            tabla.addCell("Numero de dosis");
            tabla.addCell("Cantidad de dosis");
            
            try{
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
             ps = con.prepareStatement("select * from vacuna;");
             rs = ps.executeQuery();
             
             if(rs.next()){
             
                 do{
                      if(Integer.parseInt(rs.getString("edad_aplicacion_meses"))>diferencia_meses){
                            tabla.addCell(rs.getString(2));
                            tabla.addCell(rs.getString(3));
                            tabla.addCell(rs.getString(4));
                            tabla.addCell(rs.getString(5));
                            tabla.addCell(rs.getString(6));
                        } 
                 }while(rs.next());
                 documento.add(tabla);
             }
            }catch(Exception e){
            
            }
            documento.close();
        }catch(Exception e){
        
        }
            
    }
    
    public String fecha_nacimiento_paciente(){
    
     String fecha_nacimiento_paciente = null;
        try{    
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
            st = con.createStatement();
            rs = st.executeQuery("SELECT fecha_nacimiento FROM paciente WHERE idpaciente=(SELECT max(idpaciente) FROM paciente);");
            while(rs.next()){
                        fecha_nacimiento_paciente = rs.getString(1);
                    }
                    
                }catch(Exception e){
                    System.out.println(e);
                }
     return fecha_nacimiento_paciente;
    }
}
