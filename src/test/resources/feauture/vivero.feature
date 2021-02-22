#Author: your.email@your.domain.com

Feature: Servicios asociados a un vivero

    Scenario: Agregar un nuevo vale
     Given existe un vale; idFuncionario 1, fecha "2020-02-20", codigoProducto 1, total 4000
     When deseo agregar el vale
     Then se obtiene el estado "created"

    Scenario: Ingresar un nuevo proveedor
     Given se tiene un nuevo proveedor; nombre "Berta Ortiz", rut "12632851-9", telefono "952361478", email "berta@mail.com", direccion "Tomas Delphin 878"
     When se desea agregar a este proveedor a los registros del vivero
     Then se obtiene el estado "created" y el proveedor creado tiene como nombre "Berta Ortiz", rut "12632851-9" y direccion "Tomas Delphin 878"

	Scenario: El sistema no puede editar un proveedor porque lo busca y no existe
    Given Hay un proveedor con id 5014, rut "18595862-1", direccion "Las Castañas 78", telefono "415268745", email "diego@gmail.com", nombre "Diego", estado "activo"
    When se desea editar al proveedor
    Then se obtiene el status "not_found"

