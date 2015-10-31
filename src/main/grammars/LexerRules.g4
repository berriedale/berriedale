lexer grammar LexerRules;

/* Tokens */
IDENTIFIER : [a-zA-Z_]+ ;
BLOCK_BODY : [a-zA-Z]+ ;

WS : [ \t\r\n]+ -> skip ;
