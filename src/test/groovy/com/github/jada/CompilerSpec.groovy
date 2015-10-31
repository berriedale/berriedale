package com.github.jada

import com.github.jada.errors.CompilerError
import com.github.jada.grammars.*

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import spock.lang.*

class CompilerSpec extends Specification {

    @Ignore('Not handling errors at all yet')
    def "compileString with bad input should raise"() {
        given:
        final String buffer = 'WRONG'

        when:
        new Compiler().compileString(buffer)

        then:
        thrown(CompilerError)
    }


    def "wompwomp"() {
        given:
        final String buffer = '''
procedure Main is
begin

end Main;
'''
        Compiler compiler = new Compiler()
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(buffer.bytes))
        // create a lexer that feeds off of input CharStream
        AdaLexer lexer = new AdaLexer(input)
        // alternatively: create the lexer with ArrayInitLexer()
        // then attach the input stream using `setInputStream`
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer)
        AdaParser parser = new AdaParser(tokens)
        ParseTree tree = parser.procedureDeclaration()
        ParseTreeWalker walker = new ParseTreeWalker()
        walker.walk(new AdaASTListener(), tree)

        expect:
        true
    }
}
