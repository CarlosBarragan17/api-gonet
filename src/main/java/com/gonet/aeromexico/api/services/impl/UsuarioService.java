package com.gonet.aeromexico.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gonet.aeromexico.api.models.dao.UsuarioDao;
import com.gonet.aeromexico.api.models.entity.Usuario;
import com.gonet.aeromexico.api.services.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

}
