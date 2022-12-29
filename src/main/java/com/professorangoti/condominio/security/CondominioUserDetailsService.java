package com.professorangoti.condominio.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.professorangoti.condominio.model.Usuario;
import com.professorangoti.condominio.repository.UsuarioRepository;


public class CondominioUserDetailsService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.buscaPorEmail(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário não autenticado!");
    }
    System.out.println(usuario.getPapeis());
    return new CondominioUserDetails(usuario);
  }
}
