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
@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(requests -> requests
                // Endpoints públicos
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/auth/**").permitAll() // Para login/registro
                .requestMatchers("/h2-console/**").permitAll()
                
                // Swagger UI e documentação - LIBERADO PARA ACESSO
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                
                // Funcionários - apenas ADMIN e RH
                .requestMatchers(HttpMethod.GET, "/funcionarios/**").hasAnyRole("ADMIN", "RH")
                .requestMatchers(HttpMethod.POST, "/funcionarios/**").hasAnyRole("ADMIN", "RH")
                .requestMatchers(HttpMethod.PUT, "/funcionarios/**").hasAnyRole("ADMIN", "RH")
                .requestMatchers(HttpMethod.DELETE, "/funcionarios/**").hasAnyRole("ADMIN", "RH")
                
                // Categorias - ADMIN e COMERCIAL
                .requestMatchers(HttpMethod.GET, "/categoria/**").permitAll() // Leitura liberada
                .requestMatchers(HttpMethod.POST, "/categoria/**").hasAnyRole("ADMIN", "COMERCIAL")
                .requestMatchers(HttpMethod.PUT, "/categoria/**").hasAnyRole("ADMIN", "COMERCIAL")
                .requestMatchers(HttpMethod.DELETE, "/categoria/**").hasAnyRole("ADMIN", "COMERCIAL")
                
                // Produtos - ADMIN e COMERCIAL para modificações
                .requestMatchers(HttpMethod.GET, "/produto/**").permitAll() // Leitura liberada para catálogo
                .requestMatchers(HttpMethod.POST, "/produto/**").hasAnyRole("ADMIN", "COMERCIAL")
                .requestMatchers(HttpMethod.PUT, "/produto/**").hasAnyRole("ADMIN", "COMERCIAL")
                .requestMatchers(HttpMethod.DELETE, "/produto/**").hasAnyRole("ADMIN", "COMERCIAL")
                
                // Pedidos - leitura para usuários autenticados, modificação para ADMIN e COMERCIAL
                .requestMatchers(HttpMethod.GET, "/pedidos/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/pedidos/**").authenticated() // Usuários podem fazer pedidos
                .requestMatchers(HttpMethod.PUT, "/pedidos/**").hasAnyRole("ADMIN", "COMERCIAL")
                .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasAnyRole("ADMIN", "COMERCIAL")
                
                // Qualquer outra requisição precisa estar autenticada
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers -> headers.frameOptions().disable());

        // 1º - Filtro de Autorização (verifica token)
        http.addFilterBefore(new JwtAuthorizationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), 
                jwtUtil, 
                userDetailsService),
                UsernamePasswordAuthenticationFilter.class);
        
        // 2º - Filtro de Autenticação (processa login)
        http.addFilterAfter(new JwtAuthenticationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), 
                jwtUtil),
                JwtAuthorizationFilter.class);

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
        
        // Origens permitidas
        corsConfiguration.setAllowedOrigins(Arrays.asList(
            "http://localhost:5173", 
            "http://localhost:2000",
            "http://localhost:3000" 
        ));
        
        // Métodos HTTP permitidos
        corsConfiguration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"
        ));
        
        // CORREÇÃO: Headers essenciais para JWT
        corsConfiguration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "X-Requested-With",
            "Accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        // Permite envio de credenciais
        corsConfiguration.setAllowCredentials(true);
        
        // Headers expostos para o cliente
        corsConfiguration.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials"
        ));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}