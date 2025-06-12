package org.serratec.backend.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    
    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            System.out.println("Teste1");
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Teste2 - Usuário autenticado com roles: " + auth.getAuthorities());
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.isValidToken(token)) {
            System.out.println("Token válido: " + token);
            String username = jwtUtil.getUsername(token);
            System.out.println("Username: " + username);
            
            // OPÇÃO 1: Usar roles do token (mais eficiente)
            List<String> roles = jwtUtil.getRoles(token);
            if (roles != null && !roles.isEmpty()) {
                List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
                
                System.out.println("Roles extraídas do token: " + roles);
                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
            
            // Fallback - buscar do banco (caso não tenha roles no token)
            UserDetails user = userDetailsService.loadUserByUsername(username);
            System.out.println("Roles do banco: " + user.getAuthorities());
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}
