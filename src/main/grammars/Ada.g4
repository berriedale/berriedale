grammar Ada;
import LexerRules;


/* Rules */
procedureDeclaration
    : 'procedure' IDENTIFIER 'is' block;

block : 'begin' WS? BLOCK_BODY? 'end' IDENTIFIER ';' ;

