package com.github.berriedale

import com.github.berriedale.internal.*

import com.github.berriedale.grammars.AdaListener
import com.github.berriedale.grammars.AdaParser

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode


class AdaParserListener implements AdaListener {
    protected CompilationUnit rootCompilationUnit

    protected CompilationUnit getCurrentUnit() {
        if (rootCompilationUnit.expressions.size > 0) {
            return rootCompilationUnit.expressions[-1]
        }
        return rootCompilationUnit
    }

    AdaParserListener(CompilationUnit unit) {
        /* add the root unit */
        this.rootCompilationUnit = unit
    }

    void enterProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        println("enter '${context.ID()}' (${context})");

        String identifier = context.ID().text

        if (identifier == MainProcedure.MAIN_PROCEDURE_NAME) {
            currentUnit.expressions << new MainProcedure(identifier)
        }
        else {
            currentUnit.expressions << new Procedure(identifier)
        }
    }

    void exitProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        println("exit '${context.ID()}' (${context})");
    }

    void enterEveryRule(ParserRuleContext context) {
        println("enter every (${context})");
    }

    void exitEveryRule(ParserRuleContext context) {
        println("exit every (${context})");
    }

    void visitTerminal(TerminalNode node) {
        println "visitTerminal ${node}"
    }

    void visitErrorNode(ErrorNode node) {
        println "errorNode: ${node}"
    }

    void enterBlock(AdaParser.BlockContext context) {
        println "enterBlock (${context})"
    }

    void exitBlock(AdaParser.BlockContext context) {
        println "exitBlock (${context})"
    }

    void enterNullStatement(AdaParser.NullStatementContext ctx) {
        println "start null statement"
    }

    void exitNullStatement(AdaParser.NullStatementContext ctx) {
        println "end null statement"
    }
}
