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

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

	@Mock
	private ProveedorRepository proveedorRepo;

	@InjectMocks
	private ProveedorService proveedorService;

	private Proveedor mockedProvider = new Proveedor("56.925.741-4",
			"Panamericana Norte Km. 102. Hijuelas, V Región", "332272799", "contacto@grupohijuelas.cl",
			"Grupo Hijuelas");

	private Proveedor foundProvider;

	// Eliminación lógica de un proveedor
	// Para eliminar un proveedor, este debe ser encontrado primero
	// Caso exitoso: encuentra al proveedor que se desea eliminar
	@Test
	void alBuscarUnProveedorParaEliminarloEsteSiEsEncontradoYDevueltoPorElRepositorio()
			throws ProveedorNotFoundException {
		// Arrange
		when(proveedorRepo.getOne(2)).thenReturn(mockedProvider);

		// Act
		foundProvider = proveedorService.searchProvider(2);

		// Assert
		assertNotNull(foundProvider);
		assertAll("foundProvider", () -> assertEquals(2, foundProvider.getId()),
				() -> assertEquals("56.925.741-4", foundProvider.getRut()),
				() -> assertEquals("Panamericana Norte Km. 102. Hijuelas, V Región".toLowerCase(),
						foundProvider.getDireccion().toLowerCase()),
				() -> assertEquals("332272799", foundProvider.getTelefono()),
				() -> assertEquals("contacto@grupohijuelas.cl", foundProvider.getEmail()),
				() -> assertEquals("Grupo Hijuelas".toLowerCase(), foundProvider.getNombre().toLowerCase()));
	}

	// Caso exitoso: luego de encontrar al proveedor, cambia el atributo "estado" de
	// activo a inactivo
	@Test
	void luegoDeEncontrarElProveedorQueSeQuiereEliminarSeCambiaSuEstadoDeActivoAInactivo()
			throws ProveedorNotFoundException {
		// Arrange
		when(proveedorRepo.getOne(2)).thenReturn(mockedProvider);

		// Act
		proveedorService.delete(2);

		// Assert
		assertEquals("Inactivo".toLowerCase(), mockedProvider.getEstado().toLowerCase());
		verify(proveedorRepo, times(1)).save(mockedProvider);
	}

	// Caso fallido: el proveedor buscado no es encontrado, por lo que lanza
	// ProveedorNotFoundException
	@Test
	void alBuscarUnProveedorParaEliminarNoLoEncuentraPorLoQueLanzaExcepcionProveedorNotFoundException() {
		// Arrange
		when(proveedorRepo.findById(2)).thenReturn(null);

		// Act + Assert
		assertThrows(ProveedorNotFoundException.class, () -> proveedorService.searchProvider(2));
	}

	// Modificar los datos del proveedor
	// Caso Exitoso
	@Test
	void siDeseaModificarProveedorYEsteEsEncontradoEntoncesLoModificaYGuardaLosCambios()
			throws ProductNotFoundException, WithoutChangesException, BlankDataException, ProveedorNotFoundException {
		// Arrange
		Proveedor proveedorBuscado = new Proveedor("18.305.605-3", "Las Camelias 676", "+56934299140", "patricia@gmail.com", "Patricia Escalada Fuentes");
		when(proveedorRepo.getOne(1)).thenReturn(proveedorBuscado);

		// Act
		proveedorService.modificarProveedor(1, "18.305.605-3", "Las Camelias 676, Chillán", "+56934299141", "patricia@contacto.com", 
				"Patricia Alejandra Escalada Fuentes", "Inactivo");

		// Assert
		assertNotNull(proveedorBuscado);
		assertEquals("18.305.605-3", proveedorBuscado.getRut());
		assertEquals("Las Camelias 676, Chillán", proveedorBuscado.getDireccion());
		assertEquals("+56934299141",proveedorBuscado.getTelefono());
		assertEquals("patricia@contacto.com", proveedorBuscado.getEmail());
		assertEquals("Patricia Alejandra Escalada Fuentes", proveedorBuscado.getNombre());
		assertEquals("Inactivo", proveedorBuscado.getEstado());

		verify(proveedorRepo, times(1)).save(proveedorBuscado);
	}

	// Modificar los datos del producto
	// Caso Sin Cambios
	@Test
	void siDeseaModificarProveedorYEsteEsEncontradoEntoncesLoModificaSinCambiosYGuardaLosCambios()
			throws ProductNotFoundException, WithoutChangesException {
		// Arrange
		Proveedor proveedorBuscado = new Proveedor("18.305.605-3", "Las Camelias 676", "+56934299140", "patricia@gmail.com",
				"Patricia Escalada Fuentes");
		when(proveedorRepo.getOne(1)).thenReturn(proveedorBuscado);

		// Act + Assert
		assertThrows(WithoutChangesException.class,
				() -> proveedorService.modificarProveedor(1, "18.305.605-3", "Las Camelias 676", "+56934299140", "patricia@gmail.com", 
						"Patricia Escalada Fuentes", "Activo"));
	}

	// Modificar los datos del producto
	// Caso Con datos en blanco
	@Test
	void siDeseaModificarProveedorYEsteEsEncontradoEntoncesLoModificaConDatosEnBlancoYGuardaLosCambios()
			throws ProductNotFoundException, WithoutChangesException, BlankDataException {
		// Arrange
		Proveedor proveedorBuscado = new Proveedor("18.305.605-3", "Las Camelias 676", "+56934299140", "patricia@gmail.com",
				"Patricia Escalada Fuentes");

		when(proveedorRepo.getOne(1)).thenReturn(proveedorBuscado);

		// Act + Assert
		assertThrows(BlankDataException.class, () -> proveedorService.modificarProveedor(1, "", "", "", "", "", ""));
	}
}
