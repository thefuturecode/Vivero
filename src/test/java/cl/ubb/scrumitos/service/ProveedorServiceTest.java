package cl.ubb.scrumitos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

	@Mock
	private ProveedorRepository proveedorRepo;
	
	@InjectMocks
	private ProveedorService proveedorService;
	
	private Proveedor mockedProvider = new Proveedor(2, "56.925.741-4", "Panamericana Norte Km. 102. Hijuelas, V Región", "332272799", 
			"contacto@grupohijuelas.cl", "Grupo Hijuelas", "Activo");
	
	private Proveedor foundProvider;
	
	// Eliminación lógica de un proveedor
	// Para eliminar un proveedor, este debe ser encontrado primero
	// Caso exitoso: encuentra al proveedor que se desea eliminar
	@Test
	void alBuscarUnProveedorParaEliminarloEsteSiEsEncontradoYDevueltoPorElRepositorio() throws ProveedorNotFoundException {
		// Arrange
		when(proveedorRepo.findById(2)).thenReturn(mockedProvider);
		
		// Act
		foundProvider = proveedorService.searchProvider(2);
		
		// Assert
		assertNotNull(foundProvider);
		assertAll("foundProvider", 
				() -> assertEquals(2, foundProvider.getId()),
				() -> assertEquals("56.925.741-4", foundProvider.getRut()),
				() -> assertEquals("Panamericana Norte Km. 102. Hijuelas, V Región".toLowerCase(), foundProvider.getDireccion().toLowerCase()),
				() -> assertEquals("332272799", foundProvider.getTelefono()),
				() -> assertEquals("contacto@grupohijuelas.cl", foundProvider.getEmail()),
				() -> assertEquals("Grupo Hijuelas".toLowerCase(), foundProvider.getNombre().toLowerCase()));
	}
	
	// Caso exitoso: luego de encontrar al proveedor, cambia el atributo "estado" de activo a inactivo
	@Test
	void luegoDeEncontrarElProveedorQueSeQuiereEliminarSeCambiaSuEstadoDeActivoAInactivo() throws ProveedorNotFoundException {
		// Arrange
		when(proveedorRepo.findById(2)).thenReturn(mockedProvider);
		
		// Act 
		proveedorService.delete(2);
		
		// Assert
		assertEquals("Inactivo".toLowerCase(), mockedProvider.getEstado().toLowerCase());
		verify(proveedorRepo, times(1)).save(mockedProvider);
	}
	
	// Caso fallido: el proveedor buscado no es encontrado, por lo que lanza ProveedorNotFoundException
	@Test
	void alBuscarUnProveedorParaEliminarNoLoEncuentraPorLoQueLanzaExcepcionProveedorNotFoundException() {
		// Arrange
		when(proveedorRepo.findById(2)).thenReturn(null);
		
		// Act + Assert
		assertThrows(ProveedorNotFoundException.class, () -> proveedorService.searchProvider(2));
	}
}
