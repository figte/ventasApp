INSERT INTO sucursales(nombre, telefono, email ,direccion, detalle, create_at, update_at) values('FERRETERIA & PAPELERIA','503 5555 4444','jaime@uso.org','colonia x','detalle','2019-08-14', '2019-08-14');
#INSERT INTO sucursales(nombre, telefono, email ,direccion, detalle, create_at, update_at) values('Sucursal 2','503 5555 4444','jaime@uso.org','colonia x','detalle','2019-08-14', '2019-08-14');
INSERT INTO roles(nombre, detalle, create_at, update_at) values('ADMINISTRADOR', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO roles(nombre, detalle, create_at, update_at) values('VENTAS', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO roles(nombre, detalle, create_at, update_at) values('SUPERVISOR', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO roles(nombre, detalle, create_at, update_at) values('GERENTE', 'detalle','2019-08-14', '2019-08-14');

INSERT INTO puestos(nombre, detalle, create_at, update_at) values('PUESTO1', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO puestos(nombre, detalle, create_at, update_at) values('PUESTO2', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO puestos(nombre, detalle, create_at, update_at) values('PUESTO3', 'detalle','2019-08-14', '2019-08-14');


INSERT INTO estados(nombre, detalle, create_at, update_at) values('ACTIVO', 'detalle','2019-08-14', '2019-08-14');
INSERT INTO estados(nombre, detalle, create_at, update_at) values('INACTIVO', 'detalle','2019-08-14', '2019-08-14');


INSERT INTO EMPLEADOS(nombre, apellido, cargo ,contacto, direccion,profesion, create_at, update_at,sucursal_id,edad) values('CARLOS','RAMIREZ','PUESTO1','7777-7777','col. cucumacayan','Ingeniero','1999-08-14', '1999-08-14',1,'1999-08-14');
INSERT INTO EMPLEADOS(nombre, apellido, cargo ,contacto, direccion,profesion, create_at, update_at,sucursal_id,edad) values('JAIME','RAMIREZ','PUESTO2','7777-7777','col. cucumacayan','Ingeniero','1999-08-14', '1999-08-14',1,'1999-08-14');

INSERT INTO perfiles_empleados(nombre, password,empleado_id, estado_id, create_at, update_at) values('admin','$2a$10$oiBMpzJuwxmTXObpYp.Dv.q2.AgKvDWFW0a0EKp.GEOO0KcjMYRa2',1,1,'2019-08-14', '2019-08-14');
INSERT INTO perfiles_empleados(nombre, password,empleado_id, estado_id, create_at, update_at) values('user','$2a$10$oiBMpzJuwxmTXObpYp.Dv.q2.AgKvDWFW0a0EKp.GEOO0KcjMYRa2',2,1,'2019-08-14', '2019-08-14');

INSERT INTO roles_perfiles(perfil_empleado_id, rol_id,create_at, update_at) values(2,2,'2019-08-14', '2019-08-14');
INSERT INTO roles_perfiles(perfil_empleado_id, rol_id,create_at, update_at) values(1,1,'2019-08-14', '2019-08-14');

INSERT INTO roles_perfiles(perfil_empleado_id, rol_id,create_at, update_at) values(2,2,'2019-08-14', '2019-08-14');

INSERT INTO categorias(detalle,estado, nombre) values('detalle', 1, 'categoria1');
INSERT INTO proveedores(contacto,detalle, estado, nombre) values('777777','detalle',1,'disa sa de sv');  
INSERT INTO productos(codigo,existencias,nombre,precio_venta,stock,unidad_medida,categoria_id,proveedor_id,estado) values('T01',0,'tornillo galvanizado',0.35,'full','ninguna',1,1,1); 
INSERT INTO entradas(cantidad,create_at,detalle,tipo_entrada,producto_codigo,porcentaje,precio_compra) values(5,'8-11-2019','detalle','test','T01', 10,5.5);  



CREATE OR REPLACE FUNCTION incrementar_stock() 
RETURNS TRIGGER AS
$$
BEGIN
      UPDATE productos SET existencias =
      (new.cantidad + (SELECT existencias FROM productos WHERE codigo = new.producto_codigo)) 
      WHERE codigo = new.producto_codigo;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER insert_increment_stock
  BEFORE INSERT
  ON entradas
  FOR EACH ROW
  EXECUTE PROCEDURE incrementar_stock(); 

 