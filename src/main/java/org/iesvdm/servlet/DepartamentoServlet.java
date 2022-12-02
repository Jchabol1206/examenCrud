package org.iesvdm.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesvdm.dao.DepartamentoDAO;
import org.iesvdm.dao.DepartamentoDAOImpl;
import org.iesvdm.model.Departamento;


/**
 * Servlet implementation class ProductosServlet
 */
@WebServlet("/departamentos/*")
public class DepartamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	
	/**
	 * HTTP Method: GET
	 * Paths: 
	 * 		/productos/{index} -muestra listado principal con operaciones CRUD
	 * 		/productos/{id}	- ver detalle de producto con id
	 * 		/productos/edit/{id}	- editar producto con {id}
	 * 		/productos/create	- crear un producto nuevo
	 */		
    public DepartamentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		
		String pathInfo = request.getPathInfo(); //
			
		if (pathInfo == null || "/".equals(pathInfo)) {
			DepartamentoDAO depDAO = new DepartamentoDAOImpl();
			
			List<Departamento> lista = depDAO.getAll();
			String min= request.getParameter("min");
			String max= request.getParameter("max");
			System.out.println(min);
			System.out.println(max);
			
		if(min!=null && max!=null) {
			lista=lista.stream().filter(p->{
			double presupuestoA= p.getPresupuesto()-p.getGasto();
			System.out.println(presupuestoA);
			boolean retorno= false;
			if(presupuestoA<Double.valueOf(max) && presupuestoA>Double.valueOf(min)) {
				retorno=true;
			}
			System.out.println(retorno);
			return retorno;
			}
			).collect(Collectors.toList());	
			
			request.setAttribute("listaDepartamentos", depDAO.getAll());
			}else {
				request.setAttribute("listaDepartamentos", depDAO.getAll());
			}
			
			
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
			        		       
		} else {
			// GET
			// 		/fabricantes/{id}
			// 		/fabricantes/{id}/
			// 		/fabricantes/edit/{id}
			// 		/fabricantes/edit/{id}/
			// 		/fabricantes/create
			// 		/fabricantes/create/
			
			pathInfo = pathInfo.replaceAll("/$", "");
			String[] pathParts = pathInfo.split("/");
			
			if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
				DepartamentoDAO depDAO = new DepartamentoDAOImpl();

				request.setAttribute("listaDepartamentos", depDAO.getAll());	
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/crear-departamento.jsp");
        												
			
			} else if (pathParts.length == 2) {
				
				
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
					        								
				
				
			} else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
			
				try {
				
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
					        								
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
				}
				
				
			} else {
				
				System.out.println("Opción POST no soportada.");
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
			
			}
		}
		dispatcher.forward(request, response);	
		}
		

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String __method__ = request.getParameter("__method__");
		
		if (__method__ == null) {
			// Crear uno nuevo
			DepartamentoDAO depDAO = new DepartamentoDAOImpl();
			
			String nombre = request.getParameter("nombre");
			String presupuesto = request.getParameter("presupuesto");
			String gasto = request.getParameter("gasto");
			double gastos = Double.parseDouble(gasto);
			double presupuestos = Double.parseDouble(presupuesto);

			Departamento nuevoDep = new Departamento();
			nuevoDep.setNombre(nombre);		//toDO Poner mas cosas para que pueda modificarse
			nuevoDep.setPresupuesto(presupuestos);
			nuevoDep.setGasto(gastos);
			depDAO.create(nuevoDep);			
			
		} else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {			
			// Actualizar uno existente
			//Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
			doPut(request, response);
			
		
		} else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {			
			// Actualizar uno existente
			//Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
			doDelete(request, response);
			
			
			
		} else {
			
			System.out.println("Opción POST no soportada.");
			
		}
		
		response.sendRedirect("/web_empleados/departamentos");
		
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		
	
	}
}
