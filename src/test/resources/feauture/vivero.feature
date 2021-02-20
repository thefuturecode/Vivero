#Author: your.email@your.domain.com

Feature: Servicios asociados a un vivero
Scenario: Agregar un nuevo vale
Given exixte un vale; idFuncionario 1, fecha "2020-02-20", codigoProducto 1, total 4000
When deseo agregar el vale
Then se obtiene el estado "created"



