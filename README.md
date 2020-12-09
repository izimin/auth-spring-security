# Модуль авторизации.

## API сервиса:
- ### Документация 
GET-запрос по адресу <http://{domain}/api/user/login/> будет выдавать (или уже выдаёт) эту документацию.

- ### Вход
POST-запрос по адресу <http://{domain}/api/user/login/api/user/login> производит вход в систему.
Данные для входа (тело запроса) - объект JSON с параметрами "login" и "password".
```
Пример
  POST http://{domain}/api/user/login
  {
    "login": "user", 
    "password": "password"
  }
```
Если вход успешен, возвращается информация о пользователе (с HTTP-кодом 200). Иначе возвращается сообщение о неудачном входе (с HTTP-кодом 401).
Также к ответу подцепляется кука сессии JSESSIONID (сессионный ключ).


- ### Получение иеформации
GET-запрос по адресу <http://{domain}/api/user/login/api/user/info> выдаёт данные о залогиненном пользователе.
Если вход ещё не был произведён, доступ к пути блокируется.
```
Пример
  GET http://{domain}/api/user/login/api/user/info
```

- ### Выход
POST-запрос <http://{domain}/api/user/login/api/user/logout> производит выход из системы.
После выхода кука сессии изымается из оборота (удяляется).
Разумеется, доступен только вошедшим пользователям.
```
Пример
  POST http://{domain}/api/user/login/api/user/logout
```

- ### Регистрация
POST-запрос <http://{domain}/api/user/login/api/user/registration> производит регистрацию в системе.
Данные для регистрации (тело запроса) - объект JSON с параметрамис параметрами "login", "firstName" (имя), "lastName" (фамилия), "patronym" (отчество), "email" (мыло), "phone" (тел. номер), "password1" и "password2" (пароль и повтор пароля).
Все параметры обязательны.
В качестве тел. номера пока что допускается указывать только российский номер мобильного телефона.
```
Пример
  POST http://{domain}/api/user/login/api/user/registration
  {
    "login":"user2",
    "firstName":"Имя",
    "lastName":"Фамилиев",
    "patronym":"Отчествович",
    "email":"q@q.ru",
    "phone":"88005553535",
    "password1":"Пороль",
    "password2":"Пороль",
  }
```

***