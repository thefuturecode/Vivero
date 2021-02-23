package cl.ubb.scrumitos.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="vales")
public class Vale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vale")
	private int idVale;
	@Column(name = "id_funcionario")
	private int idFuncionario;
	private String fecha;
	@Column(name = "codigo_producto")
	private int codigoProducto;
	private int total;
	
	
	
	
	public Vale() {super();}


	public Vale(int idFuncionario, String fecha, int codigoProducto, int total) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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
