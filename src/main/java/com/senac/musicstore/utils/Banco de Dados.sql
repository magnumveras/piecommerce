USE PROJETOINTEGRADORIV;

CREATE TABLE Perfil (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    perfil VARCHAR(30) NOT NULL,
    CONSTRAINT primary_key PRIMARY KEY (codigo)
);

CREATE TABLE Usuario (
    codigo INTEGER NOT NULL AUTO_INCREMENT, 
    nome VARCHAR(30) NOT NULL,
    login VARCHAR(20) NOT NULL UNIQUE,
    senha VARCHAR(20) NOT NULL,
    codigoperfil INTEGER NOT NULL,
    CONSTRAINT primary_keyUsu PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyUsu FOREIGN KEY (codigoperfil) REFERENCES Perfil(codigo)
);

CREATE TABLE Cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    sobrenome VARCHAR(70) NOT NULL,
    sexo VARCHAR(15) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    rg VARCHAR(12) NOT NULL,
    datanasc VARCHAR(12),
    telefone VARCHAR(14),
    email VARCHAR(60),
    endereco VARCHAR(70) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro varchar(15) NOT NULL,
    complemento VARCHAR(60),
    cidade VARCHAR(30) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    codigousuario INTEGER NOT NULL,
    ofertas boolean NOT NULL,
    CONSTRAINT primary_keycli PRIMARY KEY (id),
    CONSTRAINT foreign_keycli FOREIGN KEY (codigousuario) REFERENCES Usuario(codigo)
);

CREATE TABLE Carrinho (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigocliente INTEGER NOT NULL,
    datacarrinho TIMESTAMP NOT NULL,
    valortotal DECIMAL(8,2) NOT NULL,
    CONSTRAINT primary_keycar PRIMARY KEY (codigo),
    CONSTRAINT foreign_keycar FOREIGN KEY (codigocliente) REFERENCES Cliente(id)
);

CREATE TABLE Fornecedor (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    endereco VARCHAR(60) NOT NULL,
    numero varchar(10) NOT NULL,
    complemento varchar (60),
    cidade varchar (30),
    estado varchar (2),
    telefone VARCHAR (14),
    CONSTRAINT primary_keyfor PRIMARY KEY (codigo)
);

CREATE TABLE Categoria (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    CONSTRAINT primary_keycate PRIMARY KEY (codigo)
);

CREATE TABLE Imagem(
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(70) NOT NULL,
    legenda VARCHAR(70) NOT NULL,
    CONSTRAINT primary_keyimg PRIMARY KEY (codigo)
);

CREATE TABLE Produto (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(500) NOT NULL,
    descricao VARCHAR(2000) NOT NULL,
    codigofornecedor INTEGER NOT NULL,
    codigocategoria INTEGER NOT NULL,
    precocompra DECIMAL(8,2) NOT NULL,
    precovenda DECIMAL(8,2) NOT NULL,
    estoque INTEGER NOT NULL,
    codigoimagem INTEGER,
    datacadastro timestamp NOT NULL,
    CONSTRAINT primary_keypro PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyprofor FOREIGN KEY (codigofornecedor) REFERENCES Fornecedor(codigo),
    CONSTRAINT foreign_keyprocat FOREIGN KEY (codigocategoria) REFERENCES Categoria(codigo),
    CONSTRAINT foreign_keyproimg FOREIGN KEY (codigoimagem) REFERENCES Imagem(codigo)
);

CREATE TABLE Endereco_Entrega(
	codigo INTEGER NOT NULL AUTO_INCREMENT,
    endereco VARCHAR(50),
    complemento VARCHAR(50),
    numero varchar(10),
    bairro varchar(10),
    CONSTRAINT primary_keyven PRIMARY KEY (codigo)
);

CREATE TABLE Forma_Pagamento(
	codigo INTEGER NOT NULL AUTO_INCREMENT,
    cartaocredito BOOLEAN,
    paypal BOOLEAN,
    numerocartao VARCHAR(30),
    nomecartao VARCHAR(20),
    codigoseguranca VARCHAR(3),
    usuariopay VARCHAR(30),
    senhapay VARCHAR(30),
    CONSTRAINT primary_keyforma PRIMARY KEY (codigo)
);

CREATE TABLE Pedido(
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigocliente INTEGER NOT NULL,
    datavenda timestamp NOT NULL,
    valortotal DECIMAL(8,2) NOT NULL,
    codigopagamento INTEGER NOT NULL,
    codigoendereco INTEGER NOT NULL,
    codigovenda INTEGER,
    CONSTRAINT primary_keyven PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyvencli FOREIGN KEY (codigocliente) REFERENCES Cliente(id),
    CONSTRAINT foreign_keycodpag FOREIGN KEY (codigopagamento) REFERENCES Forma_Pagamento(codigo),
    CONSTRAINT foreign_keycodend FOREIGN KEY (codigoendereco) REFERENCES Endereco_Entrega(codigo)
);

