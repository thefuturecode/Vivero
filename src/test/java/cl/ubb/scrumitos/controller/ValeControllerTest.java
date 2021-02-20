package cl.ubb.scrumitos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Date;

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
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ubb.scrumitos.exceptions.ValeAlreadyExistsException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.service.ValeService;

@ExtendWith(MockitoExtension.class)
class ValeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ValeService valeService;

	@InjectMocks
	private ValeController valeController;

	private JacksonTester<Vale> jsonVale;

	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(valeController).build();
	}

	// AGREGAR VALE
	@Test
	void alPresionarAgregarValeEsteSeDebeIngresar() throws Exception {
		// given
		Vale vale = new Vale(1, "2020-01-02", 1, 2000);

		// when
		MockHttpServletResponse response = mockMvc.perform(
				 post("/vale/agregar").contentType(MediaType.APPLICATION_JSON)
				 .content(jsonVale.write(vale).getJson()))
						.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}
	
		

	// ACTUALIZAR VALE
	//Para actualizar se debe pasar el id del vale como parametro.
	@Test
	void alPresionarActualizarValeSeDebeDesplegarTodaLaInformacionDeEste() throws Exception {
		// given
		Vale vale2 = new Vale(1, "2010-02-02", 1, 2000);

		// when
		MockHttpServletResponse response = mockMvc.perform(get("/vale/actualizar/2").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}
	
	//ELIMINAR VALE
	
	@Test
	void alPresionarEliminarSeDebeEliminarElVale() throws Exception {
		// given
		Vale vale3 = new Vale(1, "2020-09-23", 1, 2000);
		
		MockHttpServletResponse response = mockMvc.perform(get("/vale/eliminar/2").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
