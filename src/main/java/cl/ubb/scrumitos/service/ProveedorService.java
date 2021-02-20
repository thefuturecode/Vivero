package cl.ubb.scrumitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.ubb.scrumitos.exceptions.BlankDataException;
import cl.ubb.scrumitos.exceptions.ProveedorNotFoundException;
import cl.ubb.scrumitos.exceptions.WithoutChangesException;
import cl.ubb.scrumitos.model.Proveedor;
import cl.ubb.scrumitos.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepo;

	@Transactional
	public Proveedor addProvider (Proveedor proveedor) {
		if (proveedor == null) return null;
		else return proveedorRepo.save(proveedor);
	}

	public Proveedor searchProvider(int i) throws ProveedorNotFoundException {
		if (proveedorRepo.findById(i) == null)
			throw new ProveedorNotFoundException();

		return (Proveedor) proveedorRepo.getOne(i);
	}

	public void delete(int i) throws ProveedorNotFoundException {
		Proveedor foundProvider = searchProvider(i);
		foundProvider.setEstado("Inactivo");
		proveedorRepo.save(foundProvider);
	}

	public void modificarProveedor(int id, String rut, String direccion, String telefono, String email, String nombre,
			String estado) throws WithoutChangesException, ProveedorNotFoundException, BlankDataException {
		Proveedor proveedorModificado = searchProvider(id);
		// Caso Sin cambios, mismos datos

		if (proveedorModificado.getNombre().equalsIgnoreCase(nombre)
				&& proveedorModificado.getDireccion().equalsIgnoreCase(direccion)
				&& proveedorModificado.getEmail().equalsIgnoreCase(email)
				&& proveedorModificado.getRut().equalsIgnoreCase(rut)
				&& proveedorModificado.getTelefono().equalsIgnoreCase(telefono)) {
			throw new WithoutChangesException();
		}
		// Caso Con datos en Blanco
		if (nombre.isEmpty() && rut.isEmpty() && email.isEmpty() && estado.isEmpty() && direccion.isEmpty()
				&& telefono.isEmpty()) {
			throw new BlankDataException();
		}
		// Caso donde se modifican los datos
		if (!proveedorModificado.getRut().equalsIgnoreCase(rut) && !rut.isEmpty()) {
			proveedorModificado.setRut(rut);
		}
		if (!proveedorModificado.getDireccion().equalsIgnoreCase(direccion) && !direccion.isEmpty()) {
			proveedorModificado.setDireccion(direccion);
		}
		if (!proveedorModificado.getTelefono().equalsIgnoreCase(telefono) && !telefono.isEmpty()) {
			proveedorModificado.setTelefono(telefono);
		}
		if (!proveedorModificado.getEmail().equalsIgnoreCase(email) && !email.isEmpty()) {
			proveedorModificado.setEmail(email);
		}
		if (!proveedorModificado.getNombre().equalsIgnoreCase(nombre) && !nombre.isEmpty()) {
			proveedorModificado.setNombre(nombre);
		}

		if (!proveedorModificado.getEstado().equalsIgnoreCase(estado) && !estado.isEmpty()) {
			proveedorModificado.setEstado(estado);
		}
		proveedorRepo.save(proveedorModificado);

	}

}
