# RestAssured_Api Testing Project

## Описание
Проект создан для автоматизации тестирования API с использованием **RestAssured** и **TestNG**.  
Тесты покрывают основные HTTP методы (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) и проверяют корректность ответов, коды состояния, и валидацию данных.

## Технологии и инструменты
- Java
- RestAssured
- TestNG
- Maven
- Allure Reports
- Git

## Покрытие тестами
- [x] GET — проверка получения данных
- [x] POST — создание новой записи
- [x] PUT / PATCH — обновление данных
- [x] DELETE — удаление ресурса

## Как запустить проект
1. Клон репозиторий:
   ```bash
   git clone https://github.com/BermetB16/RestAssured_Api.git

2. Открыть в IDE (например IntelliJ IDEA).
Запуск тесты через TestNG.

3.Чтобы получить отчет Allure:
mvn clean test
mvn allure:serve
