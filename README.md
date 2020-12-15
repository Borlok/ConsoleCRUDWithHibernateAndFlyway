Консольное CRUD приложение с использованием
JDBC, Maven и Liquibase

Задача:
Необходимо реализовать консольное CRUD 
приложение, которое взаимодействует с БД
и позволяет выполнять все CRUD операции 
над сущностями:

Customer
Specialty
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Customer-> Set<Specialty> specialties+ Account account
Account -> AccountStatus

Требования:
1.Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)

2.Для миграции БД использовать https://www.liquibase.org/

3.Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).

4.Для импорта библиотек использовать Maven

При первом запуске программы, в mysql создается  ДБ practice
и таблицы в ней:

Customers
AccountStatus
SpecialtyList
Accounts
Specialties 

Технологии: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito