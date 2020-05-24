import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;




public class MyVisitor extends gBaseVisitor<Object> {


    Stack <HashMap <String, Value>> Stack_var = new Stack<>();;
    HashMap<String, Value> tableforprint = new HashMap<>();
    HashMap<String, gParser.BlockContext> function = new HashMap<>();

    private Value getVariable(String varName) throws Exception {
        if (tableforprint.containsKey(varName))
            return tableforprint.get(varName);
        for (HashMap<String, Value> hm : Stack_var) {
            if (hm.containsKey(varName)) {
                return hm.get(varName);
            }
        }
        throw new Exception("No such variable in the table");
    }


    private void setVariable(String variableName, Value value) throws Exception {
        value.setIdent(variableName);
        if (tableforprint.containsKey(variableName)) {
            Value val = tableforprint.get(variableName);
            if (val.isConst()) throw new Exception("You cannot change the value of a constant " + variableName);
            else tableforprint.replace(variableName, value);
        }
        else
            for (HashMap<String, Value> cv: Stack_var) {
                if (cv.containsKey(variableName)) {
                    Value val = cv.get(variableName);
                    if (val.isConst()) throw new Exception("You cannot change the value of a constant " + variableName);
                    else cv.replace(variableName, value);
                }
                else throw  new Exception("Variable" + variableName + " is not identified");
            }
    }

    private void callProcedure(String ident) throws Exception{
        if (function.containsKey(ident)) {
            visit(function.get(ident));
        }
        else throw new Exception("Procedure" + ident + " is not identified");
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitGo(gParser.GoContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitBlock(gParser.BlockContext ctx) {
        boolean stack = false;
        if (!tableforprint.isEmpty()) {
            Stack_var.push(tableforprint);
            stack = true;
        }
        visitChildren(ctx);
        if (stack){
            tableforprint = Stack_var.pop();
        } else
            tableforprint.clear();
        return null;
    }

    @Override public Object visitConsts(gParser.ConstsContext ctx) {

        String type;
        Value value;

        String name = ctx.ident().getText();

        if (ctx.children.contains(ctx.number())){
            Object num = ctx.number().getText();
            type = "INTEGER";
            value = new Value(name, type, num, true);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue() + " , константа? " +  value.isConst());

        }
        else if (ctx.children.contains(ctx.floatnumber())){
            Object floatnum = ctx.floatnumber().getText();
            type = "FLOAT";
            value = new Value(name, type, floatnum, true);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue() + " , константа? " +  value.isConst());

        }
        return visitChildren(ctx);
    }


    @Override public Object visitVars(gParser.VarsContext ctx) {

        String type;
        Value value;

        String name = ctx.ident().getText();

        if (ctx.children.contains(ctx.number())){
            Object num = ctx.number().getText();
            type = "INTEGER";
            value = new Value(name, type, num, false);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue() + " , константа? " +  value.isConst());

        }
        else if (ctx.children.contains(ctx.floatnumber())){
            Object floatnum = ctx.floatnumber().getText();
            type = "FLOAT";
            value = new Value(name, type, floatnum, false);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue() + " , константа? " +  value.isConst());

        }
        return visitChildren(ctx);
    }



    @Override public Object visitProcedure(gParser.ProcedureContext ctx) {
        String ident = ctx.ident().getText();
        function.put(ident, ctx.block());
        return null;
    }

    @Override public Object visitStatement(gParser.StatementContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAssignstmt(gParser.AssignstmtContext ctx) {
        String variableName = ctx.ident().getText();
        Value value = (Value) visit(ctx.expression());
        try {
            setVariable(variableName, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override public Object visitCallstmt(gParser.CallstmtContext ctx) {
        try {
            callProcedure(ctx.ident().getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override public Object visitPrintmess(gParser.PrintmessContext ctx) {

        String name;

        if(ctx.children.contains(ctx.expression())){
            name = ctx.expression().getText();
            System.out.println("output переменной " + tableforprint.get(name));
        } else if (ctx.children.contains(ctx.literal()))
        {
            name = ctx.literal().getText();
            System.out.println("output Строковой литерал: " + name);
        }
        return visitChildren(ctx);
    }



    @Override public Object visitBeginstmt(gParser.BeginstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIfstmt(gParser.IfstmtContext ctx) {
        Value value = (Value) visit(ctx.condlast());
        if (Boolean.parseBoolean(value.getValue().toString())){
            visit(ctx.beginstmt());
        }
        return null;
    }

    @Override public Object visitWhilestmt(gParser.WhilestmtContext ctx) {
        Value value = (Value) visit(ctx.condlast());
        while (Boolean.parseBoolean(value.getValue().toString())){
            visit(ctx.beginstmt());
            value = (Value) visit(ctx.condlast());
        }
        return null;
    }



    @Override public Object visitBreakstmt(gParser.BreakstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitContinuestmt(gParser.ContinuestmtContext ctx) { return visitChildren(ctx); }

    @Override public Object visitIdent(gParser.IdentContext ctx) { return visitChildren(ctx); }

    @Override public Object visitExpr_op(gParser.Expr_opContext ctx) {
        Value left = (Value) visit(ctx.expression());
        Value right = (Value) visit(ctx.term());
        switch (ctx.op.getText()) {
            case "+":
                try {
                    if (Utils.CheckType(left, right)) {
                        System.out.println(Utils.Sum(left, right));
                        return Utils.Sum(left, right);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-":
                try {
                    if (Utils.CheckType(left, right)) {
                        System.out.println(Utils.Sub(left, right));
                        return Utils.Sub(left, right);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
        return null;
    }

    @Override public Object visitTerm_expr(gParser.Term_exprContext ctx) {
        return (Value) visit(ctx.term());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitTerm_op(gParser.Term_opContext ctx) {
        Value left = (Value) visit(ctx.term());
        Value right = (Value) visit(ctx.factor());
        switch (ctx.op.getText()) {
            case "/":
                try {
                    if (Utils.CheckType(left, right)) {
                        System.out.println(Utils.Div(left, right));
                        return Utils.Div(left, right);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "*":
                try {
                    if (Utils.CheckType(left, right)) {
                        System.out.println(Utils.Mult(left, right));
                        return Utils.Mult(left, right);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFactor_term(gParser.Factor_termContext ctx) {
        return (Value) visit(ctx.factor());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIdent_factor(gParser.Ident_factorContext ctx) {
        try {
            return getVariable(ctx.ident().getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitInteger_factor(gParser.Integer_factorContext ctx) {
        System.out.println(ctx.number().getText());
        return new Value ("", "INTEGER", Integer.parseInt(ctx.number().getText()), false);
    }



    @Override public Object visitFloat_factor(gParser.Float_factorContext ctx) {
        System.out.println((ctx.floatnumber().getText()));
        return new Value ("", "FLOAT", Float.parseFloat(ctx.floatnumber().getText()), false);
    }




    @Override public Object visitExpr_factor(gParser.Expr_factorContext ctx) {
        return (Value) visit(ctx.expression());
    }



    @Override public Object visitFloatnumber(gParser.FloatnumberContext ctx) { return visitChildren(ctx); }


    @Override public Object visitNumber(gParser.NumberContext ctx) { return visitChildren(ctx); }


    @Override public Object visitCondlast(gParser.CondlastContext ctx) {
        if (ctx.condition().size() > 1) {
            Value left = (Value) visit(ctx.condition(0));

            Value right = (Value) visit(ctx.condition(1));

            if (ctx.check.getText() == "or") {
                return new Value("", Utils.Bool, (Boolean.parseBoolean(left.getValue().toString()) || Boolean.parseBoolean(right.getValue().toString())), false);
            } else if (ctx.check.getText() == "and") {
                return new Value("", Utils.Bool, (Boolean.parseBoolean(left.getValue().toString()) && Boolean.parseBoolean(right.getValue().toString())), false);
            }
            return visitChildren(ctx);
        }
        else {
            return visitChildren(ctx);
        }
    }

    @Override public Object visitCond_expr_all(gParser.Cond_expr_allContext ctx) {
        Value left = (Value) visit(ctx.expression(0));
        Value right = (Value) visit(ctx.expression(1));
        try {
            if (!Utils.CheckTypeAll(left, right)) return null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Value value;
        try {
            return Utils.Compare(ctx.check.getText(), left, right);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public Object visitCond_expr(gParser.Cond_exprContext ctx) {
        Value result = (Value) visit(ctx.expression());
        try {
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

