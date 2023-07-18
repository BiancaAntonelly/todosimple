package com.biancaantonelly.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biancaantonelly.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	//buscar uma lista de task de um usuário
	
	
	//já tem dentro do JPA
	//Optional <Task> findBy(Long id);
	
	List <Task> findByUsuario_Id(Long id);
	
	//@Query(value = "Select t FROM Task t WHERE t.user.id = :id")
	//List <Task> findByUsuario_Id(@Param("id") Long id);
}
