package cl.ubb.scrumitos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.ValeAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.ValeNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.service.ValeService;

@Controller
@RequestMapping("/vale")
public class ValeController {

	@Autowired
	private ValeService valeService;
	
	@PostMapping("/agregar")
	public ResponseEntity<Vale> logicAgregar(@RequestBody Vale vale) throws Exception {
		
		try {
			valeService.guardarVale(vale);
			return new ResponseEntity<>(vale, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/actualizar/{id}")
	public ResponseEntity<Vale> actualizarVale(@PathVariable int id) throws WithoutChangesException, BlankDataException, ValeAlreadyExistsException{
		
		try {
			valeService.searchVale(id);

		} catch (ValeAlreadyExistsException e) {
			return new ResponseEntity<Vale>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Vale>(HttpStatus.OK);
	}
	
	@GetMapping("/eliminar/{id}")
	public ResponseEntity<Vale> eliminarVale(@PathVariable int id){
		
		valeService.eliminarVale(id);
		return new ResponseEntity<Vale>(HttpStatus.OK);
	}
	
}
