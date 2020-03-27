grammar g;

go
   : block '.'
   ;

block
   : consts? vars? procedure* statement
   ;

consts
   : 'CONSTANT' ident '=' number (',' ident '=' number)*
   ;

vars
   : 'INTEGER' ident (',' ident)*
   ;

procedure
   : 'FUNCTION' ident  block
   ;

statement
   : (assignstmt | callstmt | writestmt | qstmt | bangstmt | beginstmt | ifstmt | whilestmt )
   ;

assignstmt
   : ident '=' expression
   ;

callstmt
   : 'FUNC' ident
   ;

writestmt
   : 'WRITE' ident
   ;

qstmt
   : '?' ident
   ;

bangstmt
   : 'output' expression
   ;

beginstmt
    : '{' block (block)* '}'
    ;

ifstmt
   : 'IF' '('condition')' 'THEN' statement
   ;

whilestmt
   : 'WHILE' '('condition')' 'DO' statement
   ;

condition
   : 'ODD' expression
   | expression ('==' | '#' | '<' | '<=' | '>' | '>=') expression
   ;

expression
   : ('+' | '-')? term (('+' | '-') term)*
   ;

term
   : factor (('*' | '/') factor)*
   ;

factor
   : ident
   | number
   | '(' expression ')'
   ;

ident
   : STRING
   ;

number
   : NUMBER
   ;



STRING
   : [a-zA-Z] [a-zA-Z]*
   ;


NUMBER
   : [0-9] +
   ;


WS
   : [ \t\r\n] -> skip
   ;

BlockComment
    : '/*' .*? '*/' -> skip
    ;

OdrinaryComment
    : '//' .*? '\n' -> skip
    ;
