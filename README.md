# Проект по автоматизации API тестов сайта Reqres
<a target="_blank" href="https://reqres.in/">Веб сайт Reqres</a>

<p align="center">
<img title="Allure Graphics" src="images/screens/Reqres.png">
</p>

## :drop_of_blood: Содержание:

- [Технологии и инструменты](#earth_africa-технологии-и-инструменты)
- [Реализованные проверки](#earth_africa-Реализованные-проверки)
- [Сборка в Jenkins](#earth_africa-Jenkins-job)
- [Запуск из терминала](#earth_africa-Запуск-тестов-из-терминала)
- [Allure отчет](#earth_africa-Allure-отчет)
- [Отчет в Telegram](#earth_africa-Уведомление-в-Telegram-при-помощи-бота)

## :bird: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## :boom: Реализованные проверки

- ✓ Поиск информации по id пользователя
- ✓ Вывести список пользователей
- ✓ Вывести список ресурсов
- ✓ Создание пользователя
- ✓ Регистрация пользователя
- ✓ Обновление информации пользователя
- ✓ Поиск несуществующего пользователя
- ✓ Успешная авторизация пользователя

## :heavy_heart_exclamation: Запуск тестов
### Локальный запуск (через терминал):
```
gradle clean test
```
### </a> Удаленный запуск <a target="_blank" href="https://jenkins.autotests.cloud/job/10_DikayaAV_DiplomaAPI/"> (через Jenkins) </a>. Нажать на кнопку Собрать : 
<p align="center">
<a href="https://jenkins.autotests.cloud/job/10_DikayaAV_diploma/"><img src="images/screens/Jenkins1.png" alt="Jenkins"/></a>
</p>

## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/10_DikayaAV_DiplomaAPI/allure/">Allure report</a>

### :lady_beetle: Дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/Allure.png">
</p>

### :cherries: Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/Allure2.png">
</p>

### :cut_of_meat: Графики

<p align="center">
<img title="Allure Graphics" src="images/screens/Allure3.png">
</p>

## <img src="images/logo/Telegram.svg" width="25" height="25"  alt="Allure"/></a> Уведомление в Telegram при помощи бота

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/Telegram.png" >
</p>


