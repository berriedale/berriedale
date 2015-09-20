package com.github.jada;

import com.github.jada.grammars.AdaListener;
import com.github.jada.grammars.AdaParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 */
public class AdaASTListener implements AdaListener {
    public void enterProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        System.out.println("enter");
        System.out.println(context);
        System.out.println(context.IDENTIFIER());
    }

    public void exitProcedureDeclaration(AdaParser.ProcedureDeclarationContext context) {
        System.out.println("exit");
        System.out.println(context);
    }

    public void enterEveryRule(ParserRuleContext context) {
        System.out.println("enter every");
        System.out.println(context);
    }

    public void exitEveryRule(ParserRuleContext context) {
        System.out.println("exit every");
        System.out.println(context);
    }


    public void visitTerminal(TerminalNode node) {
    }

    public void visitErrorNode(ErrorNode node) {
    }

    public void enterBlock(AdaParser.BlockContext context) {
    }

    public void exitBlock(AdaParser.BlockContext context) {
    }
}
