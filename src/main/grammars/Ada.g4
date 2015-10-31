grammar Ada;
import LexerRules;

/* Rules */
procedureDeclaration
    : 'procedure' ID 'is' block;

block : 'begin' (BLOCK_BODY | nullStatement) 'end' ID ';' ;

nullStatement: 'null' ';' ;
