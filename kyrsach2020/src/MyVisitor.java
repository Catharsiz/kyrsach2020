import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;




public class MyVisitor extends gBaseVisitor<Object> {


    Stack <HashMap <String, Value>> Stack;

    HashMap<String, Value> tableforprint = new HashMap<>();

    private Value getVariable(String varName) throws Exception {
        if (tableforprint.containsKey(varName))
            return tableforprint.get(varName);
        for (HashMap<String, Value> hm : Stack) {
            if (hm.containsKey(varName)) {
                return hm.get(varName);
            }
        }
        throw new Exception("No such variable in the table");
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
    @Override public Object visitBlock(gParser.BlockContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
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
            type = "DOUBLE";
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
            type = "DOUBLE";
            value = new Value(name, type, floatnum, false);
            tableforprint.put(name,value);

            System.out.println( "Переменная: " + value.getIdent() + " ,  тип переменной: "+ value.getType() + " , Значение: " + value.getValue() + " , константа? " +  value.isConst());

        }
        return visitChildren(ctx);
    }



    @Override public Object visitProcedure(gParser.ProcedureContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitStatement(gParser.StatementContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAssignstmt(gParser.AssignstmtContext ctx) {
/*
        Value val = tableforprint.get(ctx.ident().getText());
        Object oldValue =  val.getValue();

        val.setValue(visitExpr_op(ctx.expression().));
        System.out.println(val);

       // System.out.println("Переменная '" +ctx.ident().getText() + "' была изменена," +
         //      " старое: " + oldValue );
*/
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitCallstmt(gParser.CallstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */

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
    @Override public Object visitIfstmt(gParser.IfstmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitWhilestmt(gParser.WhilestmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitCondition(gParser.ConditionContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
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
}