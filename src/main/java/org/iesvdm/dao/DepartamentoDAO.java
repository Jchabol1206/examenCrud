package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Departamento;

public interface DepartamentoDAO {
public void create(Departamento departamento);
	
	public List<Departamento> getAll();
	public Optional<Departamento>  find(int id);
	
	public void update(Departamento departamento);
	
	public void delete(int id);
	
	

}