<%-- 
    Document   : consulta_vacunacion
    Created on : 20/10/2022, 09:32:19 AM
    Author     : 50256
--%>
<%@page import="conections.Conection"%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Paciente"%>
<%@page import="conections.ReportePDF"%>
<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/consulta_vacunacion.css">
        <title>Registro y consulta de vacunación</title>
        
        
    </head>
    <body>
       
        
        
        <section>
        <h1>Registro de paciente y consulta</h1>    
        
        <form name="form_vacunacion" method="post">
            <table width="100%">
                <tr>
                    <td><input type="text" name="nombre_encargado" size="50" placeholder="Ingrese nombre del encargado" class="controls" required></td>
                </tr>
                
                <tr>
                    
                    <td><input type="text" name="edad_encargado"  class="controls" placeholder="Ingrese edad del encargado" required></td>
                
                </tr>
                <tr>
                   
                    <td><input type="text" name="numero_dpi_encargado"  class="controls" placeholder="Ingrese numero de DPI del encargado" required></td>
                </tr>
                <tr>
                   
                    <td><input type="text" name="direccion_encargado" class="controls" placeholder="Ingrese la dirección del encargado" required></td>
                </tr>
                <tr>
                    
                    <td><input type="text" name="numero_telefono_encargado" class="controls" placeholder="Ingrese el numero de telefono del encargado" required></td>
                </tr>
                <tr>
                    
                    <td><input type="text" name="nombre_paciente"  class="controls" placeholder="Ingrese el nombre del paciente" required></td>
                </tr>
                <tr>
                    
                    <td><input type="date" name="fecha_nacimiento_paciente" class="controls" required></td>
                </tr>
                <tr>
                    
                    <td>
                        <select name="box_genero" id="genero" class="controls" required>

                        <option value="Masculino">Hombre</option>
                        <option value="Femenino">Mujer</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    
                    <td>
                        
                        <select name="box_enfermedad" id="enfermedad" required>
                        <option value="Si">Si</option>
                        <option value="No">No</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_btn"><button type="submit" name="insertar" class="btn-primary">Consultar</button></td>
                </tr>
            </table>
        </form>
    </section>
        
        <%
            if(request.getParameter("insertar")!=null){
                Paciente p1 = new Paciente(request.getParameter("nombre_paciente"),request.getParameter("fecha_nacimiento_paciente"),request.getParameter("box_genero"),request.getParameter("box_enfermedad"));
                Encargado e1 = new Encargado(request.getParameter("nombre_encargado"),request.getParameter("edad_encargado"),request.getParameter("numero_dpi_encargado"),request.getParameter("direccion_encargado"),request.getParameter("numero_telefono_encargado"));
                   
                String id_encargado = null;
                ResultSet rs= null;
                
                try{
                    Connection con=null;
                    Statement st=null;
                    Statement st2=null;
                    Statement st3 = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_proyectoii?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "200319156156561");
                    
                    //INSERTAR ENCARGADO
                    st = con.createStatement();
                    st.executeUpdate("INSERT INTO `db_proyectoii`.`encargado`(`nombre`,`edad`,`numero_dpi`,`direccion`,`numero_telefono`)VALUES('"+e1.getNombre_encargado()+"','"+e1.getEdad_encargado()+"','"+e1.getNumero_dpi()+"','"+e1.getDireccion()+"','"+e1.getTelefono()+"');");
                    
                    //
                    
                    //INSERTAR PACIENTE
                    st2 = con.createStatement();
                    rs = st2.executeQuery("SELECT * FROM encargado WHERE id_encargado=(SELECT max(id_encargado) FROM encargado);");
                    while(rs.next()){
                        id_encargado = rs.getString(1);
                    }                    
              
                    st3 = con.createStatement();
                    st3.executeUpdate("INSERT INTO `db_proyectoii`.`paciente`(`nombre_paciente`,`fecha_nacimiento`,`id_encargado`,`genero`,`enfermedad`)VALUES('"+p1.getNombre_paciente()+"','"+p1.getFecha_nacimiento()+"','"+id_encargado+"','"+p1.getGenero()+"','"+p1.getEnfermedad()+"');");                  
                    response.sendRedirect("vacunacion_lst.jsp");
                     String fecha_nacimiento;

                }catch(Exception e){
                         out.print(e);
                }
            }
        
        %>
        
      
        <% /*
            String fecha_nacimiento;
            ReportePDF myreporte = new ReportePDF();
            fecha_nacimiento = myreporte.fecha_nacimiento_paciente();
            myreporte.imprimir_reporte(fecha_nacimiento);
            */
        %>
        
    </body>
</html>
