Консольное CRUD приложение с использованием
Hibernate, Maven, Flyway

Задача:
Необходимо реализовать консольное CRUD 
приложение, которое взаимодействует с БД
при помощи Hibernate
и позволяет выполнять все CRUD операции 
над сущностями:

Customer
Specialty
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Customer-> Set<Specialty> specialties+ Account account
Account -> AccountStatus

Требования:
Все CRUD операции для каждой из сущностей

1.Придерживаться подхода MVC

2.Для сборки проекта использовать Maven

3.Для взаимодействия с БД - Hibernate

4.Для конфигурирования Hibernate - аннотации

5.Инициализация БД должна быть реализована с помощью flyway

6.Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito)

Результатом выполнения задания должен быть репозиторий на github,
с использованием Travis (https://travis-ci.org/) и отображением статуса сборки проекта.

В MySql должны присутствовать:
1. Пользователь student с паролем 123.
2. ДБ practice

При компиляции программы, в mysql создаются таблицы:

customers
specialty_list
accounts
specialties 

Технологии: Java, MySQL, Maven, Flyway, JUnit, Mockito, Hibernate