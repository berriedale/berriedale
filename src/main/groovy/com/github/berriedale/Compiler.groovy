package com.github.berriedale

import com.github.berriedale.grammars.*
import com.github.berriedale.internal.*

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker

/*
 * Primary compiler interface
 */
class Compiler {
    CompilationUnit compileString(String buffer) {
        CompilationUnit root = new RootCompilationUnit()

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
        walker.walk(new AdaParserListener(root), tree)

        return root
    }
}
