package org.iesvdm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Empleado;


public class EmpleadoDAOImpl extends AbstractDAOImpl implements EmpleadoDAO{

	@Override
	public void create(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Empleado> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Empleado> find(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = connectDB();
        	
        	ps = conn.prepareStatement("SELECT * FROM empleado WHERE id = ?");
        	
        	int idx =  1;
        	ps.setInt(idx, id);
        	
        	rs = ps.executeQuery();
        	
        	if (rs.next()) {
        		Empleado empl = new Empleado();
        		idx = 1;
        		empl.setCodigo(rs.getInt(idx++));
        		empl.setNif(rs.getString(idx++));
        		empl.setNombre(rs.getString(idx++));
        		empl.setApellido1(rs.getString(idx++));
        		empl.setApellido2(rs.getString(idx++));
        		empl.setCodigo_departamento(rs.getInt(idx));
        		
        		return Optional.of(empl);
        	}
        	
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
        
        return Optional.empty();
	}

	@Override
	public void update(Empleado empleado) {

		Connection conn = null;
		PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = connectDB();
        	
        	ps = conn.prepareStatement("UPDATE empleado SET nif=?, nombre = ?, apellido1=?, apellido2=?, id_departamento=?  WHERE id = ?");
        	int idx = 1;
        	ps.setString(idx++, empleado.getNif());
        	ps.setString(idx++, empleado.getNombre());	
        	ps.setString(idx++, empleado.getApellido1());
        	ps.setString(idx++, empleado.getApellido2());
        	ps.setInt(idx++, empleado.getCodigo_departamento());
        	ps.setInt(idx, empleado.getCodigo());
        	
        	int rows = ps.executeUpdate();
        	
        	if (rows == 0) 
        		System.out.println("Update de fabricante con 0 registros actualizados.");
        	
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            closeDb(conn, ps, rs);
        }
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
