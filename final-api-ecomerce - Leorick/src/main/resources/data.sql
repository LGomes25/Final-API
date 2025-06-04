--Insercao Categorias--
INSERT INTO categoria (nome) VALUES ('Ficção');
INSERT INTO categoria (nome) VALUES ('API - Terror');
INSERT INTO categoria (nome) VALUES ('Fantasia');
INSERT INTO categoria (nome) VALUES ('Ciência');
INSERT INTO categoria (nome) VALUES ('História');
INSERT INTO categoria (nome) VALUES ('Biografia');
INSERT INTO categoria (nome) VALUES ('Tecnologia');
INSERT INTO categoria (nome) VALUES ('Autoajuda');
INSERT INTO categoria (nome) VALUES ('Romance');
INSERT INTO categoria (nome) VALUES ('Mistério');

--Insercao de Endereços--
INSERT INTO endereco (cep, logradouro, bairro, localidade, uf) VALUES
('25680-000', 'Rua 16 de Março', 'Centro', 'Petrópolis', 'RJ'),
('25670-000', 'Avenida Ipiranga', 'Quitandinha', 'Petrópolis', 'RJ'),
('25650-000', 'Rua do Imperador', 'Valparaíso', 'Petrópolis', 'RJ'),
('25660-000', 'Avenida Barão do Rio Branco', 'Alto da Serra', 'Petrópolis', 'RJ'),
('25685-000', 'Rua Teresa', 'Mosela', 'Petrópolis', 'RJ');

--Insercao de Perfis--
INSERT INTO PERFIL (ID, NOME) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_COMERCIAL'),
(3, 'ROLE_DP'),
(4, 'ROLE_RH');

--Insercao de Clientes--
INSERT INTO cliente (nome, telefone, email, cpf, senha, id_endereco) VALUES
('João Silva', '11987654321', 'joao.silva@gmail.com', '52373484080', '123456', 1),
('Maria Souza', '11976543210', 'maria.souza@gmail.com', '77586629005', '123456', 2),
('Carlos Oliveira', '11965432109', 'carlos.oliveira@gmail.com', '98857216063', '123456', 3),
('Ana Pereira', '11954321098', 'ana.pereira@gmail.com', '15467895028', '123456', 4),
('Pedro Santos', '11943210987', 'pedro.santos@gmail.com', '65931288058', '123456', 5),
('Mariana Costa', '11932109876', 'mariana.costa@gmail.com', '80977048039', '123456', 1),
('Fernando Almeida', '11921098765', 'fernando.almeida@gmail.com', '18602703015', '123456', 2),
('Juliana Lima', '11910987654', 'juliana.lima@gmail.com', '80090173023', '123456', 3),
('Rafael Oliveira', '11909876543', 'rafael.oliveira@gmail.com', '98796284030', '123456', 4),
('Camila Fernandes', '11908765432', 'camila.fernandes@gmail.com', '69258957040', '123456', 5);

--Insercao de funcionarios--
INSERT INTO funcionario (nome, telefone, email, cpf, senha, salario, data_admissao, id_endereco) VALUES
('Leorick', '22998877601', 'leorick@email.com', '72403628093', '123456', 15000.00, '2024-05-01', 1),
('Eduardha', '21987654321', 'eduardha@email.com', '20439515050', '123456', 18000.50, '2023-10-15', 2),
('Mateus', '22991234567', 'mateus@email.com', '62102761053', '123456', 22000.75, '2022-11-20', 3),
('Maria Vitoria', '21999887766', 'maria@email.com', '17499719090', '123456', 19050.90, '2021-09-10', 4),
('Leonardo', '22991122334', 'leonardo@email.com', '93178199071', '123456', 1750.25, '2020-07-05', 5),
('Gabriela', '21993456789', 'gabriela@email.com', '13299649000', '123456', 2100.00, '2024-03-12', 1),
('Juliana', '22994567890', 'juliana@email.com', '55192511003', '123456', 1890.00, '2023-08-22', 2),
('Rodrigo', '21995678901', 'rodrigo@email.com', '87549618070', '123456', 2050.00, '2022-06-18', 3),
('Fernanda', '22996789012', 'fernanda@email.com', '09378586090', '123456', 2300.00, '2021-12-05', 4),
('Lucas', '21997890123', 'lucas@email.com', '39258346050', '123456', 1700.00, '2020-01-25', 5);

--Insercao de funcionarios_perfil--
INSERT INTO funcionario_perfil (data_criacao, id_perfil, id_funcionario) VALUES
('2025-06-01', 1, 1),
('2025-06-01', 1, 2),
('2025-06-01', 1, 3),
('2025-06-01', 1, 4),
('2025-06-01', 1, 5),
('2025-06-01', 2, 6),
('2025-06-01', 2, 7),
('2025-06-01', 3, 8),
('2025-06-01', 3, 9),
('2025-06-01', 4, 10);

