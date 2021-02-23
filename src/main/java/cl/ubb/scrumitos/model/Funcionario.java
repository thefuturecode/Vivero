package cl.ubb.scrumitos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcionarios")
public class Funcionario {

	private String run;
	private String nombre;
	private String apellido;
	private String cargo;
	private String telefono;
	private String email;

	@Id
	@GeneratedValue
	@Column(name = "id_funcionario")
	private int idFuncionario;
	private String estado;
	
	public Funcionario() {}
	
	public Funcionario(String run, String nombre, String apellido, String cargo, String telefono, String email,int idFuncionario, String estado) {
		this.run = run;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.telefono = telefono;
		this.email = email;
		this.idFuncionario = idFuncionario;
		this.estado=estado;
	}


	public void setRun(String run) {
		this.run = run;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public String getEstadoFuncionario() {
		return estado;
	}

	public void setEstadoFuncionario(String estado) {
		this.estado = estado;
	}


	public String getRun() {
		// TODO Auto-generated method stub
		return run;
	}
	
	
	
	
	
}
