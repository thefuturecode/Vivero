package cl.ubb.scrumitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ubb.scrumitos.model.Funcionario;
import cl.ubb.scrumitos.model.Vale;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer>{
	
}
