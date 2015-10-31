package com.github.jada.internal

import org.objectweb.asm.*

import groovy.transform.TypeChecked
import groovy.transform.InheritConstructors

/**
 * RootCompilationUnit is the root unit for compilation (e.g. unpackaged)
 *
 * In the context of Ada, there's a "global" compilation unit, where everything
 * is a "World" or "Kernel" (to borrow Ruby terminology) scope.
 */
@TypeChecked
@InheritConstructors
class RootCompilationUnit extends CompilationUnit {
    static final String ROOT_CLASS_NAME = 'Root'

    byte[] getAsByteCode() {
        classWriter = new ClassWriter(0)
        MethodVisitor mw
        classWriter.visit(V1_1, ACC_PUBLIC,
            ROOT_CLASS_NAME,
            null,
           'java/lang/Object',
           null)

        mw = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
                null);

        mw.visitVarInsn(ALOAD, 0);
        // invokes the super class constructor
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        // this code uses a maximum of one stack element and one local variable
        mw.visitMaxs(1, 1);
        mw.visitEnd();

        expressions.each { CompilationUnit expr ->
            expr.visit(classWriter)
        }

        return classWriter.toByteArray()
    }
}
