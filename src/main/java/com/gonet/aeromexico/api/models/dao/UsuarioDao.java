package com.gonet.aeromexico.api.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonet.aeromexico.api.models.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

}
