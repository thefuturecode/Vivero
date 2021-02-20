package cl.ubb.scrumitos.integrationTests.stepdefs;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

import cl.ubb.scrumitos.integrationTests.CucumberSpringContextConfiguration;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.repository.ValeRepository;
import cl.ubb.scrumitos.service.ValeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViveroStepDefs extends CucumberSpringContextConfiguration{
	
	@LocalServerPort
    private int port;
	
	 Vale vale;
	 
	 private ResponseEntity<Vale> responseVale;
	 
	 @Autowired
	 private ValeRepository valeRepository;
	 
	 @Autowired
	 private ValeService valeService;
	 
	 @Autowired
	    private TestRestTemplate testRestTemplate;
	 
	 @After
	    public  void tearDown(){
	        valeRepository.deleteAll();
	    }
	 
	 //Ingreso de un vale
	 @Given("existe un vale; idFuncionario {int}, fecha {string}, codigoProducto {int}, total {int}")
	 public void existe_un_nuevo_vale_idFuncionario_fecha_codigoProducto_total(int idFuncionario, String fecha, int codigoProducto, int total) {
		 vale = new Vale(idFuncionario, fecha, codigoProducto, total);
		 
	 }
	 
	 @When("deseo agregar el vale")
	 public void deseo_agregar_un_vale()throws Exception{
		 
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		 HttpEntity<Vale> entity = new HttpEntity<>(vale,httpHeaders);
		 
		 testRestTemplate = new TestRestTemplate();
		 //responseVale = testRestTemplate.exchange(createURLWithPort("/vale/agregar"), HttpMethod.POST,entity,Vale.class);
	 }
	 @Then("se obtiene el estado {string}")
	 public void se_obtiene_el_estado(String estado) {
		//assertEquals(estado.toUpperCase(),responseVale.getStatusCode().name().toString());
		 
	 }

	private String createURLWithPort(String uri) {
	    return "http://localhost:" + port + uri;
	}

}
