package com.github.jada

import com.github.jada.errors.CompilerError
import com.github.jada.grammars.*
import com.github.jada.internal.*

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import spock.lang.*

class CompilerSpec extends Specification {
    Compiler compiler

    def setup() {
        this.compiler = new Compiler()
    }

    @Ignore('Not handling errors at all yet')
    def "compileString with bad input should raise"() {
        given:
        final String buffer = 'WRONG'

        when:
        compiler.compileString(buffer)

        then:
        thrown(CompilerError)
    }


    def "wompwomp"() {
        given:
        CompilationUnit unit = null
        final String buffer = '''
procedure Main is
begin
  null;
end Main;
'''

        when:
        unit = compiler.compileString(buffer)

        then: 'there should be expressions collected'
        unit.expressions.size == 1

        and: 'the primary expression should be "Main"'
        unit.expressions[0].identifier == 'Main'

        and: 'the CompilationUnit should emit byte code'
        unit.asByteCode
        FileOutputStream fos = new FileOutputStream('Root.class')
        fos.write(unit.asByteCode)
        fos.close()

    }
}
