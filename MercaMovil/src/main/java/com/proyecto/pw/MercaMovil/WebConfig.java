package com.proyecto.pw.MercaMovil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Clase de configuración para la aplicación web.
 * Configura las vistas y la gestión de recursos estáticos para una aplicación Spring MVC.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.proyecto.pw.Controller" })
public class WebConfig implements WebMvcConfigurer {

    /**
     * Define el resolutor de vistas interno para la aplicación web.
     * Configura el uso de JSTL para las vistas JSP ubicadas en "/WEB-INF/jsp/".
     *
     * @return el resolutor de vistas configurado.
     */
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Configura la manipulación de recursos estáticos.
     * Define la ubicación de los recursos estáticos en "/resources/".
     *
     * @param registry el registro de manipuladores de recursos.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
    }
}