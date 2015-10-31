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
  null;
end Main;
'''
        Compiler compiler = new Compiler()
        compiler.compileString(buffer)

        expect:
        true
    }
}
