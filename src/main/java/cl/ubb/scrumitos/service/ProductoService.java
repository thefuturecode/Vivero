package cl.ubb.scrumitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProductNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productRepo;

	public Producto searchProduct(int codigo) throws ProductNotFoundException {
		Producto productFound = productRepo.getOne(codigo);
		
		if (productFound == null) {
			throw new ProductNotFoundException();
		}
		
		return productFound;
	}

	public void guardarProducto(Producto productoBuscado) throws ProductNotFoundException {
		productRepo.save(productoBuscado);
		
	}

	public void eliminarProducto(int codigo) throws ProductNotFoundException {
		Producto deletedProduct = searchProduct(codigo);
		deletedProduct.setEstado("Inactivo");
		productRepo.save(deletedProduct);
	}
	
	public void modificarProducto(Producto productoModificado) throws ProductNotFoundException, WithoutChangesException, BlankDataException {
		Producto productoOriginal = searchProduct(productoModificado.getCodigo());
		
		if(productoOriginal.getNombre().equalsIgnoreCase(productoModificado.getNombre()) && 
				productoOriginal.getDescripcion().equalsIgnoreCase(productoModificado.getDescripcion()) &&
				productoOriginal.getMarca().equalsIgnoreCase(productoModificado.getMarca()) &&
				productoOriginal.getPrecio() == productoModificado.getPrecio() && productoOriginal.getStock() == productoModificado.getStock()
				&& productoOriginal.getEstado().equalsIgnoreCase(productoModificado.getEstado())) {
			throw new WithoutChangesException();
		}
		
		if(productoModificado.getNombre().isEmpty() || productoModificado.getDescripcion().isEmpty() ||
				productoModificado.getMarca().isEmpty() || productoModificado.getPrecio() == 0 || productoModificado.getStock() == 0
				|| productoModificado.getEstado().isEmpty()) {
			throw new BlankDataException();
		}
		
		if(!productoOriginal.getNombre().equalsIgnoreCase(productoModificado.getNombre()) && !productoModificado.getNombre().isEmpty()) {
			productoOriginal.setNombre(productoModificado.getNombre());
		}
		if(!productoOriginal.getDescripcion().equalsIgnoreCase(productoModificado.getDescripcion()) && !productoModificado.getDescripcion().isEmpty()) {
			productoOriginal.setDescripcion(productoModificado.getDescripcion());
		}
		if(!productoOriginal.getMarca().equalsIgnoreCase(productoModificado.getMarca()) && !productoModificado.getMarca().isEmpty()) {
			productoOriginal.setMarca(productoModificado.getMarca());
		}
		if(productoOriginal.getPrecio() != 0 && productoModificado.getPrecio() != 0) {
			productoOriginal.setPrecio(productoModificado.getPrecio());
		}
		if(productoOriginal.getStock() != 0 && productoModificado.getStock() != 0) {
			productoOriginal.setStock(productoModificado.getStock());
		}
		if(!productoOriginal.getEstado().equalsIgnoreCase(productoModificado.getEstado()) && !productoModificado.getEstado().isEmpty()) {
			productoOriginal.setEstado(productoModificado.getEstado());
		}
		productRepo.save(productoModificado);
		
	}
}
