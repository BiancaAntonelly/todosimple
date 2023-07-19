package com.biancaantonelly.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biancaantonelly.todosimple.models.Task;
import com.biancaantonelly.todosimple.models.Usuario;
import com.biancaantonelly.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Task findById(Long id) {
	    Task task = this.taskRepository.findById(id).orElse(null);
	    if (task == null) {
	        throw new RuntimeException(
	                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName());
	    }
	    return task;
	}

	public Task create (Task obj) {
		Usuario usuario = this.usuarioService.findById(obj.getUsuario().getId());  
		obj.setId(null);
		obj.setUsuario(usuario);
		return this.taskRepository.save(obj);
	}
	
	
	public Task update (Task obj) {
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.taskRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
}



















