package cl.ubb.scrumitos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import cl.ubb.scrumitos.exceptions.FuncionarioAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.FuncionarioNotFoundException;
import cl.ubb.scrumitos.model.Funcionario;
import cl.ubb.scrumitos.service.FuncionarioService;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	
	private final FuncionarioService funcionarioService;
	
	@Autowired
	 public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService= funcionarioService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarFuncionario(@RequestBody Funcionario funcionario) throws FuncionarioAlreadyExistsException {
		funcionarioService.agregar(funcionario);
	
	}
	
	@GetMapping("/eliminar/{id}")
	public ResponseEntity<Funcionario> logicDelete(@PathVariable int id)  {
		try {
			funcionarioService.eliminarFuncionario(id);
		} catch (FuncionarioNotFoundException e) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Funcionario>(HttpStatus.OK);
	}
	@PostMapping(path = {"/update/{id}"})
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable(value = "id") int id,@RequestBody Funcionario funcionario) {

		try {
        	funcionarioService.agregar(funcionario);
            funcionario.setIdFuncionario(id);
            funcionarioService.updateFuncionario(funcionario);
            return new ResponseEntity<>(funcionarioService.updateFuncionario(funcionario),HttpStatus.CREATED);
        }catch (FuncionarioNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


