package org.serratec.backend.config;

import java.util.Arrays;

import org.serratec.backend.security.JwtAuthenticationFilter;
import org.serratec.backend.security.JwtAuthorizationFilter;
import org.serratec.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
//@Configuration
public class SecurityConfig {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        //areas para liberacao de endpoints
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/public/**").permitAll()
	            //.requestMatchers(HttpMethod.GET,"/funcionarios").permitAll()
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/funcionarios").hasAnyRole("ADMIN", "RH")
	            .requestMatchers(HttpMethod.POST, "/funcionarios").hasAnyRole("ADMIN", "RH")
	            .requestMatchers(HttpMethod.PUT, "/funcionarios").hasAnyRole("ADMIN", "RH")
	            .requestMatchers(HttpMethod.DELETE, "/funcionarios").hasAnyRole("ADMIN", "RH")
	            
	            .requestMatchers(HttpMethod.POST, "/categoria").hasAnyRole("ADMIN", "COMERCIAL")
	            .requestMatchers(HttpMethod.PUT, "/categoria").hasAnyRole("ADMIN", "COMERCIAL")
	            .requestMatchers(HttpMethod.DELETE, "/categoria").hasAnyRole("ADMIN", "COMERCIAL")
	            
	            .requestMatchers(HttpMethod.POST, "/produto").hasAnyRole("ADMIN", "COMERCIAL")
	            .requestMatchers(HttpMethod.PUT, "/produto").hasAnyRole("ADMIN", "COMERCIAL")
	            .requestMatchers(HttpMethod.DELETE, "/produto").hasAnyRole("ADMIN", "COMERCIAL")
	            
	            .requestMatchers(HttpMethod.PUT, "/pedidos").hasAnyRole("ADMIN", "COMERCIAL")
	            .requestMatchers(HttpMethod.DELETE, "/pedidos").hasAnyRole("ADMIN", "COMERCIAL")
	            
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .headers(headers -> headers.frameOptions().disable());

	    http.addFilterBefore(new JwtAuthenticationFilter(
	            authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
	            UsernamePasswordAuthenticationFilter.class);

	    http.addFilterBefore(new JwtAuthorizationFilter(
	            authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService),
	            UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}
