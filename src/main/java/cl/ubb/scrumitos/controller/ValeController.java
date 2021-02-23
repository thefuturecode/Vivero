package cl.ubb.scrumitos.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.ValeAlreadyExistsException;
import cl.ubb.scrumitos.exceptions.ValeNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.model.Vale;
import cl.ubb.scrumitos.service.ValeService;

@RestController
@RequestMapping("/vale")
public class ValeController {

	@Autowired
	private ValeService valeService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Vale>> getAllVales() throws Exception{
		try{
			List<Vale> vales = valeService.getAllVales();
			return new ResponseEntity<>(vales,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/eliminar/{id}")
	public ResponseEntity<Vale> eliminarVale(@PathVariable int id){
		
		valeService.eliminarVale(id);
		return new ResponseEntity<Vale>(HttpStatus.OK);
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<Vale> agregarVale(@RequestBody Vale vale) throws ValeNotFoundException{
		try {
			Vale nuevoVale = valeService.agregarVale(vale);
			return new ResponseEntity<>(nuevoVale, HttpStatus.CREATED);
		}catch (ValeAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	 @PostMapping("/update/{id}")
	    public ResponseEntity<Vale> update(@PathVariable int id, Vale vale) {
	        try {
	        	vale.setIdVale(id);
	            return new ResponseEntity<Vale>(valeService.editarVale(vale),HttpStatus.CREATED);
	        } catch (ValeNotFoundException e) {
	            return new ResponseEntity<Vale>(HttpStatus.NOT_FOUND);
	        }

	    }
}
