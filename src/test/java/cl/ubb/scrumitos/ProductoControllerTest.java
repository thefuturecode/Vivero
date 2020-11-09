package cl.ubb.scrumitos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cl.ubb.scrumitos.controller.ProductoController;
import cl.ubb.scrumitos.entity.Producto;
import cl.ubb.scrumitos.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {
	
	private MockMvc mvc;

	@Mock
	private ProductoRepository productoRepo; // Debiese mockear un servicio o un repositorio?
	
	@InjectMocks
	private ProductoController productoController;
	
	private JacksonTester<Producto> jsonProducto;
	
	@BeforeEach
	void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(productoController).build();
	}
	
	@Test
	void puedeRealizarEliminacionLogicaDeUnProductoDeManeraExitosa() {
		// given
		given(productoRepo.findById(1)).willReturn(new Producto());
		// ¿Cómo hacer arrange de un método void?
		
		// when
		MockHttpServletResponse response = mvc.perform(get("/producto/1").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then
		
	}

}
