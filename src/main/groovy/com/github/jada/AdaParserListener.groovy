package com.github.jada

import com.github.jada.grammars.AdaListener
import com.github.jada.grammars.AdaParser

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode


class AdaParserListener implements AdaListener {
    void enterProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        System.out.println("enter");
        System.out.println(context);
        System.out.println(context.ID());
    }

    void exitProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        System.out.println("exit");
        System.out.println(context);
    }

    void enterEveryRule(ParserRuleContext context) {
        System.out.println("enter every");
        System.out.println(context);
    }

    void exitEveryRule(ParserRuleContext context) {
        System.out.println("exit every");
        System.out.println(context);
    }

    void visitTerminal(TerminalNode node) {
        println 'visitTerminal'
    }

    void visitErrorNode(ErrorNode node) {
        println 'errornode'
    }

    void enterBlock(AdaParser.BlockContext context) {
    }

    void exitBlock(AdaParser.BlockContext context) {
    }

    void enterNullStatement(AdaParser.NullStatementContext ctx) {
    }

    void exitNullStatement(AdaParser.NullStatementContext ctx) {
    }
}
