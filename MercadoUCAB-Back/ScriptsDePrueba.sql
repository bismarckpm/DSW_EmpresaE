INSERT INTO tipo_usuario (descripcion, estado)
	values
		('cliente', 'a'),
        ('administrador', 'a'),
        ('encuestado', 'a'),
        ('analista', 'a');

INSERT INTO USUARIO (username, clave, id_tipo)
	values
		('juan', '12345', 1),
        ('pedro', '12345', 2),
        ('pancho', '12345', 3),
        ('jose', '12345', 4);
        
INSERT INTO estado_civil (nombre)
	values
		('soltero'),
        ('casado'),
        ('divorciado'),
        ('viudo');
        
INSERT INTO medio_conexion (nombre)
	values
		('movil'),
        ('tablet'),
        ('computadora'),
        ('ninguno');
        
INSERT INTO genero (nombre)
	values
		('femenino'),
        ('masculino'),
        ('otro');
        
INSERT INTO nivel_academico (nombre)
	values
		('primaria'),
        ('secundaria'),
        ('superior');
        
INSERT INTO ocupacion (nombre)
	values
		('abogado'),
        ('ingeniero'),
        ('trabajador social'),
        ('peluquero'),
        ('ama de cada');
        
INSERT INTO nivel_socioeconomico (nombre, descripcion)
	values
		('muy pobre','sin recursos suficientes'),
        ('pobre','suficiente para comer'),
        ('clase media','vive bien'),
        ('clase alta','recursos de sobra');
        
INSERT INTO lugar (nombre, tipo)
	values
		('venezuela','pais');
        
INSERT INTO lugar (nombre, tipo, id_recursiva)
	values
		('merida','estado',1),
        ('tachira','estado',1);
        
INSERT INTO lugar (nombre, tipo, id_recursiva)
	values
		('alberto adriani','municipio',2),
        ('andrés bello','municipio',2),
        ('san cristobal','municipio',3),
        ('ayacucho','municipio',3);
        
INSERT INTO lugar (nombre, tipo, id_recursiva)
	values
		('presidente betancourt','parroquia',4),
        ('presidente páez','parroquia',4),
        ('parroquia prueba merida','parroquia',5),
        ('parriquia prueba merida2','parroquia',5),
        ('parroquia prueba tachia 1','parroquia',6),
        ('parroquia prueba tachia 2','parroquia',6),
        ('parroquia prueba tachia 3','parroquia',7),
        ('parroquia prueba tachia 4','parroquia',7);
        
INSERT INTO cliente (razon_social, rif, id_lugar, id_usuario)
	values
		('sa', 24587, 15, 1);
        
INSERT INTO encuestado (primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,fecha_nacimiento,id_lugar,id_ocupacion,id_civil,
							id_conexion,id_genero,id_nivel_academico,id_nivel_socioeco,id_usuario)
		values
			('jose', 'antonio', 'prieto', 'quintero', '1994-10-25', 13,1,1,1,1,1,1,2);
            
INSERT INTO telefono (numero, id_cliente, id_encuestado)
	values
		(0425876644, 1,null),
        (0425847585, null, 1);
        
INSERT INTO OPCION (descripcion)
	values
		('migrar'),
        ('abandonar'),
        ('elegir'),
        ('opcion3'),
        ('pregunta'),
        ('forma');
        
INSERT INTO HIJO (fecha_nacimiento,id_genero,id_encuestado)
	values
		('1995-06-07', 1, 1);
        
INSERT INTO TIPO_PREGUNTA (tipo)
	values
		('Abiertas'),
        ('Verdadero y Falso'),
        ('Selección Simple'),
        ('Selección múltiple'),
        ('Por rango');
        
INSERT INTO categoria (nombre)
	VALUES
		('cuidado personal'),
        ('higiene');
        
INSERT INTO subcategoria (nombre, id_categoria)
	values
		('afeitadora', 1),
        ('crema', 1),
        ('jabon', 2),
        ('shampoo', 2);

INSERT INTO pregunta (descripcion,id_tipo,id_subcategoria)
	values
		('quien lava los platos en el dia?',1,1),
        ('Juan se pone crema?',2,2),
        ('juan se bana?',3,3),
        ('se lava el cabello?',4,4);
        
INSERT INTO pregunta_opcion (id_opcion,id_pregunta)
	values
		(1,1),
        (2,2),
        (3,3),
        (4,4);
        
INSERT INTO MARCA (nombre)
	values
		('samsung'),
        ('palmolive'),
        ('dove'),
        ('frecuencia');
        
INSERT INTO presentacion (descripcion)
	values
		('telefono'),
        ('lavadora'),
        ('barra'),
        ('liquido'),
        ('suave'),
        ('duro');

INSERT INTO tipo (nombre)
	values
		('feo'),
        ('bonito'),
        ('grande'),
        ('pequeño');
        
INSERT INTO subcategoria_marca(id_subcategoria,id_marca)
	values
		(1,1),
        (1,2),
        (2,3),
        (4,2),
        (3,3);
        
INSERT INTO marca_tipo(id_marca,id_tipo)
 values
	(1,2),
    (1,3),
    (2,2),
    (4,1);
    
INSERT INTO tipo_presentacion(id_tipo, id_presentacion)
	values
		(1,2),
		(1,3),
		(2,2),
		(4,1);
        
INSERT INTO ESTUDIO (nombre,comentario_analista,edad_minima,edad_maxima,fecha_inicio,fecha_fin,id_subcat,id_nivelsocioeco,id_lugar,id_usuario_analista)
	VALUES
		('estudio1', 'el comentacrio numero 1', 15,20,'2020-02-02',null,1,1,1,1),
        ('estudio2', 'el comentacrio numero 2', 10,14,'2020-02-02',null,1,1,1,1),
        ('estudio3', 'el comentacrio numero 3', 0,100,'2020-02-02','2020-02-03',1,1,1,1),
        ('estudio4', 'el comentacrio numero 4', null,null,'2020-02-02',null,1,1,1,1);
        
INSERT INTO estudio_encuestado(fecha_realizacion,id_encuestado,id_estudio)
	VALUES
		('2020-05-05',1,1);
        
INSERT INTO ENCUESTA(fecha_inicio,fecha_fin,id_pregunta,id_estudio)
	VALUES
		('2020-05-05',null,1,1),
        ('2020-05-05',NULL,2,1),
        ('2020-05-05',null,3,1),
        ('2020-05-05',null,4,1),
        ('2020-05-05',null,1,2),
        ('2020-05-05',NULL,2,2),
        ('2020-05-05',null,3,2),
        ('2020-05-05',null,4,2),
        ('2020-05-05',null,1,3),
        ('2020-05-05',NULL,2,3),
        ('2020-05-05',null,3,3),
        ('2020-05-05',null,4,3),
        ('2020-05-05','2020-06-06',1,4),
        ('2020-05-05','2020-06-06',2,4),
        ('2020-05-05','2020-06-06',3,4),
        ('2020-05-05','2020-06-06',4,4);
        
INSERT INTO CLIENTE_ESTUDIO(comentario_cliente,id_cliente,id_estudio)
	VALUES
		('comentario de estudio 1', 1,1),
        ('comantacio de estudio 2',1,2),
        ('comentario de estudio 3',1,3),
        (null,1,4);
        
INSERT INTO ESTUDIO_GENERO(id_genero,id_estudio)
	VALUES
		(1,1),
        (2,1),
        (3,1),
        (1,1),
        (2,3),
        (3,3),
        (1,3),
        (2,3),
        (3,4),
        (1,4),
        (2,4),
        (3,4);
        