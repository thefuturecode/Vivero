package cl.ubb.scrumitos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ubb.scrumitos.exceptions.FuncionarioAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.FuncionarioNotFoundException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.model.Funcionario;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.repository.FuncionarioRepository;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

	@Mock
	private FuncionarioRepository funcionarioRepo;
	
	@InjectMocks
	private FuncionarioService funcService;
		
	//Caso Exitoso
	@Test 
	void siSeRequiereIngresarUnNuevoFuncionarioYlosCamposEstanCompletosEntoncesSeAgrega() throws FuncionarioAlreadyExistsException{ 
	
		Funcionario funcionario = new Funcionario("19289859-7", "Javier", "Saavedra", "vendedor","+56985997895","j.Saavedra@gmail.com",1,"activo");
		
		//act
		funcService.agregar(funcionario);
		
		//assert
		assertNotNull(funcionario);
		assertAll("funcionario",
			() -> assertEquals("19289859-7",funcionario.getRun()),
			() -> assertEquals("Javier",funcionario.getNombre()),
			() -> assertEquals("Saavedra",funcionario.getApellido()),
			() -> assertEquals("vendedor",funcionario.getCargo()),
			() -> assertEquals("+56985997895",funcionario.getTelefono()),
			() -> assertEquals("j.Saavedra@gmail.com",funcionario.getEmail()),
			() -> assertEquals(1,funcionario.getIdFuncionario()),
			() -> assertEquals("activo",funcionario.getEstadoFuncionario())
		);
		verify(funcionarioRepo, times(1)).save(funcionario);
		
	}
	//Caso fallido
	
	// para ingresar se debe buscar si el id ya esta en la lista, si esta se lanza una excepcion ya que no se puede ingresar si ya existe
	@Test
	void siElFuncionarioAIngresarYaSeEncuentraRegistradoEntoncesSeDebeTerminarLaOperacion() throws FuncionarioNotFoundException {
		// Arrange
		Funcionario func = new Funcionario ("19289859-7", "Javier", "Saavedra", "vendedor","+56985997895",
				"j.Saavedra@gmail.com",1,"activo");
		when(funcionarioRepo.findById(1)).thenReturn(func);

		// Act
		Funcionario func2 = funcService.searchFuncionario(1);
		// Assert
		assertNotNull(func2);
		assertEquals(func, func2);
	}
	
	//eliminacion exitosa, si encuentra el producto entonces lo elimina logicamente cambiando el estado
	
	@Test
	void siseDeseaEliminarUnFuncionarioYesteExisteEntoncesSeBuscaYSeCambiaElEstadoDelFuncionarioAInactivo() throws FuncionarioNotFoundException, FuncionarioAlreadyExistsException {
		      // Arrange
				Funcionario func = new Funcionario ("19289859-7", "Javier", "Saavedra", "vendedor","+56985997895",
						"j.Saavedra@gmail.com",1,"activo");
				when(funcionarioRepo.findById(1)).thenReturn(func);
				
				// Act
			    funcService.eliminarFuncionario(1);
				
				//Assert
				assertNotNull(func);
				assertEquals("Inactivo".toLowerCase(), func.getEstadoFuncionario().toLowerCase());
				verify(funcionarioRepo, times(1)).save(func);
	}
	
	//eliminacion fallida, no encuentra el funcionario a eliminar y lanza una excepcion
	
	@Test
	void siDeseaEliminarUnFuncionarioPeroElServiceNoLoEncuentraEntoncesLanzaLaExcepcionFuncionarioNotFoundException() throws FuncionarioNotFoundException,FuncionarioAlreadyExistsException{
		// Arrange
		when(funcionarioRepo.findById(1)).thenReturn(null);
		
		// Act + Assert
		assertThrows(FuncionarioNotFoundException.class, () -> 
				funcService.searchFuncionario(1));
	}
}

	