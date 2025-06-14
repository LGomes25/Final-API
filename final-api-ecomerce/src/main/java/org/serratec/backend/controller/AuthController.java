package org.serratec.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.serratec.backend.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Faça login aqui primeiro para obter o token!")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(
        summary = "🚀 FAÇA LOGIN AQUI PRIMEIRO!",
        description = "1️⃣ Faça login aqui\n" +
                     "2️⃣ Copie o token da resposta\n" +
                     "3️⃣ Clique no botão 'Authorize' 🔒 no topo da página\n" +
                     "4️⃣ Cole: Bearer {seu_token}\n" +
                     "5️⃣ Agora pode testar todos os endpoints! 🎉"
    )
    @ApiResponse(responseCode = "200", description = "✅ Token gerado com sucesso!")
    @ApiResponse(responseCode = "401", description = "❌ Usuário ou senha inválidos")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Autentica
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username, request.password)
            );

            // Gera token
            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user.getUsername());

            // Resposta simples e clara
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername()));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("❌ Credenciais inválidas!");
        }
    }

    // Classes simples para o Swagger
    public static class LoginRequest {
        public String username;
        public String password;
        
        // Getters e setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        public String token;
        public String username;

        public LoginResponse(String token, String username) {
            this.token = token;
            this.username = username;
        }

        // Getters e setters
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }
}
