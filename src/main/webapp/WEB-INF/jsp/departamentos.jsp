<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.iesvdm.model.Departamento"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Departamentos</title>
<style>
.clearfix::after {
	content: "";
	display: block;
	clear: both;
}

</style>
</head>
<body>

	<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
		<div class="clearfix">
			<div style="float: left; width: 50%">
				<h1>Departamentos</h1>
			</div>
			<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
					<form action="/web_empleados/departamentos/">
						<input type="number" name="min">
						<input type="number" name="max">
							<input type="submit" value="Buscar">
					</form>
				
						<form action="/web_empleados/departamentos/crear">
							<input type="submit" value="Crear">
						</form>
					</div>
					
				
			</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
		<div class="clearfix">
			<div style="float: left;width: 16.4%">Código</div>
			<div style="float: left;width: 16.4%">Nombre</div>
			<div style="float: left;width: 16.4%">Presupuesto</div>
			<div style="float: left;width: 16.4%">Gastos</div>
			<div style="float: left;width: 16.4%">#Empleados</div>
			<div style="float: none;width: auto;overflow: hidden;">Acción</div>
		</div>
		<div class="clearfix">
			<hr/>
		</div>
	<% 
        if (request.getAttribute("listaDepartamentos") != null) {
            List<Departamento> listaDepartamentos = (List<Departamento>)request.getAttribute("listaDepartamentos");
            System.out.println(listaDepartamentos.toString());
            for (Departamento departamento : listaDepartamentos) {
    %>

		<div style="margin-top: 6px;" class="clearfix">
			<div style="float: left;width: 16.4%"><%= departamento.getCodigo()%></div>
			<div style="float: left;width: 16.4%"><%= departamento.getNombre()%></div>
			<div style="float: left;width: 16.4%"><%= departamento.getPresupuesto()%></div>
			<div style="float: left;width: 16.4%"><%= departamento.getGasto()%></div>
			<div style="float: left;width: 16.4%"><%= departamento.getNumeroEmpleados()%></div>
			<div style="float: none;width: auto;overflow: hidden;">
				<form action="/web_empleados/departamentos/<%= departamento.getCodigo()%>" style="display: inline;">
    				<input type="submit" value="Ver Detalle" />
				</form>
			
				<form action="/web_empleados/departamentos/editar/<%= departamento.getCodigo()%>" style="display: inline;">
    				<input type="submit" value="Editar" />
				</form>
				<form action="/web_empleados/departamentos/borrar/" method="post" style="display: inline;">
					<input type="hidden" name="__method__" value="delete"/>
					<input type="hidden" name="codigo" value="<%= departamento.getCodigo()%>"/>
    				<input type="submit" value="Eliminar" />
				</form>
				</div>
					</div>
			<% }
			
			%>
			
	

	<% 
            }
         else { 
    %>
		No hay registros de fabricante
	<% } %>
	</div>

</body>
</html>