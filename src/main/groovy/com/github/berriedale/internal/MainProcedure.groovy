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
        //visitor.visitInsn(RETURN);
        //visitor.visitMaxs(1, 1);
        visitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        // pushes the "Hello World!" String constant
        visitor.visitLdcInsn("Hello world!");
        // invokes the 'println' method (defined in the PrintStream class)
        visitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V");
        visitor.visitInsn(RETURN);
        // this code uses a maximum of two stack elements and two local
        // variables
        visitor.visitMaxs(2, 2);
        visitor.visitEnd()
    }
}
