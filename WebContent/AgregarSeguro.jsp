<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.TipoSeguro" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Agregar Seguro</title>
<style>
#nav ul {
	list-style: none;
	padding: 0;
}

#nav li {
	display: inline;
	margin-right: 10px;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

form li + li {
  margin-top: 1em;
}
label {
  display: inline-block;
  width: 200px;
  text-align: left;
}

input,
textarea {
  width: 200px;
}
input,
select {
  width: 200px;
}

</style>
</head>
<body>
	<div id="header">
		<div id="nav">
			<ul>
				<li><a href="Inicio.jsp">Inicio</a></li>
				<li><a href="servletSeguro">Agregar Seguro</a></li>
				<li><a href="ListarSeguros.jsp">Listar Seguros</a></li>
			</ul>
		</div>
	</div>

	<h2>Agregar seguros</h2>
	<% 
	int idSeguro = (int) request.getAttribute("seguroProximoId");
	ArrayList<TipoSeguro> tipoSeguros = (ArrayList<TipoSeguro>) request.getAttribute("tipoSeguro");
	boolean insertado = false;
	if (request.getAttribute("insertado") != null)
	{
		insertado = (boolean) request.getAttribute("insertado");
	}
	%>
	<form action="servletSeguro" method="post">
	<ul>
		<li>
			<label for="idSeguro">Id Seguro:</label>
			<label for="idSeguro"><%=idSeguro%></label>
		</li>
		<li>
			<label for="tipoSeguro">Tipo de seguro:</label> 
			<select	id="tipoSeguro" name="tipoSeguro">
			<%for (TipoSeguro tipoSeguro : tipoSeguros) {%>
				<option value="<%=tipoSeguro.getId()%>"><%=tipoSeguro.getDescripcion()%></option>
			<%} %>
			</select>	
		</li>
		<li>
			<label for="Descripcion">Descripcion:</label> 
			<input type="text" id="txtDescripcion" name="txtDescripcion" required> 	
		</li>
		<li>		
			<label for="costoContratacion">Costo contratacion:</label> 
			<input type="text" id="txtcostoContratacion" name="txtcostoContratacion" required>			
		</li>
		<li>		
			<label for="costoMaximoAsegurado">Costo	maximo asegurado:</label>
			<input type="text" id="txtcostoMaximoAsegurado"	name="txtcostoMaximoAsegurado" required>	
		</li>
		<li>
			<input type="submit" name="btnAceptar" value="Aceptar" style="">
		</li>
	</ul>
	</form>

	<%if(insertado) {%>
	<h3>El usuario fue insertado con exito!</h3>
	<%}%>
</body>
</html>