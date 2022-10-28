<%-- 
    Document   : buscador_pacientes
    Created on : 27/10/2022, 09:38:04 AM
    Author     : 50256
--%>
<%@page import="clases.Encargado"%>
<%@page import="clases.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="conections.Conection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="CSS/buscador_pacientes.css">
    <title>Filtro de elementos</title>
    <script type="text/javascript">
        function editar(id_paciente){  
               document.form_buscador.action = "buscador_paciente_individual.jsp?id_paciente="+id_paciente;
               document.form_buscador.submit();          
        }
        function imprimir_boleta(id_paciente){
            document.form_buscador.action= "carnet_vacunacion_individual.jsp?id_paciente="+id_paciente;
            document.form_buscador.submit(); 
            
        }
    </script>
    
</head>
<body>
    
    <%!
            public List<Paciente> buscar(){
                Paciente paciente = new Paciente();
                List<Paciente> lstPaciente = paciente.buscar();
                return lstPaciente;                 
            }

        %>
        
<section id="contenedor-main">
    <form method="post" name="form_buscador">
           
    <div>
        <input type="text" name="buscador" id="buscador" placeholder="Buscar...">

        <ul id="listaArticulos">
            <%  
                    List<Paciente> lstPaciente = (List<Paciente>)buscar();
                    for (int i=0; i<lstPaciente.size(); i++){
                           Paciente paciente = lstPaciente.get(i);
                    
                            %>
                            <li class="articulo">
                                <a><%=paciente.getNombre_paciente()%></a>
                                <a onclick="imprimir_boleta(<%=String.valueOf(paciente.getId_paciente())%>)">Imprimir boleta</a>
                                <a onclick="editar(<%=String.valueOf(paciente.getId_paciente())%>)">Visualizar datos</a>
                            </li>
                            
                            <%                                
                            }
                            %> 
        </ul>

    </div>
</form>

</section>
        <script>
            
            
            document.addEventListener("keyup", e=>{

        if (e.target.matches("#buscador")){

        if (e.key ==="Escape")e.target.value = ""

      document.querySelectorAll(".articulo").forEach(paciente =>{

          paciente.textContent.toLowerCase().includes(e.target.value.toLowerCase())
            ?paciente.classList.remove("filtro")
            :paciente.classList.add("filtro")
      })

  }


})
            
        </script>
</body>
</html>