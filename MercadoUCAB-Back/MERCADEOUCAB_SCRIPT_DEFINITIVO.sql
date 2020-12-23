CREATE DATABASE MERCADEOUCAB;
use MERCADEOUCAB;

CREATE TABLE TIPO_USUARIO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100),
    estado ENUM('a','i') DEFAULT 'a'
);

CREATE TABLE USUARIO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    username VARCHAR(20) NOT NULL UNIQUE,
    clave VARCHAR(20) NOT NULL,
    id_tipo INT NOT NULL,
    CONSTRAINT fk_tipo_usuario FOREIGN KEY (id_tipo) REFERENCES TIPO_USUARIO(id) ON DELETE CASCADE
);

CREATE TABLE ESTADO_CIVIL (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL
);

CREATE TABLE MEDIO_CONEXION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL
);

CREATE TABLE GENERO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL
);

CREATE TABLE NIVEL_ACADEMICO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL
);

CREATE TABLE OCUPACION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL
);

CREATE TABLE NIVEL_SOCIOECONOMICO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE LUGAR (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(70) NOT NULL,
    tipo ENUM('pais', 'estado', 'municipio', 'parroquia'),
    id_recursiva INT,
    id_nivelsocioeco INT,
    CONSTRAINT fk_lugar FOREIGN KEY (id_recursiva) REFERENCES LUGAR(id) ON DELETE CASCADE,
    CONSTRAINT fk_nivelsocioeco FOREIGN KEY (id_nivelsocioeco) REFERENCES NIVEL_SOCIOECONOMICO(id) ON DELETE CASCADE,
    CONSTRAINT unique_lugar UNIQUE (id, id_recursiva)
);

CREATE TABLE CLIENTE (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    razon_social ENUM('j','p','ca','sa','srl','ong','asfl') NOT NULL,
    rif INT UNIQUE NOT NULL,
    id_lugar INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_lugar_cliente FOREIGN KEY (id_lugar) REFERENCES LUGAR(id) ON DELETE CASCADE,
    CONSTRAINT fk_cliente_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);
#j:juridico, p:personal, ca: compañía anónima, srl: sociedad de responsabilidad limitada, ong: organización no gubernamental
#asfl: asociación sin fines de lucro

CREATE TABLE ENCUESTADO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    primer_nombre VARCHAR(70) NOT NULL,
    segundo_nombre VARCHAR(70),
    primer_apellido VARCHAR(70) NOT NULL,
    segundo_apellido VARCHAR(70),
    fecha_nacimiento DATE NOT NULL,
    id_lugar INT NOT NULL,
    id_ocupacion INT NOT NULL,
    id_civil INT NOT NULL,
    id_conexion INT NOT NULL,
    id_genero INT NOT NULL,
    id_nivel_academico INT NOT NULL,
    id_nivel_socioeco INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_lugar_encuestado FOREIGN KEY (id_lugar) REFERENCES LUGAR(id) ON DELETE CASCADE,
    CONSTRAINT fk_ocupacion_encuestado FOREIGN KEY (id_ocupacion) REFERENCES OCUPACION(id) ON DELETE CASCADE,
    CONSTRAINT fk_civil_encuestado FOREIGN KEY (id_civil) REFERENCES ESTADO_CIVIL(id) ON DELETE CASCADE,
    CONSTRAINT fk_conexion_encuestado FOREIGN KEY (id_conexion) REFERENCES MEDIO_CONEXION(id) ON DELETE CASCADE,
    CONSTRAINT fk_genero_encuestado FOREIGN KEY (id_genero) REFERENCES GENERO(id) ON DELETE CASCADE,
    CONSTRAINT fk_nivelacademico_encuestado FOREIGN KEY (id_nivel_academico) REFERENCES NIVEL_ACADEMICO(id) ON DELETE CASCADE,
    CONSTRAINT fk_nivelsocioeco_encuestado FOREIGN KEY (id_nivel_socioeco) REFERENCES NIVEL_SOCIOECONOMICO(id) ON DELETE CASCADE,
    CONSTRAINT fk_encuestado_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIO(id) ON DELETE CASCADE
    
);

