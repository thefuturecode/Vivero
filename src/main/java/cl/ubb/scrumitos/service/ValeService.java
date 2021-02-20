package cl.ubb.scrumitos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.FuncionarioAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.ValeAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.ValeNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.repository.ValeRepository;

@Service
public class ValeService {
	
	@Autowired
	private ValeRepository valeRepo;
	
	public Vale searchVale(int id) throws ValeAlreadyExistsException {
		
		Vale vale = valeRepo.findById(id);
		
		if (vale != null) {
			throw new ValeAlreadyExistsException();
		}
		return vale;
	}
	
	public Vale searchVale2(int id) {
		if (valeRepo.findById(id) != null) {
			return (Vale) valeRepo.findById(id);
		}
		return new Vale();

	}
	
	public void guardarVale(Vale vale) throws ValeAlreadyExistsException, ValeNotFoundException, BlankDataException {
		
		if(vale.getIdVale()== 0 && vale.getIdFuncionario() == 0 && vale.getFecha()==null && vale.getCodigoProducto()==0 && vale.getTotal()==0) {
			throw new BlankDataException();
		}
		Vale aux = valeRepo.findById(vale.getIdVale());
		if(aux!= null) {
			throw new ValeAlreadyExistsException();
		}else {
			valeRepo.save(vale);
		}
	}
	
	public void modificarVale(Vale vale) throws WithoutChangesException, BlankDataException, ValeNotFoundException, ValeAlreadyExistsException {
		
		Vale valeAModificar = searchVale(vale.getIdVale());
		
		//Caso cuando no se modifican los datos
		if(valeAModificar.getIdVale()==vale.getIdVale() && valeAModificar.getIdFuncionario() == vale.getIdFuncionario() 
				&& valeAModificar.getFecha()==vale.getFecha() && valeAModificar.getCodigoProducto()==vale.getCodigoProducto() && valeAModificar.getTotal() == vale.getTotal()) {
			throw new WithoutChangesException();
		}
		
		//Datos en blanco
		
		if(vale.getIdVale()== 0 && vale.getIdFuncionario() == 0 && vale.getFecha()==null && vale.getCodigoProducto()==0 && vale.getTotal()==0) {
			throw new BlankDataException();
		}
		
		//Datos modificados
		
		if(valeAModificar.getIdVale()==vale.getIdVale() && vale.getIdVale()>0) {
			valeAModificar.setIdVale(vale.getIdVale());
		}
		if(valeAModificar.getIdFuncionario()>0) {
			valeAModificar.setIdFuncionario(vale.getIdFuncionario());
		}
		if(valeAModificar.getFecha()!=null) {
			valeAModificar.setFecha(vale.getFecha());
		}
		if(valeAModificar.getCodigoProducto()>0) {
			valeAModificar.setCodigoProducto(vale.getCodigoProducto());
		}
		if (valeAModificar.getTotal()>=0) {
			valeAModificar.setTotal(vale.getTotal());
		}
		
	}
	
	public void modificarVale(int idVale, int idFuncionario, String fecha, int codigoProducto, int total){

		Vale valeModificado = searchVale2(idVale);
		Vale vale2 = new Vale(1,fecha,1,4000);
		valeRepo.save(vale2);
		// Caso cuando no se modifican los datos
		if (valeModificado.getIdVale() == idVale && valeModificado.getIdFuncionario() == idFuncionario
				&& valeModificado.getFecha() == fecha && valeModificado.getCodigoProducto() == codigoProducto
				&& valeModificado.getTotal() == total) {
			valeRepo.save(valeModificado);
		}

	}
	
	public void eliminarVale(int id) {
		Vale valeAEliminar = searchVale2(id);
		valeRepo.delete(valeAEliminar);
	}
	

}
