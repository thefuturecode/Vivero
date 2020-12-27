package cl.ubb.scrumitos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.service.ProveedorService;

@Controller
@RequestMapping("/proveedores/")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	// Eliminación lógica de un proveedor
	@GetMapping("eliminar/{id}")
	ResponseEntity<Proveedor> eliminarProveedor(@PathVariable int id) throws ProveedorNotFoundException {
		try {
			proveedorService.delete(id);
			
		} catch (ProveedorNotFoundException e) {
			return new ResponseEntity<Proveedor>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Proveedor>(HttpStatus.OK);
	}
}