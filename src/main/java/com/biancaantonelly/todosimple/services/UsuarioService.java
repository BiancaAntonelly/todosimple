package com.biancaantonelly.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.biancaantonelly.todosimple.models.Usuario;
import com.biancaantonelly.todosimple.repositories.TaskRepository;
import com.biancaantonelly.todosimple.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TaskRepository taskRepository;
    
    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Transactional
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj = this.usuarioRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
