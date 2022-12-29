package com.professorangoti.condominio.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.professorangoti.condominio.model.Usuario;

public class CondominioUserDetails implements UserDetails {

  private Usuario usuario;

  public CondominioUserDetails(Usuario usuario) {
    this.usuario = usuario;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String[] papeis = usuario.getPapeis().split(",");
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (String papel : papeis) {
      authorities.add(new SimpleGrantedAuthority(papel));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return usuario.getSenha();
  }

  @Override
  public String getUsername() {
    return usuario.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}