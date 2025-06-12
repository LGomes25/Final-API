# 📚 Sebo Online - API E-commerce de Livros Usados

Projeto backend desenvolvido em Java com Spring Boot para gerenciamento de um sebo online. A aplicação permite o cadastro e controle de clientes, produtos (livros), pedidos, cupons, avaliações e muito mais.

## 👥 Autores

Este projeto foi desenvolvido por:

* **Eduardha Fecher**
* **Maria Vitoria Martelli**
* **Leorick Felippe**
* **Mateus Fernandes**
* **Leonardo Gomes**

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- H2 e PostgreSQL
- Lombok
- Swagger (OpenAPI)
- Postman (para testes)

## 📌 Funcionalidades da API

A API expõe endpoints RESTful para:

### 📦 Produtos (`/produtos`)
- `GET /produtos` – Listar todos
- `GET /produtos/{id}` – Buscar por ID
- `GET /produtos/faixa?faixa1=XX&faixa2=YY` – Buscar por faixa de preço
- `POST /produtos` – Inserir
- `PUT /produtos/{id}` – Atualizar
- `DELETE /produtos/{id}` – Excluir

### 👤 Clientes (`/clientes`)
- `GET /clientes` – Listar
- `POST /clientes` – Criar
- `PUT /clientes/{id}` – Atualizar
- `DELETE /clientes/{id}` – Excluir

### 🛒 Pedidos (`/pedidos`)
- `GET /pedidos` – Listar
- `GET /pedidos/{id}` – Buscar por ID
- `POST /pedidos` – Criar com produtos
- `PUT /pedidos/{id}/status?status=PROCESSANDO` – Atualizar status
- `DELETE /pedidos/{id}` – Excluir

### 🧾 Cupons (`/cupons`)
- `GET /cupons` – Listar todos
- `GET /cupons/codigo/{codigo}` – Buscar por código
- `POST /cupons` – Criar
- `PUT /cupons/codigo/{codigo}` – Atualizar
- `DELETE /cupons/{codigo}` – Excluir

### 🗺️ Endereços (`/enderecos`)
- `GET /enderecos/{cep}` – Buscar por CEP (via API externa)

### 🧑‍💼 Funcionários (`/funcionarios`)
- `GET /funcionarios` – Listar
- `POST /funcionarios` – Inserir (com perfil)
- `PUT /funcionarios/{id}` – Atualizar
- `DELETE /funcionario/{id}` – Excluir

### ✅ Autenticação
- `POST /login` – Retorna JWT (token) para autenticação de funcionários

### 💬 Avaliações (`/avaliacoes`)
- `GET /avaliacoes` – Listar
- `GET /avaliacoes/produto/{id}` – Por produto
- `POST /avaliacoes` – Criar
- `PUT /avaliacoes` – Atualizar
- `DELETE /avaliacoes/{id}` – Excluir

### 💖 Lista de Desejos (`/listaDeDesejos`)
- `GET /listaDeDesejos` – Listar todas
- `GET /listaDeDesejos/{id}` – Por ID
- `GET /listaDeDesejos/cliente/{idCliente}` – Por cliente
- `POST /listaDeDesejos` – Criar
- `DELETE /listaDeDesejos/{id}` – Excluir

## 🔧 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sebo-online.git
   cd sebo-online
   ```

2. Importe no **Spring Tool Suite** ou IDE de sua preferência.

3. Acesse:
   - API: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
   - Swagger: `http://localhost:8080/swagger-ui.html` (se habilitado)

## 📋 Etapas Futuras

- ⚠️ **Verificar tratamento de mensagens de erro com clareza**
- ⚙️ **Documentar completamente os endpoints no Swagger**
- 🎯 **Implementar lógica de aplicação de cupons nos pedidos**

## 📁 Arquivos de Testes

Os endpoints podem ser testados com o arquivo Postman disponível em:

📂 [`EndPoints Sebo Online.postman_collection.json`](./EndPoints%20Sebo%20Online.postman_collection.json)

## 📜 Licença

Este projeto é open-source e está sob a licença MIT.