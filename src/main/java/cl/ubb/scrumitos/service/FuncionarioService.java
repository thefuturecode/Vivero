package cl.ubb.scrumitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ubb.scrumitos.exceptions.FuncionarioNotFoundException;
import cl.ubb.scrumitos.model.Funcionario;
import cl.ubb.scrumitos.repository.FuncionarioRepository;


@Service
public class FuncionarioService {

	
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	
	public Funcionario searchFuncionario(int id) throws  FuncionarioNotFoundException {
		
			Funcionario funcionario = funcionarioRepo.getOne(id);
			
			if(funcionario == null) {
				throw new FuncionarioNotFoundException();
			}
			
			return funcionario;
	}
	
	public void agregar(Funcionario funcionario){   	       
	      funcionarioRepo.save(funcionario);
		}

	public void eliminarFuncionario(int id) throws FuncionarioNotFoundException{
		Funcionario deletedFunc = searchFuncionario(id);
		deletedFunc.setEstadoFuncionario("Inactivo");
		funcionarioRepo.save(deletedFunc);
		
	}
	public Funcionario updateFuncionario(Funcionario funcionario) throws FuncionarioNotFoundException {
        searchFuncionario(funcionario.getIdFuncionario());
        return  funcionarioRepo.save(funcionario);
    }
	
	

}
