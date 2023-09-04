Feature: Probar la búsqueda en google

#Background: funciona para pasos repetitivos por ejemplo para evitarse Given repetitivos
#Rule: funciona para especificar la regla a probar, pieza fundamental, razon del feature file

  Scenario Outline: Busco algo en google
    Given I am on the Google search page
    When I search <search>
    And click on the search button
    Then the results match the criteria <result>
    Examples:
      | search        | result |
      |Perritos       |Perritos|
      |Gatitos        |Gatitos |
      |Ktronix Ofertas|Ofertas |

