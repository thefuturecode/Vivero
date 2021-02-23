package cl.ubb.scrumitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ubb.scrumitos.model.Producto;
import cl.ubb.scrumitos.model.Vale;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer>{

	

}
