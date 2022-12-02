package org.iesvdm.model;

import java.util.Objects;

public class Departamento {
	private int codigo;
	private String nombre;
	private double presupuesto;
	private double gasto;
	private int numeroEmpleados;
	
	public double getGasto() {
		return gasto;
	}
	public void setGasto(double gasto) {
		this.gasto = gasto;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}
	@Override
	public String toString() {
		return "Departamento [codigo=" + codigo + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", gasto="
				+ gasto + ", numeroEmpleados=" + numeroEmpleados + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigo, gasto, nombre, numeroEmpleados, presupuesto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return codigo == other.codigo && Double.doubleToLongBits(gasto) == Double.doubleToLongBits(other.gasto)
				&& Objects.equals(nombre, other.nombre) && numeroEmpleados == other.numeroEmpleados
				&& Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto);
	}

	
	

}
