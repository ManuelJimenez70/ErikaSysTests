Feature: Probar las posibles transacciones en el sistema

  @RecordAction
  Scenario Outline: Registrar una transacción
    Given El usuario empleado ya está logeado
    When Se registra una accion con los atributos id_usuario: <id_usuario>, id_action: <id_action>, id_product: <id_product>, quantity: <quantity>, state: <state>
    Then el resultado de deberia ser <result> al registrar accion
    Examples:
      | id_usuario|id_action|id_product|quantity|state  | result|
      |68         |5        |5         |13      |pruebas|SUCCESS|
      |68         |6        |5         |64      |pruebas|  ERROR|
      |8974585    |4        |5         |4       |pruebas|  ERROR|
      |68         |46       |5         |2       |pruebas|  ERROR|
      |68         |6        |5000000   |2       |pruebas|  ERROR|
      |68         |6        |5         |50000000|pruebas|  ERROR|


