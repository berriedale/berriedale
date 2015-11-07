lexer grammar LexerRules;

ID         : Letter LetterOrDigit*      ;
INT        : DIGIT+                     ;
BLOCK_BODY : [a-zA-Z]+                  ;
STRING     : '"' STRING_CHAR* '"'             ;

/* skip all whitespace */
WS : (' ' | NEWLINE | TAB) -> skip      ;

/* To be re-used in all numeric lexer rules */
fragment DIGIT   : [0-9]      ;
/* Used for crafting rules that respect Windows and Unix newlnies */
fragment NEWLINE : '\r'? '\n' ;
fragment TAB     : '\t'       ;

fragment STRING_CHAR : ~["\\\\r\n];

fragment Letter : [a-zA-Z$_] ;
fragment LetterOrDigit : [a-zA-Z0-9$_] ;
