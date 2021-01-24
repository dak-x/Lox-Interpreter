package lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    final Stmt.Function declarations;

    LoxFunction(Stmt.Function declarations) {
        this.declarations = declarations;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguements) {
        Environment environment = new Environment(interpreter.globals);
        for (int i = 0; i < declarations.params.size(); i++) {
            environment.define(declarations.params.get(i).lexeme, arguements.get(i));
        }
        interpreter.executeBlock(declarations.body, environment);
        return null;
    }

    @Override
    public int arity() {
        return this.declarations.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declarations.name.lexeme + ">";
    }
}