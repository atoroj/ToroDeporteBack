package com.torodeporte.toroDeporteBackEnd.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/productos",  "/api/productos/page/**").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.GET, "/api/productos/{id}").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/productos/upload").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/productos/update").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.DELETE, "/api/productos/delete/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/empleados", "/api/empleados/page/**").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.GET, "/api/empleados/{id}").hasAnyRole("DEPENDIENTE", "ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/empleados/create", "/api/empleados/add/rol").hasAnyRole("ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/empleados/upload").hasAnyRole("ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/empleados/update").hasAnyRole("ENCARGADO")
		.antMatchers(HttpMethod.POST, "/api/change/password").hasAnyRole("ENCARGADO")
		.antMatchers(HttpMethod.DELETE, "/api/empleados/delete/**").hasRole("ENCARGADO");
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		 FilterRegistrationBean<CorsFilter> bean = new  FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		 bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		 return bean;
	}
}
