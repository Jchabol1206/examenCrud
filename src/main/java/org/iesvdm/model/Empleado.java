package org.iesvdm.model;

import java.util.Objects;

public class Empleado {
	private int codigo;
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int codigo_departamento;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public int getCodigo_departamento() {
		return codigo_departamento;
	}
	public void setCodigo_departamento(int codigo_departamento) {
		this.codigo_departamento = codigo_departamento;
	}
	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nif=" + nif + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", codigo_departamento=" + codigo_departamento + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido1, apellido2, codigo, codigo_departamento, nif, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& codigo == other.codigo && codigo_departamento == other.codigo_departamento
				&& Objects.equals(nif, other.nif) && Objects.equals(nombre, other.nombre);
	}
	
	
	

}
