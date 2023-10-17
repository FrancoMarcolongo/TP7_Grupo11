<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dominio.Seguro" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.DaoSeguro" %>
<%@page import="dominio.TipoSeguro" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Seguros</title>
<style>
	#nav ul {
            list-style: none;
            padding: 0;
        }
        #nav li {
            display: inline;
            margin-right: 10px;
        }
</style>
</head>
<body>
	<div id="header">
        <div id="nav"><ul>
                <li><a href="Inicio.jsp">Inicio</a></li>
                <li><a href="servletSeguro">Agregar Seguro</a></li>
                <li><a href="ListarSeguros.jsp">Listar Seguros</a></li>
            </ul>
        </div>
    </div>
 
<div>
Buscar tipo de seguro
<select id="Tiposeguro" name="Tipo de seguro">
</select>

<input type="submit" value="Filtar" name="btsBuscar">
</div>
 <table border="1">
        <tr>
  <% 
   ArrayList<Seguro> listaSeguros = null;
	if(request.getAttribute("listaU")!=null)
	{
		///listaUsuarios = (<seguro>) request.getAttribute("listaU");
	}
	else
	{
		DaoSeguro seg = new DaoSeguro();
		listaSeguros=seg.listarSeguros();	
	}
 %>          
           
<table border="1">
        <tr>
            <th>Id seguro</th>
            <th>Descripcion</th>
            <th>Descripcion de tipo de seguro</th>
            <th>Costo Contratacion</th>
            <th>Costo maximo Asegurado</th>
        </tr>
        <tr>
        <% if(listaSeguros!=null)
        for(Seguro user : listaSeguros)
        {%>
        	<td><%=user.getIdSeguro() %></td>
            <td><%=user.getDescripcion()%></td>
            <td><%=user.getCostoContratacion()%></td>
            <td><%=user.getCostoContratacion()%></td>
            <td><%=user.getCostoAsegurado()%></td>
        	
         <% } %>
            
        </tr>
    </table>
</table>
</body>
</html>