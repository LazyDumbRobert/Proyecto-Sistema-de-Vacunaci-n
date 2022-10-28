<%-- 
    Document   : Prueba_logica_vacunacion
    Created on : 21/10/2022, 04:44:53 PM
    Author     : 50256
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Vacuna"%>
<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="conections.VacunaBD"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/styles.css">
        <title>JSP Page</title>
       
    </head>
    <body>
       
        <%!

            ResultSet rs= null;
            String fecha_nacimiento_paciente = null;
            public List<Vacuna> buscar(){
                try{
                    Connection con=null;
                    Statement st=null;
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
                
                
                VacunaBD v1 = new VacunaBD();
                
                List<Vacuna> lstVacuna = v1.buscar(fecha_nacimiento_paciente);
                return lstVacuna;                 
            }
        
        %>
       
        <div class="container">
        <form name="form_imprimir" method="post" > 
            <table>
                <thead>
                    <tr>
                        <th>N°</th>
                        <th>Nombre de vacuna</th>
                        <th>Via de aplicación</th> 
                        <th>Edad de aplicacion en meses</th>
                        <th>Numero de dosis</th>
                        <th>Cantidad de dosis</th>
                    </tr>
                </thead>
                        <%
                            List<Vacuna> lstVacuna = (List<Vacuna>)buscar();
                            for (int i=0; i<lstVacuna.size(); i++){
                                Vacuna vacuna = lstVacuna.get(i);
                            %>
                <tbody>    
                    <tr align="center">
                        <td><%=vacuna.getId_vacuna()%></td>
                            <td><%=vacuna.getNombre_vacuna()%></td>
                            <td><%=vacuna.getVia_aplicacion_vacuna()%></td>
                             <td><%=vacuna.getEdad_aplicacion_meses()+" meses"%></td>
                            <td><%=vacuna.getNumero_dosis()%></td>                       
                            <td><%=vacuna.getCantidad_dosis()%></td>                   
                        
                    </tr>
                            <%                                
                            }
                            %> 
            </table>
         </form>
        </div>
    </body>
</html>
