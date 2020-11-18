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

import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

	@Mock
	private ProductoRepository productRepo;
	
	@InjectMocks
	private ProductoService productService;
	
	
	// ELIMINACIÓN LÓGICA DE UN PRODUCTO
		// Para eliminar un producto, debe buscarlo, traerlo, cambiar atributo "Estado" de "Activo" a "Inactivo" y
		// luego guardarlo (Servicio), controlador invoca método de eliminación y retorna status OK si la operación se realizó exitosamente.
	
	// Buscar y obtener el producto
	// Caso exitoso
	@Test
	void siDeseaEliminarUnProductoEntoncesProductoServiceBuscaElProductoMedianteSuIdYLoRetorna() throws ProductNotFoundException { 
		// Arrange
		Producto productoBuscado = new Producto(3, "Tierra Biologica Compost", "ANASAC", 
				"Producto natural, hecho a partir de la compostación de residuos orgánicos", 4990, 20, "Activo");
		when(productRepo.findById(3)).thenReturn(productoBuscado);
		
		// Act
		Producto productoObtenido = productService.searchProduct(3);
		
		// Assert
		assertNotNull(productoObtenido);
		assertAll("productoObtenido", 
				() -> assertEquals(3, productoObtenido.getCodigo()),
				() -> assertEquals("Tierra Biologica Compost".toLowerCase(), productoObtenido.getNombre().toLowerCase()),
				() -> assertEquals("ANASAC".toLowerCase(), productoObtenido.getMarca().toLowerCase()),
				() -> assertEquals("Producto natural, hecho a partir de la compostación de residuos orgánicos".toLowerCase(), 
						productoObtenido.getDescripcion().toLowerCase()),
				() -> assertEquals(4990, productoObtenido.getPrecio()),
				() -> assertEquals(20, productoObtenido.getStock()));
		
	}
	
	// Buscar y obtener producto
	// Caso fallido (no encuentra el producto y retorna null, manejado mediante ProductNotFoundException)
	@Test
	void siDeseaEliminarUnProductoYProductoServiceLoBuscaPeroNoLoEncuentraEntoncesLanzaProductNotFoundException() {
		// Arrange
		when(productRepo.findById(3)).thenReturn(null);
		
		// Act + Assert
		assertThrows(ProductNotFoundException.class, () -> 
				productService.searchProduct(3));
	}
	
	// Guardar cambio en atributo "Estado" de Activo a Inactivo
	// Caso exitoso
	@Test
	void siDeseaEliminarProductoYEsteEsEncontradoEntoncesCambiaAtributoEstadoYGuardaLosCambios() throws ProductNotFoundException {
		// Arrange
		Producto productoBuscado = new Producto(3, "Tierra Biologica Compost", "ANASAC", 
				"Producto natural, hecho a partir de la compostación de residuos orgánicos", 4990, 20, "Activo");
		when(productRepo.findById(3)).thenReturn(productoBuscado);
		
		// Act
		productService.eliminarProducto(3);
		
		//Assert
		assertNotNull(productoBuscado);
		assertEquals("Inactivo".toLowerCase(), productoBuscado.getEstado().toLowerCase());
		verify(productRepo, times(1)).save(productoBuscado);
		
	}

}
