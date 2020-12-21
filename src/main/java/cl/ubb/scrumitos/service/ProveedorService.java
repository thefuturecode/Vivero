package cl.ubb.scrumitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.repository.ProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository proveedorRepo;

	public Proveedor searchProvider(int i) throws ProveedorNotFoundException {
		if (proveedorRepo.findById(i) == null) throw new ProveedorNotFoundException();
		
		return (Proveedor) proveedorRepo.findById(i);
	}

	public void delete(int i) throws ProveedorNotFoundException {
		Proveedor foundProvider = searchProvider(i);
		foundProvider.setEstado("Inactivo");
		proveedorRepo.save(foundProvider);
	}

}
