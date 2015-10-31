package com.github.jada.internal

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

import groovy.transform.TypeChecked

/**
 * CompilationUnit is an internal representation of an Ada compilation unit.
 *
 */
@TypeChecked
class CompilationUnit implements Opcodes {
    Map<String, CompilationUnit> subunits = [:]
    List<CompilationUnit> expressions = []
    String identifier

    protected ClassWriter classWriter

    /**
     * Initialize a {@code CompilationUnit} without any identifier
     */
    CompilationUnit() {
        /* no-op */
    }

    /**
     * Initialize a {@code CompilationUnit} with the given identifier
     *
     * @param id A valid Ada identifier for the compilation unit
     */
    CompilationUnit(String id) {
        this.identifier = id
    }


    protected void visit(ClassWriter writer) {
        /* no-op */
    }
}
