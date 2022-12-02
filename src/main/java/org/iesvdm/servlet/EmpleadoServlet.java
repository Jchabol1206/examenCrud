package org.iesvdm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesvdm.dao.DepartamentoDAO;
import org.iesvdm.dao.DepartamentoDAOImpl;
import org.iesvdm.dao.EmpleadoDAO;
import org.iesvdm.dao.EmpleadoDAOImpl;
import org.iesvdm.model.Departamento;
import org.iesvdm.model.Empleado;


@WebServlet("/empleados/*")
public class EmpleadoServlet extends HttpServlet {
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
    public EmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		
		String pathInfo = request.getPathInfo(); //
			
		if (pathInfo == null || "/".equals(pathInfo)) {
			DepartamentoDAO depDAO = new DepartamentoDAOImpl();
			
			//List<Producto> lista = prodDAO.getAll();
		//borrar	String comp= request.getParameter("filtrar-por-nombre");
			
			
//			if(comp!=null) {
//				lista=lista.stream().filter(p->p.getNombre().toLowerCase().contains(comp)).collect(Collectors.toList());	
//			}
//			
				
	
			/* borrar despues if(comp==null) {
				request.setAttribute("listaProductos", prodDAO.getAll());
			}
			else {
				request.setAttribute("listaProductos", prodDAO.getAllFiltFT(comp));	
			}*/
		//	request.setAttribute("listaDepartamentos", depDAO.getAll());
			//dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
			        		       
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
			///	DepartamentoDAO depDAO = new DepartamentoDAOImpl();

				//request.setAttribute("listaDepartamentos", depDAO.getAll());	
				//dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/crear-departamento.jsp");
        												
			
			} else if (pathParts.length == 2) {
				
				
					//dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamentos.jsp");
					        								
				
				
			} else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
				EmpleadoDAO empDAO = new EmpleadoDAOImpl();
				int id = Integer.parseInt(pathParts[2]);
				System.out.println(id);
					try {
						request.setAttribute("empleado",empDAO.find(id));
						dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editar-empleado.jsp");
						        								
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
	
		EmpleadoDAO empDAO = new EmpleadoDAOImpl();
		String codigo = request.getParameter("codigo");
		String nif = request.getParameter("nif");
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2= request.getParameter("apellido2");
		String codigoD = request.getParameter("codigo_departamento");
		Empleado emp = new Empleado();
		
		try {
			int codigoDep= Integer.parseInt(codigoD);
			int id = Integer.parseInt(codigo);
			emp.setNif(nif);
			emp.setCodigo(id);
			emp.setNombre(nombre);
			emp.setApellido1(apellido1);
			emp.setApellido2(apellido2);
			emp.setCodigo_departamento(codigoDep);
			empDAO.update(emp);
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		
	
	}
}
