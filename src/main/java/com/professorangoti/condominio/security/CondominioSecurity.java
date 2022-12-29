package com.professorangoti.condominio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CondominioSecurity {

  @Bean
  public UserDetailsService userDetailsService() {
    return new CondominioUserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  // org.springframework.security.web.SecurityFilterChain 
  // Defines a filter chain which is capable of being matched against an HttpServletRequest
  // in order to decide whether it applies to that request. Used to configure a FilterChainProxy.
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
        .antMatchers("/upload", "/cad_apto", "/cad_prop").hasAnyAuthority("MORADOR", "ADMIN") // permissão para criar
        .antMatchers("/fotos_apto", "/rel_apto", "/rel_prop").hasAnyAuthority("USUARIO", "MORADOR", "ADMIN") // permissão para visualizar
        .antMatchers("/excluir_foto", "/excluir_prop").hasAnyAuthority("ADMIN") // permissão para editar/excluir
        .antMatchers("/", "/home").permitAll()
        .anyRequest().authenticated())

        .formLogin(form -> form.loginPage("/login").permitAll())
        .logout().logoutSuccessUrl("/");
    return http.build();
  }
}