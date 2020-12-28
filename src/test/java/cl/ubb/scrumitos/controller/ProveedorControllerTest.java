package cl.ubb.scrumitos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.service.ProveedorService;

@ExtendWith(MockitoExtension.class)
class ProveedorControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProveedorService proveedorService;

	@InjectMocks
	private ProveedorController proveedorController;

	private JacksonTester<Producto> jsonProducto;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(proveedorController).build();
	}

	// Eliminación lógica
	// Caso exitoso: el proveedor es encontrado y eliminado. Se devuelve un status
	// code 200
	@Test
	void siElProveedorEsEncontradoYEliminadoEntoncesLaOperacionSeRealizaExitosamenteYSeRetornaOK() throws Exception {
		// when
		MockHttpServletResponse response = mockMvc
				.perform(get("/proveedores/eliminar/2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		verify(proveedorService, times(1)).delete(2);
	}

	// Caso fallido: el proveedor no es encontrado y servicio lanza excepción. Se
	// devuelve status code 404
	@Test
	void siElProveedorNoEsEncontradoEntoncesServicioLanzaExcepcionYSeRetornaNotFoundStatus() throws Exception {
		// given
		doThrow(new ProveedorNotFoundException()).when(proveedorService).delete(2);

		// when
		MockHttpServletResponse response = mockMvc
				.perform(get("/proveedores/eliminar/2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		verify(proveedorService, times(1)).delete(2);
	}

	// Modificación de Proveedores
	// modificación Exitosa
	@Test
	void siElProveedorEsEncontradoYModificadoEntoncesLaOperacionSeRealizaExitosamente() throws Exception {
		// given
		Proveedor p = new Proveedor(1, "18.305.605-3", "Las Camelias 676, Chillán", "+56934299141", "patricia@contacto.com", 
				"Patricia Alejandra Escalada Fuentes", "Inactivo");

		// when
		MockHttpServletResponse response = mockMvc
				.perform(put("/proveedores/modificar/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		verify(proveedorService, times(1)).modificarProveedor(1, "18.305.605-3", "Las Camelias 676, Chillán", "+56934299141", "patricia@contacto.com", 
				"Patricia Alejandra Escalada Fuentes", "Inactivo");
	}
}
