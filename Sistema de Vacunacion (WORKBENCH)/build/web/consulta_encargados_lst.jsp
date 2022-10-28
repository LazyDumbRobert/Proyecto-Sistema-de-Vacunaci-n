<%-- 
    Document   : consulta_vacunacion_lst
    Created on : 20/10/2022, 10:42:20 AM
    Author     : 50256
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clases.Encargado"%>
<%@page import="conections.Conection"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script type="text/javascript">
            function insertar(){
                document.location = "consulta_vacunacion.jsp";
            }
            
        </script>
    </head>
    <body>
         <%!
            public List<Encargado> buscar(){
                Encargado encargado = new Encargado();
                List<Encargado> lstEncargados = encargado.buscar();
                return lstEncargados;                 
            }

        %>
        
        <h1>REGISTROS DE ENCARGADOS</h1>
        <form name="frmLst" method="post">
            <table width="100%"> 
                <tr>
                    <td colspan="3">                        
                        <input type="button" value="Nuevo" onclick="insertar()" id="btnNuevo" />
                    </td>
                </tr>            
                
                <tr>
                    <td colspan="3">
                        
                            <tr align="center">
                                <td width="200px"><b>Id del encargado</b></td>
                                <td width="200px"><b>Nombre del encargado</b></td>
                                <td width="200px"><b>Edad del encargado</b></td>
                            </tr>
                            <%
                            List<Encargado> lstEncargados = (List<Encargado>)buscar();
                            for (int i=0; i<lstEncargados.size(); i++){
                                Encargado encargado = lstEncargados.get(i);
                            %>
                            <tr align="center">
                                <td><%=encargado.getId_encargado()%></td>
                                <td><%=encargado.getNombre_encargado()%></td>
                                <td><%=encargado.getEdad_encargado()%></td>
                                <td><a onclick="editar(<%=String.valueOf(encargado.getId_encargado())%>)" href="#">Editar</a></td>
                            </tr>
                            <%                                
                            }
                            %>
                        
                    </td>
                </tr>
            </table>            
        </form>
        
    </body>
</html>
