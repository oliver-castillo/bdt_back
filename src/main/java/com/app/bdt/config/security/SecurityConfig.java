package com.app.bdt.config.security;

import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final IUserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/token").permitAll()
            .anyRequest().authenticated();
  }

  /*@Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  }*/

  /*@Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/countries").hasRole("RECLUTADOR") // Configura las rutas que requieren roles específicos
            .antMatchers("/cities").hasRole("VISITANTE")
            .antMatchers("/login", "/register").permitAll() // Rutas permitidas para todos
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .permitAll();
  }*/
}