[![Build Status](https://travis-ci.org/zoom59rus/HibernateCRUDApplication.svg?branch=master)](https://travis-ci.org/zoom59rus/HibernateCRUDApplication)
# Module_2_3
# Реализовать консольное CRUD приложение

## Постанока задачи
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:   
1.  Writer(id, firstName, lastName, List<Post> posts, Region region)  
2.  Post (id, content, created, updated)  
3.  Region (id, name)  
4.  Role (enum ADMIN, MODERATOR, USER)  

В качестве хранилища данных необходимо использовать реляционную базу данных.

## Требования  
1.  Придерживаться шаблона MVC (пакеты model, repository, service, controller, view);  
2.  Инициализация БД должна быть реализована с помощью Flyway;  
3.  Для взаимодействия с БД - Hibernate, конфигурирование через аннотации;
4.  Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito);  
4.  Для импорта библиотек использовать Maven;  
5.  В качестве хранилища данных необходимо использовать реляционную базу данных.  

Технологии: Java, PostgeSQL, Hibernate, Flyway, Maven, JUnit, Mockito.  

## Результат 
Результатом выполнения задания должен быть репозиторий на github, с использованием Travis (https://travis-ci.org/) и отображением статуса сборки проекта.  

## Инструкция по запуску
mvn package  
mvn exec:java