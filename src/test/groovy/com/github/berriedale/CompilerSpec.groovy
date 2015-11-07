package com.github.berriedale

import com.github.berriedale.errors.CompilerError
import com.github.berriedale.grammars.*
import com.github.berriedale.internal.*

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import spock.lang.*


import groovy.transform.TypeChecked

@TypeChecked
class ClassLoadingHarness extends ClassLoader {
    boolean executeCode(String name, byte[] code) {
        Class<?> testClass = this.defineClass(name,
                                        code,
                                        0,
                                        code.length)
        testClass.methods[0].invoke(null, [null] as Object[])
        return true
    }
}

class CompilerSpec extends Specification {
    Compiler compiler
    CompilationUnit unit

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


    def "Compile a basic no-op Main procedure "() {
        given:
        final String buffer = '''\
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
        def harness = new ClassLoadingHarness()
        harness.executeCode('Root', unit.asByteCode)
    }

    def "Compile a basic procedure that invokes Java code"() {
        given:
        final String buffer = '''\
with Java;
procedure Main is
begin
  System.out.println ("hello");
end Main;
'''

        when:
        unit = compiler.compileString(buffer)

        then: 'there should be expressions collected'
        unit.expressions.size == 1

        and: 'the primary expression should be "Main"'
        unit.expressions[0].identifier == 'Main'
    }
}