-- Inserção de produtos --
INSERT INTO produto (nome, preco, id_categoria, isbn) VALUES
('O Senhor dos Anéis', 49.90, 3,'978-8595084759'),
('Duna', 59.90, 1,'978-0441172719'),
('It: A Coisa', 39.90, 2,'978-0451149510'),
('O Código Da Vinci', 34.90, 10,'978-0307474278'),
('Sapiens: Uma Breve História da Humanidade', 44.90, 5,'978-8525432186'),
('Steve Jobs', 39.90, 6,'978-0307956231'),
('O Poder do Hábito', 34.90, 8,'978-8539004119'),
('Clean Code', 79.90, 7,'978-0132350884'),
('A Revolução dos Bichos', 29.90, 1,'978-8551837726'),
('O Hobbit', 39.90, 3,'978-8595084742'),
('1984', 29.90, 1,'978-8535914849'),
('A Arte da Guerra', 24.90, 5,'978-8594318602'),
('O Príncipe', 29.90, 4,'978-8563560032'),
('A Coragem de Ser Imperfeito', 27.90, 8,'978-8539004119'),
('Mais esperto que o diabo', 24.90, 8,'978-8568014004'),
('A Menina que Roubava Livros', 34.90, 9,'978-8598078175'),
('Orgulho e Preconceito', 29.90, 9,'978-8572329798'),
('Segredos Da Mente Milionaria', 22.90, 1,'978-8575422397'),
('A Cabana', 32.90, 9,'978-8599296363'),
('O Homem em Busca de um Sentido', 26.90, 6,'978-8531612824');

-- Inserção de Pedidos --
INSERT INTO pedido (data_pedido, status, id_cliente) VALUES
('2023-10-01', 'PENDENTE', 1),
('2023-09-30', 'PROCESSANDO', 2),
('2023-09-29', 'ENVIADO', 3),
('2023-09-28', 'ENTREGUE', 4),
('2023-09-27', 'CANCELADO', 5),
('2023-09-26', 'PENDENTE', 6),
('2023-09-25', 'PROCESSANDO', 7),
('2023-09-24', 'ENVIADO', 8),
('2023-09-23', 'ENTREGUE', 9),
('2023-09-22', 'CANCELADO', 10),
('2023-09-21', 'PENDENTE', 1),
('2023-09-20', 'PROCESSANDO', 2),
('2023-09-19', 'ENVIADO', 3),
('2023-09-18', 'ENTREGUE', 4),
('2023-09-17', 'CANCELADO', 5),
('2023-09-16', 'PENDENTE', 6),
('2023-09-15', 'PROCESSANDO', 7),
('2023-09-14', 'ENVIADO', 8),
('2023-09-13', 'ENTREGUE', 9),
('2023-09-12', 'CANCELADO', 10);

-- Inserção de dados na tabela pedido_produto --
INSERT INTO pedido_produto (quantidade, valor_venda, desconto, id_pedido, id_produto) VALUES
(2, 49.90, 0.00, 1, 1),
(1, 59.90, 5.00, 1, 2),
(3, 39.90, 0.00, 2, 3),
(1, 34.90, 2.00, 2, 4),
(1, 44.90, 0.00, 3, 5),
(2, 39.90, 3.99, 3, 6),
(1, 34.90, 0.00, 4, 7),
(1, 79.90, 7.99, 4, 8),
(2, 29.90, 0.00, 5, 9),
(1, 39.90, 3.00, 5, 10),
(1, 29.90, 0.00, 6, 11),
(1, 24.90, 2.49, 6, 12),
(2, 29.90, 0.00, 7, 13),
(1, 27.90, 0.00, 7, 14),
(1, 24.90, 0.00, 8, 15),
(1, 34.90, 3.50, 8, 16),
(1, 29.90, 0.00, 9, 17),
(1, 22.90, 2.29, 9, 18),
(2, 32.90, 0.00, 10, 19),
(1, 26.90, 0.00, 10, 20),
(1, 49.90, 4.99, 11, 1),
(2, 39.90, 0.00, 11, 3),
(1, 34.90, 0.00, 12, 4),
(1, 44.90, 4.49, 12, 5),
(1, 39.90, 0.00, 13, 6),
(1, 79.90, 7.99, 13, 7),
(1, 34.90, 0.00, 14, 8),
(1, 29.90, 2.99, 14, 9),
(2, 39.90, 0.00, 15, 10),
(1, 29.90, 0.00, 15, 11),
(1, 24.90, 0.00, 16, 12),
(1, 29.90, 2.99, 16, 13),
(1, 27.90, 0.00, 17, 14),
(1, 24.90, 0.00, 17, 15),
(1, 34.90, 0.00, 18, 16),
(1, 29.90, 2.99, 18, 17),
(1, 22.90, 0.00, 19, 18),
(1, 32.90, 3.29, 19, 19),
(2, 26.90, 0.00, 20, 20),
(1, 49.90, 0.00, 20, 1);
