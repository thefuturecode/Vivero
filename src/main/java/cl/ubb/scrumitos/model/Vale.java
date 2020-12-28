package cl.ubb.scrumitos.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Vale {
	
	private int idVale;
	private int idFuncionario;
	private Date fecha;
	private int codigoProducto;
	private int total;
	
	
	
	
	public Vale() {
	}


	public Vale(int idVale, int idFuncionario, Date fecha, int codigoProducto, int total) {
		this.idVale = idVale;
		this.idFuncionario = idFuncionario;
		this.fecha = fecha;
		this.codigoProducto = codigoProducto;
		this.total = total;
	}
	

	public int getIdVale() {
		return idVale;
	}

	public void setIdVale(int idVale) {
		this.idVale = idVale;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
	

}