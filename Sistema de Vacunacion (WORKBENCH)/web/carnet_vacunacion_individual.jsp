<%-- 
    Document   : buscador_paciente_individual
    Created on : 27/10/2022, 10:40:12 AM
    Author     : 50256
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Paciente"%>
<%@page import="clases.Vacuna"%>
<%@page import="conections.Conection"%>
<%@page import="conections.VacunaBD"%>

<!DOCTYPE html>
<html>  
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/styles.css">
        <title>Horario de vacunas</title>
    </head>
    <body>
       <%    
        String id_paciente = request.getParameter("id_paciente")==null ? "":request.getParameter("id_paciente");
        Vacuna v1 = new Vacuna();
        List<Vacuna> lstVacuna = v1.buscar_por_idpaciente(id_paciente);
        
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
                        <th>Proxima fecha de aplicación</th>
                    </tr>
                </thead>
               <%  
                    for (int i=0; i<lstVacuna.size(); i++){
                    v1 = lstVacuna.get(i);
                            %>
                <tbody>    
                    <tr align="center">
                        <td><%=v1.getId_vacuna()%></td>
                            <td><%=v1.getNombre_vacuna()%></td>
                            <td><%=v1.getVia_aplicacion_vacuna()%></td>
                             <td><%=v1.getEdad_aplicacion_meses()+" meses"%></td>
                            <td><%=v1.getNumero_dosis()%></td>                       
                            <td><%=v1.getCantidad_dosis()%></td>
                            <td><%=v1.getFecha_n()%></td>
                        
                    </tr>
                            <%                                
                            }
                            %> 
            </table>
         </form>
        </div>
    </body>
</html>
