<%-- 
    Document   : buscador_paciente_individual
    Created on : 27/10/2022, 10:40:12 AM
    Author     : 50256
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Paciente"%>
<%@page import="conections.Conection"%>

<!DOCTYPE html>
<html>  
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/styles.css">
        <title>JSP Page</title>
    </head>
    <body>
       <%    
        String id_paciente = request.getParameter("id_paciente")==null ? "":request.getParameter("id_paciente");
        Paciente paciente = new Paciente();
        List<Paciente> lstPaciente = paciente.buscar_codigo(id_paciente);
        
        %>
    
        <div class="container">
        <form name="form_imprimir" method="post" > 
            <table>
                <thead>
                    <tr>
                        <th>N°</th>
                        <th>Nombre</th>
                        <th>Fecha de nacimiento</th> 
                        <th>ID del encargado</th>
                        <th>Genero</th>
                        <th>¿Cuenta con una enfermedad?</th>
                    </tr>
                </thead>
            <%  
                for (int i=0; i<lstPaciente.size(); i++){
                paciente = lstPaciente.get(i);
                            %>
                <tbody>    
                    <tr align="center">
                        <td><%=paciente.getId_paciente()%></td>
                            <td><%=paciente.getNombre_paciente()%></td>
                            <td><%=paciente.getFecha_nacimiento()%></td>
                             <td><%=paciente.getId_encargado()%></td>
                            <td><%=paciente.getGenero()%></td>                       
                            <td><%=paciente.getEnfermedad()%></td>                   
                        
                    </tr>
                            <%                                
                            }
                            %>             
                </tbody>
            </table>
         </form>
        </div>
    </body>
</html>
