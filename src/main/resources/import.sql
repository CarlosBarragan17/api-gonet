INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('cdbo', '$2a$10$XHK0HdLOfJLk57TrsURaF.dCTTX430THLjqlE7Soz4QitLO4DiqOS', 1, 'Daniel', 'Barragan', 'barragancarlosdaniel@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$KV66M059G0udHPefY/xfTOBuOktUbeqw3i1DSZI89jRtmoTWYDs5e', 1, 'Carlos', 'Olan', 'adminl@gmail.com');

INSERT INTO `roles`(nombre) VALUES ('ROLE_USER');
INSERT INTO `roles`(nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1);
