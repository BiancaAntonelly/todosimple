package com.biancaantonelly.todosimple.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {
	public interface CreateUsuario{}
	public interface UpdateUsuario{}
	
	
	
	
	public static final String TABLE_NAME = "usuario";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "username", length = 100, nullable = false, unique = true)
	@NotNull(groups = CreateUsuario.class)
	@NotEmpty (groups = CreateUsuario.class)
	@Size(groups = CreateUsuario.class, min=2, max=100)
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password", length = 60, nullable = false)
	@NotNull (groups = {CreateUsuario.class, UpdateUsuario.class})
	@NotEmpty (groups = {CreateUsuario.class, UpdateUsuario.class})
	@Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min=8, max=60)
	private String password;

	
	@OneToMany(mappedBy = "usuario")
	private List<Task> tasks = new ArrayList<Task>();
	
	public Usuario() {
	}
	
	public Usuario(Long id, String username,String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null) 
			return false;
		if (!(obj instanceof Usuario)) 
			return false;
		Usuario other = (Usuario) obj;
		if (this.id == null)
			if(other.id != null)
				return false;
			else if (!this.id.equals(other.id))
				return false;
		return Objects.equals(this.id,other.id) && Objects.equals(this.username,other.username) && Objects.equals(this.password,other.password);
	}	
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	}

	
	

}
