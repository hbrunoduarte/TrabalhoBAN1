-- 1. Inserindo Clientes (Não tem dependências)
INSERT INTO Cliente (nome, telefone, endereco) VALUES 
('Lojas Brasileiras', '(11) 98888-1111', 'Av. Paulista, 1000 - São Paulo, SP'),
('Mercado Central', '(47) 97777-2222', 'Rua das Flores, 123 - Joinville, SC'),
('Papelaria do Estudante', '(21) 96666-3333', 'Av. Rio Branco, 45 - Rio de Janeiro, RJ');

-- 2. Inserindo Motoristas (Não tem dependências)
INSERT INTO Motorista (nome, cnh, cpf, data_nascimento) VALUES 
('Carlos Roberto da Silva', '12345678900', '111.222.333-44', '1985-05-20'),
('Ana Paula Pereira', '98765432100', '555.666.777-88', '1990-11-15'),
('João Marcos Souza', '45612378900', '999.888.777-66', '1982-02-10');

-- 3. Inserindo Veículos (Não tem dependências)
-- A capacidade está em Toneladas/KG (ex: 5000.0 = 5 Toneladas)
INSERT INTO Veiculo (placa, capacidade, modelo) VALUES 
('ABC-1234', 5000.0, 'Mercedes-Benz Accelo 815'),
('XYZ-9876', 15000.0, 'Volvo FH 460'),
('QWE-5555', 1500.0, 'Fiat Fiorino 1.4');

-- 4. Inserindo Entregas (Depende de Clientes)
-- Vinculando aos clientes de ID 1, 2 e 3 que acabamos de criar
INSERT INTO Entrega (data_pedido, status, id_cliente) VALUES 
('2023-11-01', 'Pendente', 1),
('2023-11-02', 'Em andamento', 2),
('2023-11-03', 'Concluída', 3),
('2023-11-04', 'Pendente', 2); -- Cliente 2 fez outro pedido

-- 5. Inserindo Rotas (Depende de Entregas, Motoristas e Veículos)
-- Vinculando a entrega 1 para o motorista 1 no veículo 1
INSERT INTO Rota (id_entrega, id_motorista, id_veiculo, distancia_km) VALUES 
(1, 1, 1, 450.5),
(2, 2, 2, 1200.0),
(3, 3, 3, 45.2);