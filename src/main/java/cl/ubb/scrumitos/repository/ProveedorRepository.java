package cl.ubb.scrumitos.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ubb.scrumitos.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

	/*public Object findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Proveedor foundProvider) {

		
	}*/
	
}
