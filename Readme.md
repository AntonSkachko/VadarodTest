# Currency Rate Microservice
Этот микросервис предоставляет информацию о курсах валют с сайта Национального банка Республики Беларусь (НБ РБ). И ещё тестовое задание в компанию Vadarod.

### Технологии
1. Spring Boot 
2. Spring MVC
3. Spring Data JPA
4. Hibernate
5. H2 Database
6. Maven
7. OpenFeign

### Запуск
1. Склонируйте репозиторий:
   ```https://github.com/AntonSkachko/VadarodTest```
2. Перейдите в папку проекта
3. Запустите микросервис:
    ```mvn spring-boot:run```

### API
Api можно посмотреть через swagger ```http://localhost:8080/swagger-ui/index.html```, ну или поверить на слово, что тут написано
1.  Получение курсов валют за выбранную дату
    * Endpoint ```GET /api/v1/rates?date=YYYY-MM-DD```
    * Параметры `date` (обязательный) - дата в формате `YYYY-MM-DD`
    * Ответ:
      * Если данные успешно загружены: `HTTP 200 OK`
      * Если неправильная дата, то `HTTP 404 Bad request`
2. Получение информации о курсе валюты за указанный день
    * Endpoint `GET /api/v1/rates/{currencyCode}?date=YYYY-MM-DD`
    * Параметры:
      * `currencyCode` (обязательный) - код валюты (например, USD, EUR).
      * `date` (обязательный) - дата в формате YYYY-MM-DD. 
    * Ответ:
      * Если данные найдены: `HTTP 200 OK` с информацией о курсе валюты.
      * Если данные не найдены: `HTTP 404 Not Found`.

### История взаимодействия Git
Про это я увидел в самом конце, так что не судите строго.
