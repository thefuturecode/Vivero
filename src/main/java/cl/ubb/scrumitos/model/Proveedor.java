package cl.ubb.scrumitos.model;

import javax.persistence.Entity;

@Entity
public class Proveedor {
	private int id;
	private String rut;
	private String direccion;
	private String telefono;
	private String email;
	private String nombre;
	private String estado;

	public Proveedor(int id, String rut, String direccion, String telefono, String email, String nombre, String estado) {
		this.id = id;
		this.rut = rut;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public String getRut() {
		return rut;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
