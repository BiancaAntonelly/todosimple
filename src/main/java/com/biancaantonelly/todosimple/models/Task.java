package com.biancaantonelly.todosimple.models;

import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
	public static final String TABLE_NAME = "task";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, updatable = false)
	private Usuario usuario;
	
	@Column(name = "description", length = 255, nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 255)
	private String description;

	public Task() {
	}

	public Task(Long id, Usuario usuario, String description) {
		this.id = id;
		this.usuario = usuario;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null) 
			return false;
		if (!(obj instanceof Task)) 
			return false;
		Task other = (Task) obj;
		if (this.id == null)
			if(other.id != null)
				return false;
			else if (!this.id.equals(other.id))
				return false;
		return Objects.equals(this.id,other.id) && Objects.equals(this.usuario,other.usuario) && Objects.equals(this.description,other.description);
	}	
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	}
	
	
}