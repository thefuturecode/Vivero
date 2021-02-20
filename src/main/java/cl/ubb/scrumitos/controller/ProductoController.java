package cl.ubb.scrumitos.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.service.ProductoService;

@Controller
@RequestMapping("/productos/")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// Agregar un nuevo producto
	@PostMapping("agregar")
	public ResponseEntity<Producto> logicAgregar(Producto producto, BindingResult result, Model model) {

		try {
			productoService.guardarProducto(producto);

		} catch (ProductNotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(HttpStatus.OK);
	}

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

	// Modificación exitosa de un producto
    @PutMapping("modificar")
    public ResponseEntity<Producto> modificaProducto(@RequestBody Producto producto) throws ProductNotFoundException, WithoutChangesException, BlankDataException{
        productoService.modificarProducto(producto.getCodigo(), producto.getNombre(), producto.getMarca(),
                    producto.getDescripcion(), producto.getPrecio(), producto.getStock(), producto.getEstado());
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
}
