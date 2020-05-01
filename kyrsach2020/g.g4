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
   : ('INTEGER'|'FLOAT') ident (',' ident)*
   ;

procedure
   : 'FUNCTION' ident ident? (',' ident)* block
   ;

statement
   : (assignstmt | callstmt | writestmt | bangstmt | beginstmt | ifstmt | whilestmt | breakstmt )
   ;

assignstmt
   : ident '=' expression
   ;

callstmt
   : 'FUNC' ident ident? (',' ident)*
   ;

breakstmt
    : 'BREAK'
    ;

writestmt
   : 'WRITE' ident
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
   | floatnumber
   | '(' expression ')'
   ;

ident
   : STRING
   ;

number
   : '-'? NUMBER
   ;


floatnumber
   : '-'? NUMBER','NUMBER
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