CREATE TABLE TELEFONO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    numero INT NOT NULL,
    id_cliente INT,
    id_encuestado INT,
    CONSTRAINT fk_tel_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id) ON DELETE CASCADE,
    CONSTRAINT fk_tel_encuaestado FOREIGN KEY (id_encuestado) REFERENCES ENCUESTADO(id) ON DELETE CASCADE
);

CREATE TABLE OPCION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    descripcion VARCHAR(100)
);

CREATE TABLE HIJO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    fecha_nacimiento DATE NOT NULL,
    id_genero INT NOT NULL,
    id_encuestado INT NOT NULL,
    CONSTRAINT fk_genero_hijo FOREIGN KEY(id_genero) REFERENCES GENERO(id) ON DELETE CASCADE,
    CONSTRAINT fk_encuestado_hijo FOREIGN KEY(id_encuestado) REFERENCES ENCUESTADO(id) ON DELETE CASCADE
);

CREATE TABLE TIPO_PREGUNTA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE CATEGORIA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE SUBCATEGORIA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(100) NOT NULL,
    id_categoria INT NOT NULL,
    CONSTRAINT fk_categoria_subcategoria FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id) ON DELETE CASCADE
);

CREATE TABLE PREGUNTA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    descripcion VARCHAR(100),
    id_tipo INT NOT NULL,
    id_subcategoria INT,
    CONSTRAINT fk_pregunta_subc FOREIGN KEY (id_subcategoria) REFERENCES SUBCATEGORIA(id) ON DELETE CASCADE,
    CONSTRAINT fk_tipo_pregunta FOREIGN KEY (id_tipo) REFERENCES TIPO_PREGUNTA(id) ON DELETE CASCADE
);

CREATE TABLE PREGUNTA_OPCION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    id_opcion INT NOT NULL,
    id_pregunta INT NOT NULL,
    CONSTRAINT fk_opcion FOREIGN KEY (id_opcion) REFERENCES OPCION(id) ON DELETE CASCADE,
    CONSTRAINT fk_pregunta FOREIGN KEY (id_pregunta) REFERENCES PREGUNTA(id) ON DELETE CASCADE,
    CONSTRAINT unique_preg_opcion UNIQUE (id_opcion, id_pregunta)
);

CREATE TABLE RESPUESTA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    texto VARCHAR(300),
    id_pregunta_opcion INT NOT NULL,
    id_encuestado_pregunta INT NOT NULL,
    CONSTRAINT fk_pregunta_opcion FOREIGN KEY (id_pregunta_opcion) REFERENCES PREGUNTA_OPCION(id) ON DELETE CASCADE,
    CONSTRAINT fk_encuestado_pregunta FOREIGN KEY (id_encuestado_pregunta) REFERENCES ENCUESTADO(id) ON DELETE CASCADE,
    CONSTRAINT unique_respuesta UNIQUE (id_pregunta_opcion, id_encuestado_pregunta)
);


CREATE TABLE MARCA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE PRESENTACION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a', 'i') DEFAULT 'a',
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE TIPO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    nombre VARCHAR(50)  NOT NULL,
    descripcion VARCHAR(100)
);

CREATE TABLE SUBCATEGORIA_MARCA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    id_subcategoria INT NOT NULL,
    id_marca INT NOT NULL,
    CONSTRAINT fk_subcategoria_marca FOREIGN KEY (id_subcategoria) REFERENCES SUBCATEGORIA(id),
    CONSTRAINT fk_marca FOREIGN KEY (id_marca) REFERENCES MARCA(id),
    CONSTRAINT unique_sub_marca UNIQUE (id_subcategoria, id_marca)
);

