grammar Ada;


/* Rules */
procedureDeclaration
    : 'procedure' IDENTIFIER 'is' block;

block : 'begin' WS? BLOCK_BODY? 'end' IDENTIFIER ';' ;

/* Tokens */
IDENTIFIER : [a-zA-Z_]+ ;
BLOCK_BODY : [a-zA-Z]+ ;

WS : [ \t\r\n]+ -> skip ;
