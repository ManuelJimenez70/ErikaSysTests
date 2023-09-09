Feature: Probar CRUD de Usuarios

#Background: funciona para pasos repetitivos por ejemplo para evitarse Given repetitivos
#Rule: funciona para especificar la regla a probar, pieza fundamental, razon del feature file
  @GetUserById
  Scenario Outline: Búsqueda de usuario por id
    Given El usuario administrador ya está logeado
    When busco un usuario por el id <search>
    Then el resultado del nombre deberia ser <result>
    Examples:
      | search   | result              |
      |3         |Manuel               |
      |4         |Margrith             |
      |999999    |USUARIO NO ENCONTRADO|
      |-1        |ERROR AL OBTENER USUARIO|

  @CreateUser
  Scenario Outline: Creacion de usuario
    Given El usuario administrador ya está logeado
    When Creo un usuario con los atributos: <email>,  <name>,  <lastName>,  <typeDocument>,  <document_number>,  <direction>, <password>, <roles>, <state>
    Then el resultado de la accion deberia ser <result> al crear usuario
    Examples:
      | email   | name|lastName|typeDocument|document_number|direction|password|roles|state|result|
      |<random_email>|prueba|prueba|CC       |123123123      |Prueba   |prueba  |1    |pruebas|  SUCCESS  |
      |pruebaprueba|prueba|prueba|CC       |123123123      |Prueba   |prueba  |1    |pruebas|   ERROR    |
      |<random_email>|prueba|prueba|CCC       |123123123      |Prueba   |prueba  |1    |pruebas|  ERROR   |
      |<random_email>|!!*#$prueba|prueba|TI       |1231231231231231312312313123123123123123234242343243242343242432442342342342343243242342343242343243242344234|Prueba   |prueba  |1    |pruebas|ERROR|
      |<random_email>|<null>|prueba|CCCC       |123123123      |Prueba   |prueba  |1    |pruebas|ERROR |
      |<random_email>|prueba|prueba|TI       |123123123      |Prueba   |prueba  |15    |pruebas|ERROR|
      |<random_email>|prueba|prueba|CC       |123123123      |Prueba   |prueba  |12    |pruebas|  ERROR  |

  @Login
  Scenario Outline: login
    When ingreso las credenciales email: <email>, contraseña: <password>
    Then el resultado de la accion deberia ser <result> al logearse
    Examples:
      | email   | password|result|
      |prueba@prueba|prueba|SUCCESS|
      |prueba@prueba|pruebas|  ERROR |
      |prueba@prueba|pasdsadsadasdadasdasasdasdasdasdsadadsadsadsadasdasdasdasdasdasdasdasdaddasda13123123asdasdasdasdasdasdrueba|  ERROR |










