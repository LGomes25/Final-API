package org.serratec.backend.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

@Configuration
public class OpenApiConfig {
    
    @Value("${bookcommerce.api.dev-url:http://localhost:8080}")
    private String devUrl;
    
    @Value("${bookcommerce.api.prod-url:https://api.bookcommerce.com}")
    private String prodUrl;
    
    @Bean
    public OpenAPI bookCommerceOpenAPI() {
        return new OpenAPI()
                .servers(getServers())
                .info(getApiInfo())
                .components(getComponents())
                .addSecurityItem(getSecurityRequirement());
    }
    
    private List<Server> getServers() {
        Server devServer = new Server()
                .url(devUrl)
                .description("Ambiente de Desenvolvimento - Local");
        
        Server prodServer = new Server()
                .url(prodUrl)
                .description("Ambiente de Produção");
        
        return List.of(devServer, prodServer);
    }
    
    private Info getApiInfo() {
        Contact contact = new Contact()
                .name("Suporte BookCommerce")
                .email(" ")
                .url(" ");
        
        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");
        
        return new Info()
                .title("BookCommerce API")
                .version("1.0.0")
                .description("A API de E-commerce de Livros é uma interface RESTful desenvolvida para gerenciar todo o fluxo de uma livraria online. Ela oferece endpoints para inserir, editar, remover e listar livros, funcionários, clientes e seus pedidos, proporcionando uma gestão completa e eficiente do sistema. " +
                        "Donos: Eduardha,Leonardo Soares, Leorick, Maria Vitória, Mateus dos Santos-Grupo4")
                .termsOfService("https://bookcommerce.com/terms")
                .contact(contact)
                .license(license);
    }
    
    private Components getComponents() {
        return new Components()
                .addSecuritySchemes("Bearer Authentication", 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("🔑 COMO USAR:\n" +
                                   "1. Faça login em /auth/login\n" +
                                   "2. Copie o 'token' da resposta\n" +  
                                   "3. Cole aqui: Bearer {seu_token}\n" +
                                   "4. Clique em 'Authorize'"));
    }
    
    private SecurityRequirement getSecurityRequirement() {
        return new SecurityRequirement().addList("Bearer Authentication");
    }
}

//AIprimeiro
//package org.serratec.backend.config;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.Components;
//
//@Configuration
//public class OpenApiConfig {
//    
//    @Value("${bookcommerce.api.dev-url:http://localhost:8080}")
//    private String devUrl;
//    
//    @Value("${bookcommerce.api.prod-url:https://api.bookcommerce.com}")
//    private String prodUrl;
//    
//    @Bean
//    public OpenAPI bookCommerceOpenAPI() {
//        return new OpenAPI()
//                .servers(getServers())
//                .info(getApiInfo())
//                .components(getComponents())
//                .addSecurityItem(getSecurityRequirement());
//    }
//    
//    private List<Server> getServers() {
//        Server devServer = new Server()
//                .url(devUrl)
//                .description("Ambiente de Desenvolvimento - Local");
//        
//        Server prodServer = new Server()
//                .url(prodUrl)
//                .description("Ambiente de Produção");
//        
//        return List.of(devServer, prodServer);
//    }
//    
//    private Info getApiInfo() {
//        Contact contact = new Contact()
//                .name("Suporte BookCommerce")
//                .email(" ")
//                .url(" ");
//        
//        License license = new License()
//                .name("Apache 2.0")
//                .url("https://www.apache.org/licenses/LICENSE-2.0");
//        
//        return new Info()
//                .title("BookCommerce API")
//                .version("1.0.0")
//                .description("A API de E-commerce de Livros é uma interface RESTful desenvolvida para gerenciar todo o fluxo de uma livraria online. Ela oferece endpoints para inserir, editar, remover e listar livros, funcionários, clientes e seus pedidos, proporcionando uma gestão completa e eficiente do sistema. " +
//                        "Donos: Eduardha,Leonardo Soares, Leorick, Maria Vitória, Mateus dos Santos-Grupo4")
//                .termsOfService("https://bookcommerce.com/terms")
//                .contact(contact)
//                .license(license);
//    }
//    
//    private Components getComponents() {
//        return new Components()
//                .addSecuritySchemes("Bearer Authentication", 
//                    new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                        .description("Insira o token JWT obtido no endpoint de login"));
//    }
//    
//    private SecurityRequirement getSecurityRequirement() {
//        return new SecurityRequirement().addList("Bearer Authentication");
//    }
//}

//Roni
//package org.serratec.backend.config;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//
//@Configuration
//public class OpenApiConfig {
//
//	@Value("${bookcommerce.api.dev-url:http://localhost:8080}")
//	private String devUrl;
//
//	@Value("${bookcommerce.api.prod-url:https://api.bookcommerce.com}")
//	private String prodUrl;
//
//	@Bean
//	public OpenAPI bookCommerceOpenAPI() {
//		return new OpenAPI()
//				.servers(getServers())
//				.info(getApiInfo());
//	}
//
//	private List<Server> getServers() {
//		Server devServer = new Server()
//				.url(devUrl)
//				.description("Ambiente de Desenvolvimento - Local");
//
//		Server prodServer = new Server()
//				.url(prodUrl)
//				.description("Ambiente de Produção");
//
//		return List.of(devServer, prodServer);
//	}
//
//	private Info getApiInfo() {
//		Contact contact = new Contact()
//				.name("Suporte BookCommerce")
//				.email(" ")
//				.url(" ");
//
//		License license = new License()
//				.name("Apache 2.0")
//				.url("https://www.apache.org/licenses/LICENSE-2.0");
//
//		return new Info()
//				.title("BookCommerce API")
//				.version("1.0.0")
//				.description("A API de E-commerce de Livros é uma interface RESTful desenvolvida para gerenciar todo o fluxo de uma livraria online. Ela oferece endpoints para inserir, editar, remover e listar livros, funcionários, clientes e seus pedidos, proporcionando uma gestão completa e eficiente do sistema. " +
//						"Donos: Eduardha,Leonardo Soares, Leorick, Maria Vitória, Mateus dos Santos-Grupo4")
//				.termsOfService("https://bookcommerce.com/terms")
//				.contact(contact)
//				.license(license);
//	}
//}