package cl.ubb.scrumitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ubb.scrumitos.model.Vale;

@Repository
public interface ValeRepository extends JpaRepository<Vale,Integer> {
	
}
