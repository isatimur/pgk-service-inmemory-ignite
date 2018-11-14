Сборка
------
Для сборки дистрибутива к корневом каталоге репа запускается команда:
```batch
sbt clean clean-files dist
```
После сборки в под-каталоге `\target\universal\` появится файл `pgk-service-inmemory-ignite-1.0.zip` 

Запуск
------
Взять файл `pgk-service-inmemory-ignite-1.0.zip`, сформированный на этапе сборки, раззиповать куда-либо и запустить приложение:
```batch
./pgk-service-inmemory-ignite-1.0/bin/pgk-service-inmemory-ignite
```

Тюнинг
------
По умолчанию приложение использует файл конфигурации `conf/application.conf`. В нём лежат все настройки программы, включая реквизиты доступа к базе данных.
Запустить приложение с использованием альтернативного конфигурационного (здесь -- `super.puper.conf`) файла можно так:
```batch
./pgk-service-inmemory-ignite-1.0/bin/pgk-service-inmemory-ignite -Dconfig.resource=super.puper.conf
```

Проверка
--------
При нормальном запуске приложение должно корректно реагировать на запрос GET /
т.е. оно должно выдать 200 OK и пустую страницу

