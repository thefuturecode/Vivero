package cl.ubb.scrumitos.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ProductoService productService;
	
	@InjectMocks
	private ProductoController productController;
	
	private JacksonTester<Producto> jsonProducto;
	
	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	//AGREGAR NUEVO PRODUCTO
	
	@Test
	void alPresionarAgregarProductoEsteSeDebeIngresar() throws Exception{
		Producto producto = new Producto(1, "Arbol frutal", "Kilamapu", "Arbol de 30 centimetros", 500, 10,"Activo");
		
		MockHttpServletResponse response = mockMvc.perform(
				 post("/productos/agregar").contentType(MediaType.APPLICATION_JSON)
				 .content(jsonProducto.write(producto).getJson()))
						.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	// ELIMINACIÓN LÓGICA DE UN PRODUCTO
		// Para eliminar un producto, debe buscarlo, traerlo, cambiar atributo "Estado" de "Activo" a "Inactivo" y
		// luego guardarlo (Servicio), controlador invoca método de eliminación y retorna status OK si la operación se realizó exitosamente,
		// NOT FOUND de lo contrario.
	
	// Si la operación de eliminación lógica se realiza de forma exitosa, entonces se retorna un status OK 
	@Test
	void alHacerClickEnBotonParaEliminarUnProductoEntoncesSeDebeCambiarSuEstadoDeActivoAInactivo() throws Exception {
		// given
		/*Producto productoBuscado = new Producto(3, "Tierra Biologica Compost", "ANASAC", 
				"Producto natural, hecho a partir de la compostación de residuos orgánicos", 4990, 20, "Activo");*/
		
		
		// when
		MockHttpServletResponse response = mockMvc.perform(get("/productos/eliminar/3")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		verify(productService, times(1)).eliminarProducto(3);
	}
	
	// Si la operación falla, entoces se retorna un status NOT FOUND
	@Test
	void siHaceClickEnBotonParaEliminarUnProductoPeroEsteNoEsEncontradoEntoncesRetornaNotFoundStatus() throws Exception {
		// given
		doThrow(new ProductNotFoundException()).when(productService).eliminarProducto(3);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(get("/productos/eliminar/3")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		verify(productService, times(1)).eliminarProducto(3);
		
	}
	
	@Test
	void siModificaUnProductoLosDatosDeEsteTienenQueCambiar() throws Exception{
		//given
		Producto producto = new Producto(3, "Tierra Biologica Compost", "ANASAC", 
				"Producto natural, hecho a partir de la compostación de residuos orgánicos", 4990, 20, "Activo");
		Producto productoModificado = new Producto(3, "Tierra Compost", "ARTHEMIS", 
				"Producto natural, hecho en base a compostación de residuos orgánicos", 5990, 15, "Activo");
		
		doThrow(new ProductNotFoundException()).when(productService).modificarProducto(producto);
		
		//when
		MockHttpServletResponse response = mockMvc.perform(put("/product/modificar")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then 
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		verify(productService, times(1)).modificarProducto(productoModificado);;
	}

}
