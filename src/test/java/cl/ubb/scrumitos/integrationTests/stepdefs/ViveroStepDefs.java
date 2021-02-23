package cl.ubb.scrumitos.integrationTests.stepdefs;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import cl.ubb.scrumitos.integrationTests.CucumberSpringContextConfiguration;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.repository.ValeRepository;
import cl.ubb.scrumitos.service.ValeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViveroStepDefs extends CucumberSpringContextConfiguration {

	@LocalServerPort
	private int port;

	Vale vale;
	Proveedor nuevoProveedor;

	private ResponseEntity<Vale> responseVale;
	private ResponseEntity<Proveedor> responseProveedor;

	@Autowired
	private ValeRepository valeRepository;

	@Autowired
	private ValeService valeService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@After
	public void tearDown() {
		valeRepository.deleteAll();
	}

	// Ingreso de un vale
	@Given("existe un vale; idFuncionario {int}, fecha {string}, codigoProducto {int}, total {int}")
	public void existe_un_nuevo_vale_idFuncionario_fecha_codigoProducto_total(int idFuncionario, String fecha,
			int codigoProducto, int total) {
		vale = new Vale(idFuncionario, fecha, codigoProducto, total);

	}

	@When("deseo agregar el vale")
	public void deseo_agregar_un_vale() throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Vale> entity = new HttpEntity<>(vale, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseVale = testRestTemplate.exchange(createURLWithPort("/vale/agregar"),HttpMethod.POST,entity,Vale.class);
	}

	@Then("se obtiene el estado {string}")
	public void se_obtiene_el_estado(String estado) {
		assertEquals(estado.toUpperCase(),responseVale.getStatusCode().name().toString());

	}

	// AGREGAR UN NUEVO PROVEEDOR
	@Given("se tiene un nuevo proveedor; nombre {string}, rut {string}, telefono {string}, email {string}, direccion {string}")
	public void se_tiene_un_nuevo_proveedor_y_se_desea_agregar_al_vivero(String nombre, String rut, String telefono,
			String email, String direccion) {
		nuevoProveedor = new Proveedor(nombre, rut, telefono, email, direccion);
	}

	@When("se desea agregar a este proveedor a los registros del vivero")
	public void se_agrega_el_proveedor_al_vivero() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Proveedor> entity = new HttpEntity<>(nuevoProveedor, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseProveedor = testRestTemplate.exchange(createURLWithPort("/proveedores/agregar"), HttpMethod.POST,
				entity, Proveedor.class);
	}

	@Then("se obtiene el estado {string} y el proveedor creado tiene como nombre {string}, rut {string} y direccion {string}")
	public void se_obtiene_el_estado_y_el_proveedor_tiene_nombre_rut_y_direccion(String status, String nombre,
			String rut, String direccion) {
		
		assertEquals(status.toUpperCase(), responseProveedor.getStatusCode().name().toString());

		nuevoProveedor = responseProveedor.getBody();
		assertNotNull(nuevoProveedor);

		assertAll("nuevoProveedor", 
		() -> assertEquals(nombre, nuevoProveedor.getNombre()),
		() -> assertEquals(rut, nuevoProveedor.getRut()),
		() -> assertEquals(direccion, nuevoProveedor.getDireccion()));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
