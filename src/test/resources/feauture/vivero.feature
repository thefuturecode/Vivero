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

    Scenario: El sistema no puede editar un funcionario porque lo busca y no existe
    Given Hay un Funcionario con id 2525,nombre "carolina", apellido "pereira", run "19597349-1", telefono "484645498", cargo "vendedor", email "carolina@gmail.com", estado "activo"
    When se desea editar al funcionario
    Then se obtiene el status "not_found"
    
    

