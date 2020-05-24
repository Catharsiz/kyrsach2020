grammar g;

go
   : block '@'
   ;

block
   : consts* vars* procedure* statement
   ;

consts
   : 'CONST' ident '=' (number|floatnumber) ';'
   ;

vars
   : 'VAR' ident '=' (number|floatnumber)';'
   ;

procedure
   : 'PROCEDURE' ident ';' block ';'
   ;

statement
   : (assignstmt | callstmt | printmess | breakstmt | continuestmt | beginstmt | ifstmt | whilestmt)+
   ;

assignstmt
   : ident '='  expression ';'
   ;

callstmt
   : 'CALL' ident
   ;

printmess
   : 'output' '(' (expression | literal) ')'
   ;

beginstmt
   : '{' statement (';' statement)* '}'
   ;

ifstmt
   : 'IF' condition 'THEN' statement
   ;

whilestmt
   : 'WHILE' '(' condition ')' 'DO' statement
   ;

condition
   :  (('(')? expression ( '==' |'=' | '<' | '!=' | '<=' | '>' | '>=') expression)
      (('and' | 'or')  expression ( '==' |'=' | '<' | '!=' | '<=' | '>' | '>=') expression)*
   ;

breakstmt
    : 'BREAK'
    ;

continuestmt
    : 'CONTINUE'
    ;

expression
   : expression op = ('+'| '-') term #expr_op
   | term #term_expr
   ;

term
   : factor #factor_term
   | term op = ('/' | '*') factor #term_op
   ;

factor
    : ident # ident_factor
    | floatnumber # float_factor
    | number # integer_factor
    | '(' expression ')' # expr_factor
    ;

ident
   : STRING
   ;

floatnumber
    :  number '.' number
    ;

number
   : '-'? NUMBER
   ;

literal
    : STRINGLITERAL
    ;



STRINGLITERAL
   : '"' ~["\r]* '"'
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