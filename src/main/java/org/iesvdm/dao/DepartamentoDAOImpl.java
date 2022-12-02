package org.iesvdm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Departamento;


public class DepartamentoDAOImpl extends AbstractDAOImpl implements DepartamentoDAO{

	@Override
	public void create(Departamento departamento) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        
        try {
        	conn = connectDB();


        	//1 alternativas comentadas:       
        	//ps = conn.prepareStatement("INSERT INTO fabricante (nombre) VALUES (?)", new String[] {"codigo"});        	
        	//Ver también, AbstractDAOImpl.executeInsert ...
        	//Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente. 
        	ps = conn.prepareStatement("INSERT INTO departamento (nombre, presupuesto, gastos) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            int idx = 1;
            ps.setString(idx++, departamento.getNombre());
            ps.setDouble(idx++, departamento.getPresupuesto());
            ps.setDouble(idx, departamento.getGasto());       
            int rows = ps.executeUpdate();
            if (rows == 0) 
            	System.out.println("INSERT de usuario con 0 filas insertadas.");
            
            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) 
            	departamento.setCodigo(rsGenKeys.getInt(1));
                      
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
		
	}

	@Override
	public List<Departamento> getAll() {
		Connection conn = null;
		Statement s = null;
        ResultSet rs = null;
        
        List<Departamento> listDep = new ArrayList<>(); 
        
        try {
        	conn = connectDB();

        	// Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
        	s = conn.createStatement();
            		
        	rs = s.executeQuery("Select departamento.id, departamento.nombre, departamento.presupuesto, departamento.gastos,"
        			+ "count(empleado.id_departamento) from departamento left outer join empleado on departamento.id = empleado.id_departamento group by departamento.id;");          
            while (rs.next()) {
            	Departamento dep = new Departamento();
            	int idx = 1;
            	dep.setCodigo(rs.getInt(idx++));
            	dep.setNombre(rs.getString(idx++));
            	dep.setPresupuesto(rs.getDouble(idx++));
            	dep.setGasto(rs.getDouble(idx++));
            	dep.setNumeroEmpleados(rs.getInt(idx));
            	listDep.add(dep);
            }
          
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, s, rs);
        }
        return listDep;
	}

	@Override
	public Optional<Departamento> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public void update(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
