package com.proyecto.pw.MercaMovil.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.proyecto.pw.MercaMovil.Service.UsuarioService;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Inyección del servicio de usuario
    @Autowired
    private UsuarioService usuarioServicio;

    /**
     * Configuración global de la autenticación.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio).passwordEncoder(passwordEncoder());
    }

    /**
     * Crea un codificador de contraseñas BCrypt.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Crea un proveedor de autenticación basado en DAO.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Configura la autenticación.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Configura la seguridad HTTP.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .antMatchers("/", "/resources/**", "/signup", "/home", "/about", "/views/modules/**,", "/product-image/**").permitAll()
                        .antMatchers("/profile/**","/carrito/**", "/api/mercadopago**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/productos/**", "/api/descuentos/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/productos/**", "/api/descuentos/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/productos/**", "/api/descuentos/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true))
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()); 
    }
}