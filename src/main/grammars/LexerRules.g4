lexer grammar LexerRules;

/* Tokens */
ID         : [a-zA-Z_]+                 ;
INT        : DIGIT+                     ;
BLOCK_BODY : [a-zA-Z]+                  ;


/* skip all whitespace */
WS : (' ' | NEWLINE | TAB) -> skip      ;


/* To be re-used in all numeric lexer rules */
fragment DIGIT   : [0-9]      ;
/* Used for crafting rules that respect Windows and Unix newlnies */
fragment NEWLINE : '\r'? '\n' ;
fragment TAB     : '\t'       ;

