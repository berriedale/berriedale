grammar Ada;
import LexerRules;


/* Primary rule, everything more ot less fits into a compilationg unit */
compilationUnit : ( withDeclaration | procedureDeclaration )+ ;


/*
    with Ada.Streams;
*/
withDeclaration : 'with' refList ';' ;

/*
    procedure Main is
    begin
      null;
    end Main;
*/
procedureDeclaration : 'procedure' ID 'is' block;
block           : 'begin' (expression | nullStatement)+ 'end' ID ';' ;
nullStatement   : 'null' ';' ;
expression      : objectRef '('
    (expression |
     objectRef  |
     STRING) ')' ';' ;

objectRef : ID ('.' ID)* ;
refList   : objectRef (',' objectRef)* ;
//expressionList  : expression (',' expression)* ;
