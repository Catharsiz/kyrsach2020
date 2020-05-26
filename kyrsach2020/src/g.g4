grammar g;

go
   : block '@'
   ;

block
   : vars* procedure* statement
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
   : 'output' '(' (expression |literal) ')' ';'
   ;

beginstmt
   : '{' statement (';' statement)* '}'
   ;

ifstmt
   : 'IF' condlast 'THEN' beginstmt
   ;

whilestmt
   : 'WHILE'  condlast  'DO' beginstmt
   ;

condlast
    : condition (check = ('or' | 'and') condition)*
    ;

condition
    : '(' expression (check = ('==' | '!=' | '<' | '<=' | '>' | '>=')) expression ')' # cond_expr_all
    | expression # cond_expr
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
    : '"'STRINGLITERAL'"'
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