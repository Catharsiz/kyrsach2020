import org.antlr.v4.runtime.tree.*;

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
    @Override public Object visitAssignstmt(gParser.AssignstmtContext ctx) { return visitChildren(ctx); }
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
    @Override public Object visitPrintmess(gParser.PrintmessContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
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
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitExpression(gParser.ExpressionContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitTerm(gParser.TermContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFactor(gParser.FactorContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIdent(gParser.IdentContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFloatnumber(gParser.FloatnumberContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitNumber(gParser.NumberContext ctx) { return visitChildren(ctx); }
}