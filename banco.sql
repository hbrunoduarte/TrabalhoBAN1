CREATE TABLE Motorista (
	id_motorista SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cnh VARCHAR(15) UNIQUE NOT NULL,
	cpf VARCHAR(20) UNIQUE NOT NULL,
	data_nascimento DATE 
)

CREATE TABLE Veiculo (
	id_veiculo SERIAL PRIMARY KEY,
	placa VARCHAR(10) UNIQUE NOT NULL,
	capacidade FLOAT,
	modelo VARCHAR(100)
)

CREATE TABLE Cliente (
	id_cliente SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	endereco VARCHAR(100) NOT NULL
)

CREATE TABLE Entrega (
	id_entrega SERIAL PRIMARY KEY,
	data_pedido DATE NOT NULL,
	status VARCHAR(20) NOT NULL,
	id_cliente INT NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente) 
)

CREATE TABLE Rota (
	id_rota SERIAL PRIMARY KEY,
	id_entrega INT NOT NULL,
	id_motorista INT NOT NULL,
	id_veiculo INT NOT NULL,
	distancia_km FLOAT,
	FOREIGN KEY (id_entrega) REFERENCES Entrega(id_entrega),
	FOREIGN KEY (id_motorista) REFERENCES Motorista(id_motorista),
	FOREIGN KEY (id_veiculo) REFERENCES Veiculo(id_veiculo)
)