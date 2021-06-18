
## Курсовая работа 
[презентация](https://docs.google.com/presentation/d/1ym0l3kpkjDe1biUw7uKraVv5ymegVsb4cxY5ATSdDhc/edit?usp=sharing)

### Задание
Разработать модель данных, REST сервисы и web сайт для работы со «Справочником контрагентов»

Запуск web страницы: http://localhost:8080/counterparties

REST API:

| Запрос  |  Действие |url http://localhost:8080                
| ------- |:---|:---
| PUT     | обновляем | [/api/counterparties](http://localhost:8080/api/counterparties)    
| POST    | изменяем | [/api/counterparties](http://localhost:8080/api/counterparties) 
| DELETE  | удаляем по id | [/api/counterparties/delete_id?id=5](http://localhost:8080//api/counterparties/delete_id?id=5)
| DELETE  | удаляем по name | [/api/counterparties/delete_name?name=ВТБ](http://localhost:8080//api/counterparties/delete_name?name=ВТБ)
| GET     | ищем по id | [/api/counterparties/get_id?id=5](http://localhost:8080//api/counterparties/get_id?id=5)
| GET  | ищем по name | [/api/counterparties/get_name?name=ВТБ](http://localhost:8080//api/counterparties/get_name?name=ВТБ)
| GET  | ищем все | [/api/counterparties/get_all](http://localhost:8080//api/counterparties/get_all)
| GET  | ищем по № и bic | [/api/counterparties/get_anb?accountNumber=30101810100000000835&bic=042007835](http://localhost:8080//api/counterparties/get_anb?accountNumber=30101810100000000835&bic=042007835)


### Справочная документация (стащил от сюда [Spring.io](https://start.spring.io/))
Используемый стек:

* [Gradle](https://docs.gradle.org)
* [Lombok](https://springframework.guru/spring-boot-with-lombok-part-1/) 
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/gradle-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Boot SLF4j](https://www.baeldung.com/spring-boot-logging)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Hibernate](https://www.baeldung.com/spring-boot-hibernate)
* [PostgreSQL](https://www.postgresql.org/)
* [Validation](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-validation)
* [Jasypt](https://www.baeldung.com/spring-boot-jasypt)

Плагины:
* [SonarLint](https://habr.com/ru/company/krista/blog/469963/) - анализатор кода от разработчиков SonarQube
* [Diffblue Cover](https://nuancesprog.ru/p/11011/) - создание юнит-тестов, с помощью ИИ

### Дополнительные ссылки

* [jasypt](https://www.devglan.com/online-tools/jasypt-online-encryption-decryption) - страница шифрования
* [Spring Mock-MVC](https://habr.com/ru/post/527330/) - для тестирования Spring Boot REST API без запуска сервера