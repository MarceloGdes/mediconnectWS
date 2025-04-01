CREATE TABLE especialidade(
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE medico(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	crm VARCHAR(10) NOT NULL,
	especialidade_id INT REFERENCES especialidade(id) NOT NULL,
	logradouro VARCHAR(255) NOT NULL,
    numero INT,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
	ativo BOOL DEFAULT TRUE
);

CREATE TABLE paciente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	logradouro VARCHAR(255) NOT NULL,
    numero INT,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
	cidade VARCHAR(100) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	ativo BOOL DEFAULT TRUE
);


CREATE TABLE consulta(
	id SERIAL PRIMARY KEY,
	paciente_id INT REFERENCES paciente(id) NOT NULL,
	medico_id INT REFERENCES medico(id) NOT NULL,
	data_agendamento TIMESTAMP NOT NULL,
	cancelada BOOL DEFAULT FALSE
);

CREATE TABLE motivo_cancelamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE historico_cancelamento_consulta(
	id SERIAL PRIMARY KEY,
	consulta_id INT NOT NULL REFERENCES consulta(id),
	motivo_cancelamento_id INT NOT NULL REFERENCES motivo_cancelamento(id)
);

-- Inserindo as especialidades padrão
INSERT INTO especialidade (descricao) VALUES 
('Ortopedia'),
('Cardiologia'),
('Ginecologia'),
('Dermatologia');

-- Inserindo os motivos padrão
INSERT INTO motivo_cancelamento (descricao) VALUES 
('paciente desistiu'),
('médico cancelou'),
('outros');