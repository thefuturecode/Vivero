package cl.ubb.scrumitos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.ValeAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.ValeNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.repository.ValeRepository;

@ExtendWith(MockitoExtension.class)
class ValeServiceTest {
	
	@Mock
	private ValeRepository valeRepo;
	
	@InjectMocks
	private ValeService valeService;
	
	
	
	//AGREGAR UN VALE
	//El primer test considera que se ingrese un vale que ya esta registrada entonces se debe lanzar una exepción.
	//Caso fallido
	@Test
	void SiElValeYaFueIngresadoEntoncesSeDebeLanzarUnaExcepcion() throws ValeAlreadyExistsException{
		// Arrange
		Vale vale = new Vale(10,1,new Date(),1,2000);
		when(valeRepo.findById(10)).thenReturn(vale);
		
		// Act 
		assertThrows(ValeAlreadyExistsException.class, () -> valeService.searchVale(10));
		
	}
	
	@Test
	void SiSeRegistraUnValeConDatosNulosSeDebeLanzarUnaExcepcion() throws Exception {
		// Arrange
		Vale vale = new Vale(0,0,null,0,0);
		when(valeRepo.findById(10)).thenReturn(vale);
		
		// Act 
		assertThrows(Exception.class, () -> valeService.searchVale(10));
		
		
	}
	
	@Test
	//Si los campos estan completos entonces se debe ingresar el vale
	//Caso Exitoso
	void siSeRequiereIngresarUnNuevoFuncionarioYlosCamposEstanCompletosEntoncesSeAgrega() throws ValeAlreadyExistsException, ValeNotFoundException, BlankDataException{
		
		// Arrange
		Vale vale = new Vale(1,1,new Date(),1,4000);
		
		//act
		valeService.guardarVale(vale);
		
		// assert
		assertNotNull(vale);
		assertAll("vale",
				() -> assertEquals(1,vale.getIdVale()),
				() -> assertEquals(1,vale.getIdFuncionario()),
				() -> assertEquals( new Date(), new Date()),
				() -> assertEquals(1,vale.getCodigoProducto()),
				() -> assertEquals(4000,vale.getTotal())
			);
		verify(valeRepo, times(1)).save(vale);
	}
	
	
	
	//ACTUALIZAR VALE
	//Actualización sin cambios
	@Test
	void siDeseaModificarElValePeroNoSeRealizanCambios() throws Exception {
		
		// Arrange
		Vale vale = new Vale(2,1,new Date(),1,4000);
		when(valeRepo.findById(2)).thenReturn(vale);
		
		assertThrows(Exception.class, ()-> valeService.modificarVale(vale));
	}
	
	
	@Test
	void siDeseaModificarElValeYEsteEsEncontradoEntoncesLoModificaConDatosEnBlancoYGuardaLosCambios() 
		throws Exception {
		
		// Arrange
		Vale valeAModificar = new Vale(3,1,new Date(),1,4000);
		Vale valeConNuevosDatos = new Vale(0,0,null,0,0);
		
		when(valeRepo.findById(3)).thenReturn(valeAModificar);
		// Act + Assert
		assertThrows(Exception.class, ()-> valeService.modificarVale(valeConNuevosDatos));
	}
	@Test
	void siSeIngresanDatosNoNullSeDebeModificarElVale() throws Exception {
		Vale valeAModificar = new Vale(3,1,new Date(),1,4000);
		when(valeRepo.findById(3)).thenReturn(valeAModificar);
		
		// Act + Assert
		assertThrows(Exception.class, ()-> valeService.modificarVale(new Vale(3,2,new Date(),2,6000)));
		
		
	}

}
