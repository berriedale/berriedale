package com.github.berriedale.internal

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor

import groovy.transform.TypeChecked
import groovy.transform.InheritConstructors

/**
 * MainProcedure represents the static entry point for a program.
 */
@TypeChecked
@InheritConstructors
class MainProcedure extends Procedure {
    static final String MAIN_PROCEDURE_NAME = 'Main'

    @Override
    protected void visit(ClassWriter writer) {
        MethodVisitor visitor = writer.visitMethod(ACC_PUBLIC + ACC_STATIC,
                                    "main",
                                    "([Ljava/lang/String;)V",
                                    null,
                                    null)
        visitor.visitInsn(RETURN);
        visitor.visitMaxs(1, 1);
        visitor.visitEnd()
    }
}
