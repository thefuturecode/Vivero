package cl.ubb.scrumitos.integrationTests.stepdefs;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import cl.ubb.scrumitos.integrationTests.CucumberSpringContextConfiguration;
import cl.ubb.scrumitos.model.Funcionario;
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

	int idFunc;
	int idProveedor;
	Vale vale;
	Proveedor nuevoProveedor;
	Funcionario fun;
	Proveedor proveedor;

	private ResponseEntity<Vale> responseVale;
	private ResponseEntity<Proveedor> responseProveedor;
	private ResponseEntity<Funcionario> responseFuncionario;

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
	@Given("existe un vale id_funcionario {int}, fecha {string}, codigo_producto {int}, total {int}")
	public void existe_un_nuevo_vale_idFuncionario_fecha_codigoProducto_total(int id_funcionario, String fecha,
			int codigoProducto, int total) {
		vale = new Vale(id_funcionario, fecha, codigoProducto, total);

	}

	@When("deseo agregar el vale")
	public void deseo_agregar_un_vale() throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Vale> entity = new HttpEntity<>(vale, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseVale = testRestTemplate.exchange(createURLWithPort("/vale/agregar"), HttpMethod.POST, entity,
				Vale.class);
	}

	@Then("se obtiene el estado {string}")
	public void se_obtiene_el_estado(String estado) {
		assertEquals(estado.toUpperCase(), responseVale.getStatusCode().name().toString());

	}

	// AGREGAR UN NUEVO PROVEEDOR (Caso exitoso)
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

		assertAll("nuevoProveedor", () -> assertEquals(nombre, nuevoProveedor.getNombre()),
				() -> assertEquals(rut, nuevoProveedor.getRut()),
				() -> assertEquals(direccion, nuevoProveedor.getDireccion()));
	}

	// Agregar un nuevo proveedor (caso fallido)
	@Given("se tiene un nuevo proveedor nombre nulo, rut nulo, telefono {string}, email {string}, direccion nula")
	public void se_tiene_un_nuevo_proveedor_pero_le_faltan_datos_obligatorios(String telefono, String email) {
		nuevoProveedor = new Proveedor(null, null, telefono, email, null);
	}

	@When("se intenta agregar este nuevo proveedor al sistema del vivero")
	public void se_intenta_agregar_este_nuevo_proveedor_al_sistema() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Proveedor> entity = new HttpEntity<>(nuevoProveedor, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseProveedor = testRestTemplate.exchange(createURLWithPort("/proveedores/agregar"), HttpMethod.POST,
				entity, Proveedor.class);
	}

	@Then("se obtendra el estado {string}")
	public void se_obtendra_el_estado_no_content(String status) {
		assertEquals(status.toUpperCase(), responseProveedor.getStatusCode().name().toString());
	}

	// editar funcionario
	@Given("Hay un Funcionario con id {int},nombre {string}, apellido {string}, run {string}, telefono {string}, cargo {string}, email {string}, estado {string}")
	public void hay_un_funcionario_con_id_nombre_apellido_run_telefono_cargo_email_estado(Integer id, String nombre,
			String apellido, String run, String telefono, String cargo, String email, String estado) {
		fun = new Funcionario(run, nombre, apellido, cargo, telefono, email, id, estado);
		idFunc = id;
	}

	@When("se desea editar al funcionario")
	public void se_desea_editar_al_funcionario() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Funcionario> entity = new HttpEntity<>(fun, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseFuncionario = testRestTemplate.exchange(createURLWithPort("/Funcionarios/update/" + idFunc),
				HttpMethod.POST, entity, Funcionario.class);
	}

	@Then("se obtiene el status {string}")
	public void se_obtiene_el_status(String string) {
		assertEquals(string.toUpperCase(), responseFuncionario.getStatusCode().name().toString());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	// EDITAR PROVEEDOR
	@Given("Hay un proveedor con id {int}, rut {string}, direccion {string}, telefono {string}, email {string}, nombre {string}")
	public void hay_un_proveedor_con_id_rut_direccion_telefono_email_nombre_estado(Integer id, String rut,
			String direccion, String telefono, String email, String nombre) {
		proveedor = new Proveedor(rut, direccion, telefono, email, nombre);
		idProveedor = id;
	}

	@When("se desea editar el proveedor")
	public void se_desea_editar_el_proveedor() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		HttpEntity<Proveedor> entity = new HttpEntity<>(proveedor, httpHeaders);

		testRestTemplate = new TestRestTemplate();
		responseProveedor = testRestTemplate.exchange(createURLWithPort("/Proveedor/modificar/" + idProveedor),
				HttpMethod.POST, entity, Proveedor.class);
	}

	@Then("se obtiene la respuesta {string}")
	public void se_obtiene_la_respuesta(String string) {
		assertEquals(string.toUpperCase(), responseProveedor.getStatusCode().name().toString());
	}
}
