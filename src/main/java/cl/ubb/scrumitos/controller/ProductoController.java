package cl.ubb.scrumitos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.service.ProductoService;

@Controller
@RequestMapping("/productos/")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	// Eliminación lógica de un producto
	@GetMapping("eliminar/{codigo}")
	public ResponseEntity<Producto> logicDelete(@PathVariable int codigo) {	
		try {
			productoService.eliminarProducto(codigo);
			
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}

}
