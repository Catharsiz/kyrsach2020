### Разработка осуществлялась с помощью IDE IntelliJ IDEA и ANTLR.
Входными данными является файл с исходным кодом на языке g.
Выходными данными является файл с расширением ll.
Объектный код llvm выполняется с помощью llc:
```
llc -filetype=obj output.ll
gcc output.o -no-pie -o output
./output.ll
```

 ###	Грамматика языка основана на pl0 с добавлением функций, неоходимых для выполнения требовани курсовой работы.



* Типы данных: INTEGER, FLOAT. Также возможен вывод литералов(строк) на экран.

*	При объявлении тип переменной указывать не нужно. Тип INTEGER может взаимодействовать только с типом INTEGER, также как и FLOAT можно взаимодействовать только с FLOAT.
	
### Объявление переменных должно быть в самом начале программы.
```
VAR c = 55.2;
VAR z = 50.0;
VAR i = 0; 
```
Для присвоение используется символ "="

*Вначале кода указываются глобальные перенные.
*В процедурах создавать переменные нельзя.
*Оператор присваивания ':='

### Арифметические операнды: ==, >=, <=, >, <, !=,  +, -, /, *

### Логические операторы: НЕ (!), И (AND), ИЛИ (OR)
  
*Каждое отдельное условие должно быть заключено в скобки: 
```
IF ( z == 44.0) or ( c>=150.0) THEN{}
 	WHILE (c<=250.0) and (z>10.0) DO {}
 	 IF (i==3) THEN {}
```

### Условный оператор:

## Конструкция типа   IF (условие) THEN {блок действий}
## Условие может быть: 
* выражено сравнением двух чисел
* выражено сравнением двух переменных
* выражено сравнением одной переменной и одного числа.
              

### Оператор цикла:
 ## Конструкция типа  WHILE (условие) DO {блок действий}
  ## Условие может быть: 
* выражено сравнением двух чисел
* выражено сравнением двух переменных
* выражено сравнением одной переменной и одного числа.
  
  

1. Базовый вывод:
2.  Конструкция типа output (пременная или значение или строковой литерал);


### Комментарии:
 *   - // (однострочный комментарий)
 *  - /* ... */ (многострочный комментарий)

1. Операторными скобками выступает конструкция { ... };
2. основного блока, там ставится '@'
