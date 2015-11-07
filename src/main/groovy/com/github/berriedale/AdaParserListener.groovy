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
    }

    void exitEveryRule(ParserRuleContext context) {
    }

    void visitTerminal(TerminalNode node) {
        println "visitTerminal ${node}"
    }

    void visitErrorNode(ErrorNode node) {
        println "errorNode: ${node}"
    }

    void enterBlock(AdaParser.BlockContext context) {
        //println "enterBlock (${context}) ${context.expression(0)?.OBJREF()}"
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

    void enterWithDeclaration(AdaParser.WithDeclarationContext ctx) {
        //println "with ${ctx.OBJREF()}"
       // println "with ${ctx.ID()}"
    }

    void exitWithDeclaration(AdaParser.WithDeclarationContext ctx) {
    }

    void enterCompilationUnit(AdaParser.CompilationUnitContext ctx) {
    }

    void exitCompilationUnit(AdaParser.CompilationUnitContext ctx) {
    }

    void enterExpression(AdaParser.ExpressionContext ctx) {
    }

    void exitExpression(AdaParser.ExpressionContext ctx) {
    }

    void enterRefList(AdaParser.RefListContext ctx) { }
    void exitRefList(AdaParser.RefListContext ctx) { }

    void enterObjectRef(AdaParser.ObjectRefContext ctx) { }
    void exitObjectRef(AdaParser.ObjectRefContext ctx) { }
}
