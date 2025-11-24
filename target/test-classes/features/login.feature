Feature: Login del ejecutivo

  Scenario: Login correcto usando rut desde la base de datos
    Given abro la pagina de login
    When ingreso un rut valido desde la base de datos
    Then el login debe ser exitoso

  Scenario: Login incorrecto con rut inválido
    Given abro la pagina de login
    When ingreso un rut invalido
    Then el sistema debe mostrar un mensaje de error
