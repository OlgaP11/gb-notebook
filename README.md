*Задание после 7-ого семинара*

Дописать проект, "Записная книжка", содержащий работу с записками из консоли (можно прочитать все записки, 
создать одну записку, отредактировать записку, удалить, и прочитать одну записку по ID), 
записка содержит в себе как минимум 3 поля Id, заголовок и текст, можно добавить дату. 
Хранение по вашему выбору, можно в текстовом файле (или каждая записка в одном файле, как вам удобнее). 
Можно сделать по аналогии с программой из 5 урока. Если вы сделали записки к этому уроку, 
то добавить логирование.
Продумать, какие паттерны можно использовать в вашем проекте

*Что сделано мной*
1. Добавлена система логирования. С помощью паттерн а Декоратор добавлен класс ConsoleLog 
для вывода логов в консоль.
2. Конструктор для внесения данных по пользователю через builder.