<%-- 
    Document   : encargados_eixstentes_consulta
    Created on : 26/10/2022, 04:11:38 PM
    Author     : 50256
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Paciente"%>
<%@page import="conections.Conection"%>
<link rel="stylesheet" href="CSS/styles.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            public List<Encargado> buscar(){
                Encargado encargado = new Encargado();
                List<Encargado> lstEncargados = encargado.buscar();
                return lstEncargados;                 
            }

        %>>
        <div class="container">
        <table>
            <thead>
                <tr>
                    <th>N°</th>
                    <th>Nombre del encargado</th>
                    <th>Edad del encargado</th>
                    <th>Numero de DPI</th>
                    <th>Direccion</th>
                    <th>Numero de telefono</th>
                </tr>
            </thead>
            <%
                            List<Encargado> lstEncargados = (List<Encargado>)buscar();
                            for (int i=0; i<lstEncargados.size(); i++){
                                Encargado encargado = lstEncargados.get(i);
            %>
            <tbody>

                         <tr align="center">
                                <td><%=encargado.getId_encargado()%></td>
                                <td><%=encargado.getNombre_encargado()%></td>
                                <td><%=encargado.getEdad_encargado()%></td>
                                <td><%=encargado.getNumero_dpi()%></td>
                                <td><%=encargado.getDireccion()%></td>
                                <td><%=encargado.getTelefono()%></td>
                                
                            </tr>
                            <%                                
                            }
                            %>            
            </tbody>
        </table>
    </div>
    
    </body>
</html>