CREATE TABLE Venda(
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigocliente INTEGER NOT NULL,
    datavenda timestamp NOT NULL,
    valortotal DECIMAL(8,2) NOT NULL,
    codigopagamento INTEGER NOT NULL,
    codigoendereco INTEGER NOT NULL,
    codigopedido INTEGER NOT NULL,
    CONSTRAINT primary_keyven PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyvencli FOREIGN KEY (codigocliente) REFERENCES Cliente(id),
    CONSTRAINT foreign_keycodend FOREIGN KEY (codigoendereco) REFERENCES Endereco_Entrega(codigo),
    CONSTRAINT foreign_keycodpag FOREIGN KEY (codigopagamento) REFERENCES Forma_Pagamento(codigo),
    CONSTRAINT foreign_keycodped FOREIGN KEY (codigopedido) REFERENCES Pedido(codigo)
);


CREATE TABLE ItemPedido(
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigopedido INTEGER NOT NULL,
    codigoproduto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    CONSTRAINT primary_keyitem PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyitemcod FOREIGN KEY (codigopedido) REFERENCES Pedido(codigo),
    CONSTRAINT foreign_keyitempro FOREIGN KEY (codigoproduto) REFERENCES Produto(codigo)
);

CREATE TABLE Itemvenda (
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigovenda INTEGER NOT NULL,
    codigoproduto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    CONSTRAINT primary_keyitem PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyitemcod FOREIGN KEY (codigovenda) REFERENCES Venda(codigo),
    CONSTRAINT foreign_keyitempro FOREIGN KEY (codigoproduto) REFERENCES Produto(codigo)
);

CREATE TABLE Itemcarrinho(
    codigo INTEGER NOT NULL AUTO_INCREMENT,
    codigocarrinho INTEGER NOT NULL,
    codigoproduto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    CONSTRAINT primary_keyitemcar PRIMARY KEY (codigo),
    CONSTRAINT foreign_keyitemcar FOREIGN KEY (codigocarrinho) REFERENCES Carrinho(codigo),
    CONSTRAINT foreign_keyitemcarpro FOREIGN KEY (codigoproduto) REFERENCES Produto(codigo)
);

INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('daddarío', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 1
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('fender', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 2
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('gibson', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 3
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('roland', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 4
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('martin e co', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 5
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('casio', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 6
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('blackStar', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 7
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('mark bass', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 8
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('ik multimedia', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 9
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('boss', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 10
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('korg', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 11
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('tagina', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 12
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('ibanez', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 13
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('sigma guitars', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 14
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('shure', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 15
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('eletro-harmonix', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 16
INSERT INTO FORNECEDOR (nome, endereco, numero, cidade, estado, telefone) VALUES ('on-stage', 'Avenida Luiz Carlos Berrini', '19199', 'São Paulo', 'SP', '(11)5555-8888'); -- 17


-- Categorias e Códigos
INSERT INTO CATEGORIA (nome) VALUES ('Áudio Profissional'); -- 1
INSERT INTO CATEGORIA (nome) VALUES ('Bateria & Percussão'); -- 2
INSERT INTO CATEGORIA (nome) VALUES ('Cordas & Acessórios'); -- 3
INSERT INTO CATEGORIA (nome) VALUES ('Pianos & Teclados'); -- 4
INSERT INTO CATEGORIA (nome) VALUES ('Sopro');

-- Categoria 1 Áudio Profissional
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Pedestal para Microfone Girafa Smfull Ibox', 
						'O smFULL tem menos peças e encaixes, deixando sua constituição mais robusta, 
                        forte e durável. O novo sistema de travamento é todo em metal, com a nova tecnologia
                        de acabamento HARD COAT® da IBOX com aparência única que protege o suporte de ferrugem e 
                        riscos durante o transporte. Outra inovação é o sistema de torque na extensão do tubo. 
                        Além de todas as inovações o suporte ainda conta com a base retrátil EASY MOUNTING.', 80.00, 110.00, 20, 1, 2, '2008-01-01 00:00:01');

INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Cachimbo para Microfone Dinâmico My100 On-stage Stands', 
						'CACHIMBO P/ MICROFONE DINAMICO MY100.', 10.00, 35.00, 20, 1, 2, '2008-01-01 00:00:01');
                        
-- Categoria 2 Bateria & Percussão
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Bateria Acústica Garage Rock Bumbo 22", caixa 14", Surdo 16" e toms 10" e 12" Nagano - Grey Sparkle', 
				      'A série GARAGE possui um som extremamente controlado e equalizado reforçando o ataque e o peso dos graves.
                      Os kits vem equipados com sistemas de suspensão tipo RIMS, peles duplas NAGANO DOUBLE PLY nos toms e surdos
                      e “Super coated” nas caixas. Os bumbos tem peles com anel abafador na batedeira e na resposta.garantindo assim,
                      um som totalmente controlado e profundo, sem a necessidade de abafadores.', 1500.00, 2599.00, 20, 2, 2, '2008-01-01 00:00:01');

INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Bateria Eletrônica TD30 K com Rack MDS 50K Roland', 
				      'APor mais de uma década, a revolucionária V-Drums da Roland tem liderado a industria
                      de bateria em qualidade de som e incrível dinâmica. Com a inclusão do SuperNATURAL-integrado
                      a TD-30K V-Pro-Series, a bateria eletrônica alcança um novo auge em desempenho.
                      Os sons SuperNATURAL com a modelagem de comportamento de um instrumento acústico,
                      juntamente com a avançada tecnologia de detecção de sensação, fornece um novo nível de expressividade,
                      qualidade de som e desempenho, resultando em um instrumento que reage e responde a todas as sutilezas,
                      nuances e dinâmicas das técnicas de um baterista. Para completar, a função de reprodução de músicas via USB
                      foi adicionada para expandir as possibilidades de performance. Para uso em gravações profissionais,
                      a nova TD-30K é verdadeiramente uma completa e natural experiência em tocar bateria.', 20000.00, 24109.00, 20, 2, 2, '2008-01-01 00:00:01');

-- Caregoria 3 Cordas & Acessórios
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Guitarra Solo II SGR By Schecter - Preto (Midnight Satin Black) (MSB)', 
				      'série de guitarras SGR foi projetada para todos os músicos que desejam obter o som de uma Schecter a um preço acessível.
                      Essa guitarra vai direto ao ponto e libera o timbre e a qualidade que todo guitarrista procura.', 1499.00, 1799.00, 20, 3, 2, '2008-01-01 00:00:01');
                      
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Guitarra Fender Standard Stratocaster® Fender - Branco (Artic White) (80)', 
				      'Esta excelente guitarra modelo Stratocaster ® oferece o lendário e clássico timbre da Fender , 
                      tão requisitado e solicitado pelos principais guitarristas da atualidade. 
						As características desta peça incluem três poderoso captadores single-coil, 
                        ponte vintage tremolo com bloco mais largo(proporcionando melhor sustain), 
                        tarraxas Fender®/Ping® Standard blindadas; escudo sanduiche; controles de Master volume/Master Tom; 
                        partes plásticas cosmeticamente envelhecidas; chave de 3 posições..', 4000.00, 5690.00, 20, 3, 2, '2008-01-01 00:00:01');


-- Categoria 4 Pianos & Teclados
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Piano Digital CDP135 Casio - Preto (BK)', 
				      'Esses pianos são equipados com um função de efeito que recria a acústica calorosa
                      e rica de uma sala de concerto. Enquanto toca, o desempenho será tão cheio de emoção quanto
                      se você estivesse dentro de uma sala de concerto.', 1500.00, 2299.00, 20, 4, 2, '2008-01-01 00:00:01');
                      
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Piano Digital LP380 Korg','A autêntica experiência de tocar um piano de concerto com design elegante e que adiciona estilo ao seu lar.
				       O LP-380 possui um som de piano brilhante em um design refinado. Seu gabinete abriga um amplificador de alto ganho 
                       e falantes que reproduzem precisamente o som de um piano acústico, enquanto o teclado RH3 permite performances expressivas.',
                       4000.00, 6849.00, 20, 4, 2, '2008-01-01 00:00:01');


-- Categoria 5 Sopro
INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Flauta Doce YRS23 Soprano Germânica Yamaha (Distribuição)', 
				      'A Yamaha entrega produtos seguros e confiáveis. 
                      Desde os processos de escolha da matéria-prima à fabricação,
                      prestamos muita atenção ao produzir flautas doces Yamaha com segurança e confiabilidade.', 10.00, 49.00, 20, 5, 2, '2008-01-01 00:00:01');

INSERT INTO PRODUTO (nome, descricao, precocompra, precovenda, estoque, codigocategoria, codigofornecedor, datacadastro)
            VALUES   ('Me-08215 Flauta Doce Barroca Contralto Yra314biii Yamaha (Distribuição)', 
				      'Yamaha: As mãos habilidosas de Torakusu Yamaha deram início à maior fábrica de instrumentos musicais do mundo.
                      Tudo começou em 1887, quando ele criou o primeiro órgão de bambu do Japão. O desenvolvimento da arte de criar
                      instrumentos musicais veio como uma consequência natural.', 200.00, 474.00, 20, 5, 2, '2008-01-01 00:00:01');


INSERT INTO PERFIL (perfil) 
			VALUES ('Gerente');
            
INSERT INTO PERFIL (perfil) 
			VALUES ('Operador');

INSERT INTO PERFIL (perfil) 
			VALUES ('Cliente');

INSERT INTO USUARIO (nome, login, senha, codigoperfil)
			VALUES ('Administrador', 'admin', 'admin', 2);
            
INSERT INTO USUARIO (nome, login, senha, codigoperfil)
			VALUES ('Gerente', 'gerente', 'gerente', 1);
            

SET @@global.time_zone = '+3:00';