@Test
Feature: Probar la búsqueda de usuario por id

#Background: funciona para pasos repetitivos por ejemplo para evitarse Given repetitivos
#Rule: funciona para especificar la regla a probar, pieza fundamental, razon del feature file
  Scenario Outline: Búsqueda de usuario por id
    Given El usuario administrador ya está logeado
    When busco un usuario por el id <search>
    Then el resultado del nombre deberia ser <result>
    Examples:
      | search   | result              |
      |3         |Manuel               |
      |4         |Margrith             |
      |1         |USUARIO NO ENCONTRADO|

