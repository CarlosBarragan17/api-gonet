package com.gonet.aeromexico.api.services;

import com.gonet.aeromexico.api.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