CREATE TABLE MARCA_TIPO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_marca INT NOT NULL,
    id_tipo INT NOT NULL,
    CONSTRAINT fk_marca_tipo FOREIGN KEY (id_marca) REFERENCES MARCA(id),
    CONSTRAINT fk_tipo FOREIGN KEY (id_tipo) REFERENCES TIPO(id),
    CONSTRAINT unique_marca_tipo UNIQUE (id_marca, id_tipo)
);

CREATE TABLE TIPO_PRESENTACION (
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo INT NOT NULL,
    id_presentacion INT NOT NULL,
    CONSTRAINT fk_tipo_presentacion FOREIGN KEY (id_tipo) REFERENCES TIPO(id),
    CONSTRAINT fk_presentacion FOREIGN KEY (id_presentacion) REFERENCES PRESENTACION(id),
    CONSTRAINT unique_tipo_presentacion UNIQUE (id_tipo, id_presentacion)
);

CREATE TABLE ESTUDIO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('solicitado','procesado','en ejecucion', 'culminado') DEFAULT 'solicitado',
    nombre VARCHAR(150) NOT NULL,
    comentario_analista VARCHAR(300) NOT NULL,
    edad_minima INT,
    edad_maxima INT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    id_subcat INT NOT NULL,
    id_nivelsocioeco INT NOT NULL,
    id_lugar INT NOT NULL,
    id_usuario_analista INT,
    CONSTRAINT fk_subcat_estudio FOREIGN KEY(id_subcat) REFERENCES SUBCATEGORIA(id) ON DELETE CASCADE,
    CONSTRAINT fk_nivelsocioeco_estudio FOREIGN KEY(id_nivelsocioeco) REFERENCES NIVEL_SOCIOECONOMICO(id) ON DELETE CASCADE,
    CONSTRAINT fk_estudio_analista FOREIGN KEY(id_usuario_analista) REFERENCES USUARIO(id) ON DELETE CASCADE,
    CONSTRAINT fk_lugar_estudio FOREIGN KEY(id_lugar) REFERENCES LUGAR(id) ON DELETE CASCADE
);

CREATE TABLE ESTUDIO_ENCUESTADO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    fecha_realizacion DATE NOT NULL,
    id_encuestado INT NOT NULL,
    id_estudio INT NOT NULL,
    CONSTRAINT fk_encuestado FOREIGN KEY(id_encuestado) REFERENCES ENCUESTADO(id) ON DELETE CASCADE,
    CONSTRAINT fk_estudio FOREIGN KEY(id_estudio) REFERENCES ESTUDIO(id) ON DELETE CASCADE
);

CREATE TABLE ENCUESTA (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    id_pregunta INT NOT NULL,
    id_estudio INT NOT NULL,
    CONSTRAINT fk_encuesta_pregunta FOREIGN KEY (id_pregunta) REFERENCES PREGUNTA(id) ON DELETE CASCADE,
    CONSTRAINT fk_encuesta_estudio FOREIGN KEY (id_estudio) REFERENCES ESTUDIO(id) ON DELETE CASCADE,
    CONSTRAINT unique_encuesta UNIQUE(id_pregunta, id_estudio)
);

CREATE TABLE CLIENTE_ESTUDIO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    comentario_cliente VARCHAR(300),
    id_cliente INT NOT NULL,
    id_estudio INT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id) ON DELETE CASCADE,
    CONSTRAINT fk_estudio_cliente FOREIGN KEY (id_estudio) REFERENCES ESTUDIO(id) ON DELETE CASCADE,
    CONSTRAINT unique_cliente_estudio UNIQUE (id_cliente, id_estudio)
);
    
CREATE TABLE ESTUDIO_GENERO (
	id INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('a','i') DEFAULT 'a',
    id_genero INT NOT NULL,
    id_estudio INT NOT NULL,
    CONSTRAINT fk_estudio_genero FOREIGN KEY (id_estudio) REFERENCES ESTUDIO(id) ON DELETE CASCADE,
    CONSTRAINT fk_genero FOREIGN KEY (id_genero) REFERENCES GENERO(id) ON DELETE CASCADE
);
