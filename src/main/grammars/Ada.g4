grammar Ada;
import LexerRules;


/* Primary rule, everything more ot less fits into a compilationg unit */
compilationUnit : (procedureDeclaration
    | withDeclaration)+ ;


/*
    procedure Main is
    begin
      null;
    end Main;
*/
procedureDeclaration : 'procedure' ID 'is' block;

/*
    with Ada.Streams;
*/
withDeclaration : 'with' ID ';' ;
block           : 'begin' (BLOCK_BODY | expression | nullStatement) 'end' ID ';' ;
nullStatement   : 'null' ';' ;
expression      : ID ('.' ID)* '('
    (expression |
     ID         |
     STRING) ')' ';' ;

expressionList  : expression (',' expression)* ;
